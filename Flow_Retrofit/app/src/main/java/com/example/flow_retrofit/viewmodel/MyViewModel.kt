package com.example.flow_retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow_retrofit.model.MyPost
import com.example.flow_retrofit.repository.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val myRepo: MyRepo) : ViewModel() {


    private val _myResponseFlow = MutableStateFlow("")
    val myResponseFlow = _myResponseFlow

    fun getPostFlow() {
       viewModelScope.launch {
           val response = myRepo.getPost()
           _myResponseFlow.value = response.body()
               ?.myUserId.toString()
       }
    }

    suspend fun getPost(): Response<MyPost>{
        return myRepo.getPost()
    }

}