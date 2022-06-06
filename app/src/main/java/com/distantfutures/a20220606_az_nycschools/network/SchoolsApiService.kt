package com.distantfutures.a20220606_az_nycschools.network

import com.distantfutures.a20220606_az_nycschools.data.School
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://data.cityofnewyork.us/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SchoolsApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolsList(@Path("name") name: String):
            Response<School>
}

object SchoolsApi {
    val retrofitService: SchoolsApiService by lazy {
        retrofit.create(SchoolsApiService::class.java)
    }
}