package com.c0de_h0ng.kakaosearchcleanac.domain.use_case

import android.util.Log
import com.c0de_h0ng.kakaosearchcleanac.common.CallResult
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseUseCase
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/26.
 */
class GetRxBlogUseCase @Inject constructor(
    private val repository: KakaoRepository
) : BaseUseCase<BlogDto>() {

    operator fun invoke(searchWord: String): Disposable {
        return repository.getRxJavaBlogResult(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                result.value = CallResult.Loading(true)
            }
            .doOnTerminate {
                result.value = CallResult.Loading(false)
            }
            .subscribe({
                Log.d("Result >>> ", "Success")
                result.value = CallResult.Success(it)
            }, {
                Log.d("Result >>> ", it.localizedMessage ?: "An unexpected error occured")
                result.value = CallResult.Error(it.localizedMessage ?: "An unexpected error occured", 400)
            })
    }

}