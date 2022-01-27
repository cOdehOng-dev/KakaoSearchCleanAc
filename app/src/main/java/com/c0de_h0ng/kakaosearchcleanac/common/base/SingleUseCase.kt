package com.c0de_h0ng.kakaosearchcleanac.common.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io

/**
 * Created by c0de_h0ng on 2022/01/26.
 * https://ghs-dev.tistory.com/m/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-MVVM-Clean-Architecture
 */
abstract class SingleUseCase<T, R> {
    private val result = MutableLiveData<Resource<R>>()

    fun observe() = result

    operator fun invoke(t: T): Disposable {
        return execute(t)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Result >>> ", "Success")
                result.value = Resource.Success(it)
            }, {
                Log.d("Result >>> ", it.localizedMessage ?: "An unexpected error occured")
                result.value = Resource.Error(it.localizedMessage ?: "An unexpected error occured")
            })
    }

    abstract fun execute(t: T): Single<R>

}