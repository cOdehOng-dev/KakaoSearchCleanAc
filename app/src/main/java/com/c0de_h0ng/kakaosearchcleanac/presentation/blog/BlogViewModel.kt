package com.c0de_h0ng.kakaosearchcleanac.presentation.blog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseViewModel
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.toBlog
import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetBlogUseCase
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetRxJavaBlogUseCase
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
    private val getRxJavaBlogUseCase: GetRxJavaBlogUseCase
) : BaseViewModel() {

    private val _blogList = MutableLiveData<List<Blog>>()
    val blogList: LiveData<List<Blog>>
        get() = _blogList

    private val blogResult = getRxJavaBlogUseCase.observe()

    private val _rxJavaSingleBlogList = MediatorLiveData<List<Blog>>()
    val rxJavaSingleBlogList: LiveData<List<Blog>>
        get() = _rxJavaSingleBlogList

    //private val disposables = CompositeDisposable()

    //Coroutine + Flow
    fun getBlogResultList(searchWord: String) {
        getBlogUseCase(searchWord).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _blogList.value = result.data ?: emptyList()
                    Log.d("BlogViewModel", result.data?.size.toString())
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    // RxJava
    fun getRxJavaSingleBlogResult(searchWord: String) {
        this(getRxJavaBlogUseCase(searchWord))
        _rxJavaSingleBlogList.addSource(blogResult) {
            when (it) {
                is Resource.Success -> {
                    Log.d("Resource >>> ", "Success")
                    val blog = blogResult.value?.data?.toBlog()
                    _rxJavaSingleBlogList.value = blog
                }
                is Resource.Error -> {
                    Log.d("Resource >>> ", "Fail")
                }
                is Resource.Loading -> {
                    Log.d("Resource >>> ", "Loading")
                }
            }
        }
    }

    // RxJava
//    fun getRxJavaBlogResult(searchWord: String) {
//        disposables.add(getRxJavaUseCase(searchWord)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .doOnSubscribe {
//                // 구독할 때 수행할 작업을 구현
//            }
//            .doOnTerminate {
//                // 스트림이 종료될 때 수행할 작업을 구현
//            }
//            .subscribe({
//                //onNext
//                // 작업 중 오류(서버에서 404 에러 등)가 발생하면 이 블록은 호출되지 x
//                val blog = it.toBlog()
//                Log.d("RxJava", blog.size.toString())
//            }, {
//                //onError
//                // 에러 블록
//                // 네트워크 오류나 데이터 처리 오류 등
//                // 작업이 정상적으로 완료되지 않았을 때 호출
//            }, {
//                //onComplete
//            })
//        )
//    }

//    override fun onCleared() {
//        disposables.clear()
//        super.onCleared()
//    }

}