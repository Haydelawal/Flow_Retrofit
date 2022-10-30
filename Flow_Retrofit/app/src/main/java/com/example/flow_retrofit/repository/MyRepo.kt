package com.example.flow_retrofit.repository

import com.example.flow_retrofit.data.Api
import com.example.flow_retrofit.model.MyPost
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MyRepo @Inject constructor(private val api: Api) {

    suspend fun getPost(): Response<MyPost> {
        return api.getPost()
    }

}