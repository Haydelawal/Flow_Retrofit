package com.example.flow_retrofit.data

import com.example.flow_retrofit.model.MyPost
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Api {

    @GET("posts/1")
    suspend fun getPost(): Response<MyPost>

    // path
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ) : Response<MyPost>

    // query
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int
    ) : Response<List<MyPost>>

    // querymap

    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId: Int,
        @QueryMap options : Map<String, String>
    ) : Response<List<MyPost>>
}