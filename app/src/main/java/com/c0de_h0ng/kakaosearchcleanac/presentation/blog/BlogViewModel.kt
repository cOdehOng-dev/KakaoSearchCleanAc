package com.c0de_h0ng.kakaosearchcleanac.presentation.blog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.kakaosearchcleanac.common.CallResult
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseViewModel
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.toBlog
import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetBlogUseCase
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetRxBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
@HiltViewModel
class BlogViewModel @Inject constructor(
    private val getBlogUseCase: GetBlogUseCase,
    private val getRxBlogUseCase: GetRxBlogUseCase
) : BaseViewModel() {

    private val _blogList = MutableLiveData<List<Blog>>()
    val blogList: LiveData<List<Blog>>
        get() = _blogList

    private val blogResult = getRxBlogUseCase.observe()

    private val _rxBlogList = MediatorLiveData<List<Blog>>()
    val rxBlogList: LiveData<List<Blog>>
        get() = _rxBlogList


    // Coroutine + Flow
    fun getBlogResultList(searchWord: String) {
        loadingProgress(true)
        getBlogUseCase(searchWord).onEach { result ->
            when (result) {
                is CallResult.Success -> {
                    _blogList.value = result.data ?: emptyList()
                    Log.d("BlogViewModel", result.data?.size.toString())
                }
                is CallResult.Error -> {

                }
                is CallResult.Loading -> loadingProgress(result.isLoading)
            }
        }.launchIn(viewModelScope)
    }

    // RxJava
    fun getRxJavaSingleBlogResult(searchWord: String) {
        this(getRxBlogUseCase(searchWord))
        _rxBlogList.addSource(blogResult) {
            when (it) {
                is CallResult.Success -> {
                    Log.d("Resource >>> ", "Success")
                    val blog = it.data?.toBlog()
                    _rxBlogList.value = blog
                }
                is CallResult.Error -> {
                    Log.d("Resource >>> ", "Fail")
                }
                is CallResult.Loading -> {
                    Log.d("Resource >>> ", "Loading")
                    loadingProgress(it.isLoading)
                }
            }
        }
    }

}