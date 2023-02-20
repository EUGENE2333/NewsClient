package com.example.newsapiclient.data.repository.dataSource

import com.example.newsapiclient.data.api.NewsAPIService
import com.example.newsapiclient.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.Query

interface NewsRemoteDataSource {
    // abstract functions to communicate with the api

    suspend fun getTopHeadlines(country:String,page: Int) : Response<APIResponse>
    suspend fun getSearchedNews(country:String,searchQuery:String,page: Int) : Response<APIResponse>
}