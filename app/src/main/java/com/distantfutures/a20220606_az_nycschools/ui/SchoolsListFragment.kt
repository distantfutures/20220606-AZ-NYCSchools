package com.distantfutures.a20220606_az_nycschools.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.distantfutures.a20220606_az_nycschools.R
import com.distantfutures.a20220606_az_nycschools.data.School
import com.distantfutures.a20220606_az_nycschools.databinding.FragmentSchoolsListBinding
import com.distantfutures.a20220606_az_nycschools.viewmodel.SchoolsListViewModel

class SchoolsListFragment : Fragment() {
    private val TAG = "FragmentTEST"

    private lateinit var binding: FragmentSchoolsListBinding
    private val schoolsListVM: SchoolsListViewModel by lazy {
        ViewModelProvider(this).get(SchoolsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools_list, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = schoolsListVM
        // binds data to rv adapter(which has a clicklistener)
        binding.schoolsList.adapter = SchoolsRecyclerViewAdapter{
            // shows dialog box when school is clicked
            showDialog(it)
        }
        return binding.root
    }

    private fun showDialog(school: School) {
        val schoolName = school.school_name
        schoolsListVM.scoreOfSchool(school.dbn)
        Log.i(TAG, "School Name: $schoolName")

        if (schoolsListVM.scores.value == null) {
            val dialog = AlertDialog("NO SAT SCORES")
            dialog.show(parentFragmentManager, "School Info Dialog")
        } else {
            // initialize variables from scores in viewmodel
            val math = schoolsListVM.scores.value?.sat_math_avg_score
            val writing = schoolsListVM.scores.value?.sat_writing_avg_score
            val reading = schoolsListVM.scores.value?.sat_critical_reading_avg_score
            val satTakers = schoolsListVM.scores.value?.num_of_sat_test_takers

            // instantiates, sets & shows dialog of School's SAT scores
            val dialog = AlertDialog(
                "School: $schoolName \n\n" +
                "Math Score: $math \n" +
                "Writing Score: $writing \n" +
                "Reading Score: $reading \n" +
                "No. of SAT Tests Taken: $satTakers"
            )
            dialog.show(parentFragmentManager, "School Info Dialog")
        }
    }
}