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
        binding.schoolsList.adapter = SchoolsRecyclerViewAdapter(){
            // Add code to open dialog box to show details
            showDialog(it)
        }
        return binding.root
    }

    fun showDialog(school: School) {
        val schoolName = school.school_name
//        val scores = schoolsListVM.scores.value?.dbn
        schoolsListVM.scoreOfSchool(school.dbn)
        if (schoolsListVM.scores.value == null) {
            val dialog = AlertDialog(schoolName, "NO SAT SCORES")
            dialog.show(parentFragmentManager, "School Info Dialog")
        } else {
            val math = schoolsListVM.scores.value?.sat_math_avg_score
            val writing = schoolsListVM.scores.value?.sat_writing_avg_score
            val reading = schoolsListVM.scores.value?.sat_critical_reading_avg_score

            val dialog = schoolName.let {
                AlertDialog(it,
                    "Math Score: $math \n" +
                            "Writing Score: $writing \n" +
                            "Reading Score: $reading")
            }
            dialog.show(parentFragmentManager, "School Info Dialog")
        }

    }
}