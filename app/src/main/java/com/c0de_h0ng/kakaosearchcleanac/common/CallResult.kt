package com.c0de_h0ng.kakaosearchcleanac.common

/**
 * Created by c0de_h0ng on 2022/01/31.
 */
sealed class CallResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : CallResult<T>()
    data class Error(val exception: String?, val errorCode: Int) : CallResult<Nothing>()
    data class Loading(val isLoading: Boolean) : CallResult<Nothing>()
}
