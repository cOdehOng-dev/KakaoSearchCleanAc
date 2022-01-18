package com.c0de_h0ng.kakaosearchcleanac.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
@HiltViewModel
class BlogViewModel @Inject constructor(
    private val getBlogUseCase: GetBlogUseCase
) : ViewModel() {

    private val _blogList = MutableLiveData<List<Blog>>()
    val blogList: LiveData<List<Blog>>
        get() = _blogList

    fun getBlogResultList(searchWord: String) {
        getBlogUseCase(searchWord).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _blogList.value = result.data ?: emptyList()
                    Log.d("BlogViewModel", result.data?.size.toString() ?: "empty")
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}