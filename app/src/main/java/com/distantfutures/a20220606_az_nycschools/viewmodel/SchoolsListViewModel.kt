package com.distantfutures.a20220606_az_nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distantfutures.a20220606_az_nycschools.data.SATScores
import com.distantfutures.a20220606_az_nycschools.data.School
import com.distantfutures.a20220606_az_nycschools.network.SchoolsApi
import com.distantfutures.a20220606_az_nycschools.repository.SchoolsRepository
import com.distantfutures.a20220606_az_nycschools.repository.ScoresRepository
import kotlinx.coroutines.launch

class SchoolsListViewModel : ViewModel() {
    private val TAG = "VMTEST"

    private var _schoolsList = MutableLiveData<List<School>>()
    val schoolList: LiveData<List<School>>
        get() = _schoolsList

    private var scoresList = listOf<SATScores>()

    private var _scores = MutableLiveData<SATScores?>()
    val scores: LiveData<SATScores?>
        get() = _scores

    val service = SchoolsApi.retrofitService
    val schoolRepo = SchoolsRepository(service)
    val scoresRepo = ScoresRepository(service)

    init {
        requestSchoolsList()
        requestScores()
    }

    fun requestSchoolsList() {
        viewModelScope.launch {

            val response = schoolRepo.getSchoolsList()
            if(response.isSuccessful) {

                val listedSchools = response.body()

                _schoolsList.value = listedSchools!!
            } else {
                Log.e(TAG, "RESPONSE FAILED")
            }

        }
    }

    fun requestScores() {
        viewModelScope.launch {

            val response = scoresRepo.getScoresList()
            if(response.isSuccessful) {

                val list = response.body()

                if (list != null) {
                    scoresList = list
                }

            } else {
                Log.e(TAG, "RESPONSE FAILED")
            }
        }
    }

    fun scoreOfSchool(dbn: String) {
        val schoolScores = scoresList.find { it.dbn == dbn }
        if(schoolScores != null) {
            _scores.value = schoolScores
        } else {
            _scores.value = null
        }
        Log.i(TAG, "SCHOOL SCORES $schoolScores")
    }
}