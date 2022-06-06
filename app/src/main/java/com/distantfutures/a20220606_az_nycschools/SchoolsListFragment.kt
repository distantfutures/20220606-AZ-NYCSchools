package com.distantfutures.a20220606_az_nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.distantfutures.a20220606_az_nycschools.databinding.FragmentSchoolsListBinding

class SchoolsListFragment : Fragment() {

    private lateinit var binding: FragmentSchoolsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools_list, container, false)


        binding.schoolsList.adapter = MySchoolsRecyclerViewAdapter()

        return binding.root
    }
}