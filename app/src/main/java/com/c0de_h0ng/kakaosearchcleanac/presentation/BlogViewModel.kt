package com.c0de_h0ng.kakaosearchcleanac.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.toBlog
import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog
import com.c0de_h0ng.kakaosearchcleanac.domain.use_case.GetBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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

    private val disposables = CompositeDisposable()

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

    //RxJava
    fun getRxJavaBlogResult(searchWord: String) {
        disposables.add(getBlogUseCase.repository.getRxJavaBlogResult(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // 구독할 때 수행할 작업을 구현

            }
            .doOnTerminate {
                // 스트림이 종료될 때 수행할 작업을 구현
            }
            .subscribe({
                //onNext
                // 작업 중 오류가 발생하면 이 블록은 호출되지 x
                val blog = it.toBlog()
                Log.d("RxJava", blog.size.toString())
            }, {
                //onError
                // 에러 블록
                // 네트워크 오류나 데이터 처리 오류 등
                // 작업이 정상적으로 완료되지 않았을 때 호출
            }, {
                //onComplete
            })
        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}