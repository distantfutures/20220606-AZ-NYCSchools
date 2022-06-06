package com.distantfutures.a20220606_az_nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.distantfutures.a20220606_az_nycschools.R
import com.distantfutures.a20220606_az_nycschools.databinding.FragmentSchoolsListBinding
import com.distantfutures.a20220606_az_nycschools.viewmodel.SchoolsListViewModel

class SchoolsListFragment : Fragment() {

    private lateinit var binding: FragmentSchoolsListBinding
    private val schoolsListVM: SchoolsListViewModel by lazy {
        ViewModelProvider(this).get(SchoolsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools_list, container, false)

        binding.viewModel = schoolsListVM
        binding.schoolsList.adapter = SchoolsRecyclerViewAdapter()

        return binding.root
    }
}