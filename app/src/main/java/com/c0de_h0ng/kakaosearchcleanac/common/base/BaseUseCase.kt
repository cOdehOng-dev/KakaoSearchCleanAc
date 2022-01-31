package com.c0de_h0ng.kakaosearchcleanac.common.base

import androidx.lifecycle.MutableLiveData
import com.c0de_h0ng.kakaosearchcleanac.common.Resource

/**
 * Created by c0de_h0ng on 2022/01/31.
 */
abstract class BaseUseCase<R> {
    protected val result = MutableLiveData<Resource<R>>()
    fun observe() = result
}