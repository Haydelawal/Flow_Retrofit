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
class MyCustomPostVM @Inject constructor(private val myRepo: MyRepo) : ViewModel() {

    // query
    private val _myResponseFlow = MutableStateFlow("")
    val myResponseFlow = _myResponseFlow

    fun getPostFlow(userId: Int) {
        viewModelScope.launch {
            val response = myRepo.getCustomPost(userId)
            _myResponseFlow.value = response.body().toString()
        }
    }

    suspend fun getPost(userId: Int): Response<List<MyPost>> {
        return myRepo.getCustomPost(userId)
    }

}