package com.example.newsapiclient.data.api

import com.example.newsapiclient.BuildConfig
import com.example.newsapiclient.data.model.APIResponse
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsAPIService {
    // functions to get the data from the api with url end points and query parameters


@GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
    @Query("country")
    country: String,
    @Query("page")
    page: Int,
    @Query("apiKey")
    apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>

    @GET("/v2/top-headlines")
    suspend fun getTSearchedTopHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>
}