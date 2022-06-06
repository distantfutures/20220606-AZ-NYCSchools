package com.distantfutures.a20220606_az_nycschools.repository

import com.distantfutures.a20220606_az_nycschools.data.School
import com.distantfutures.a20220606_az_nycschools.network.SchoolsApiService
import retrofit2.Response

class SchoolsRepository(private val service: SchoolsApiService) {

    suspend fun getSchoolsList(): Response<List<School>> {
        return service.getSchoolsList()
    }
}