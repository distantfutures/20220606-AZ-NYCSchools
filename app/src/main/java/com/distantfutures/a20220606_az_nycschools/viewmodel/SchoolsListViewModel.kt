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
import retrofit2.HttpException
import java.io.IOException

class SchoolsListViewModel : ViewModel() {
    private val TAG = "VMTEST"

    // holds list of schools
    private var _schoolsList = MutableLiveData<List<School>?>()
    val schoolList: LiveData<List<School>?>
        get() = _schoolsList
    // holds list of SAT Scores
    private var scoresList = listOf<SATScores>()
    // holds school's SAT scores
    private var _scores = MutableLiveData<SATScores?>()
    val scores: LiveData<SATScores?>
        get() = _scores

    // Creates repos from retrofit service object
    private val service = SchoolsApi.retrofitService
    private val schoolRepo = SchoolsRepository(service)
    private val scoresRepo = ScoresRepository(service)

    init {
        requestSchoolsList()
        requestScores()
    }

    private fun requestSchoolsList() {
        viewModelScope.launch {
            try {
                val response = schoolRepo.getSchoolsList()
                if(response.isSuccessful) {
                    val listedSchools = response.body()
                    _schoolsList.value = listedSchools!!
                } else {
                    Log.e(TAG, "RESPONSE FAILED")
                    throw HttpException(response)
                }
            } catch (e: IOException) {
                Log.e(TAG, "RESPONSE FAILED $e")
            }
        }
    }

    private fun requestScores() {
        viewModelScope.launch {
            try {
                val response = scoresRepo.getScoresList()
                Log.e(TAG, "Score Response: $response")
                if(response.isSuccessful) {
                    // gives empty list if list is null
                    scoresList = response.body() ?: listOf()
                    Log.i(TAG, "Scores List: ${response.body()}")
                } else {
                    scoresList = listOf()
                    Log.e(TAG, "RESPONSE FAILED")
                }
            } catch (e: IOException) {
                Log.e(TAG, "RESPONSE FAILED $e")
            }
        }
    }

    fun scoreOfSchool(dbn: String) {
        val schoolScores = scoresList.find { it.dbn == dbn }
        if(schoolScores != null) _scores.value = schoolScores else _scores.value = null
        Log.i(TAG, "SCHOOL SCORES $schoolScores")
    }
}