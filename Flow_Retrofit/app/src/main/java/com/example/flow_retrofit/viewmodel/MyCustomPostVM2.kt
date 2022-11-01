package com.example.flow_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow_retrofit.model.MyPost
import com.example.flow_retrofit.repository.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MyCustomPostVM2 @Inject constructor(private val myRepo: MyRepo) : ViewModel() {

    // query map
    private val _myResponseFlow = MutableStateFlow("")
    val myResponseFlow = _myResponseFlow

    fun getPostFlow(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = myRepo.getCustomPost2(userId, options)
            _myResponseFlow.value = response.body().toString()
        }
    }

    suspend fun getPost(userId: Int, options: Map<String, String>): Response<List<MyPost>> {
        return myRepo.getCustomPost2(userId, options)
    }

}