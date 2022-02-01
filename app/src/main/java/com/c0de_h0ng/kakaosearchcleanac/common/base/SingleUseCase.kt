package com.c0de_h0ng.kakaosearchcleanac.common.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, R> {
    private val result = MutableLiveData<Resource<R>>()

    fun observe() = result

    operator fun invoke(t: T): Disposable {
        return execute(t)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Result >>> ", "Success")
                result.value = Resource.Success(it)
            }, {
                Log.d("Result >>> ", it.localizedMessage ?: "An unexpected error occured")
                result.value = Resource.Error(it.localizedMessage ?: "An unexpected error occured")
            })
    }

    abstract fun execute(t: T): Observable<R>


}