package com.example.flow_retrofit.data

import com.example.flow_retrofit.model.MyPost
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("posts/1")
    suspend fun getPost(): Response<MyPost>

}