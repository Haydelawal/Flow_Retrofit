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
class MyPathViewModel @Inject constructor(private val myRepo: MyRepo) : ViewModel() {

    private val _myResponseFlow = MutableStateFlow("")
    val myResponseFlow = _myResponseFlow

    fun getPostFlow(number: Int) {
        viewModelScope.launch {
            val response = myRepo.getPost2(number)
            _myResponseFlow.value = response.body()
                ?.myUserId.toString()
        }
    }

    suspend fun getPost(number: Int): Response<MyPost> {
        return myRepo.getPost2(number)
    }
}