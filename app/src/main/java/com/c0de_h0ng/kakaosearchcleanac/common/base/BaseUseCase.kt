package com.c0de_h0ng.kakaosearchcleanac.common.base

import androidx.lifecycle.MutableLiveData
import com.c0de_h0ng.kakaosearchcleanac.common.CallResult

/**
 * Created by c0de_h0ng on 2022/01/31.
 */
abstract class BaseUseCase<R: Any> {
    protected val result = MutableLiveData<CallResult<R>>()
    fun observe() = result
}