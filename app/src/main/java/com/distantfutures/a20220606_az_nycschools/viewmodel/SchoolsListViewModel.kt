package com.distantfutures.a20220606_az_nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distantfutures.a20220606_az_nycschools.data.School
import com.distantfutures.a20220606_az_nycschools.network.SchoolsApi
import com.distantfutures.a20220606_az_nycschools.repository.SchoolsRepository
import kotlinx.coroutines.launch

class SchoolsListViewModel : ViewModel() {
    private val TAG = "VMTEST"

    private var _schoolsList = MutableLiveData<List<School>>()
    val schoolList: LiveData<List<School>>
        get() = _schoolsList

    val service = SchoolsApi.retrofitService
    val schoolRepo = SchoolsRepository(service)

    init {
        requestSchoolsList()
        // Mock School List
//        for (i in 0..20) {
//            list.add(School(school_name = "School $i"))
//        }
//        _schoolsList.value = list
    }

    fun requestSchoolsList() {
        viewModelScope.launch {

            val response = schoolRepo.getSchoolsList()
            if(response.isSuccessful) {

                val listedSchools = response.body()

                _schoolsList.value = listedSchools!!

                Log.i(TAG, "$listedSchools")
            } else {
                Log.e(TAG, "RESPONSE FAILED")
            }

        }
    }
}