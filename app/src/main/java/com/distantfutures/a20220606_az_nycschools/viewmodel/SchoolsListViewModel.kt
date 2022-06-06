package com.distantfutures.a20220606_az_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.distantfutures.a20220606_az_nycschools.data.School

class SchoolsListViewModel : ViewModel() {

    private var _schoolsList = MutableLiveData<List<School>>()
    val schoolList: LiveData<List<School>>
        get() = _schoolsList

    val list = mutableListOf<School>()


    init {
        for (i in 0..20) {
            list.add(School(school_name = "School $i"))
        }
        _schoolsList.value = list
    }
}