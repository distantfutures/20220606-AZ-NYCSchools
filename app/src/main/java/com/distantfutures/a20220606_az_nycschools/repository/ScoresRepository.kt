package com.distantfutures.a20220606_az_nycschools.repository

import com.distantfutures.a20220606_az_nycschools.data.SATScores
import com.distantfutures.a20220606_az_nycschools.network.SchoolsApiService
import retrofit2.Response

class ScoresRepository(private val service: SchoolsApiService) {

    suspend fun getScoresList(): Response<List<SATScores>> {
        return service.getScoresList()
    }
}