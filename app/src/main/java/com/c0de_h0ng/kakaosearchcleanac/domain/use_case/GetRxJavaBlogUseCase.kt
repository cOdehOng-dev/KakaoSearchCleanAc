package com.c0de_h0ng.kakaosearchcleanac.domain.use_case

import android.util.Log
import com.c0de_h0ng.kakaosearchcleanac.common.Resource
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseUseCase
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/26.
 */
class GetRxJavaBlogUseCase @Inject constructor(
    private val repository: KakaoRepository
) : BaseUseCase<BlogDto>() {

    operator fun invoke(searchWord: String): Disposable {
        return repository.getRxJavaBlogResult(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                //result.value = Resource.Loading(null, null, true)
            }
            .doOnTerminate {
                //result.value = Resource.Loading(true)
            }
            .subscribe({
                Log.d("Result >>> ", "Success")
                result.value = Resource.Success(it)
            }, {
                Log.d("Result >>> ", it.localizedMessage ?: "An unexpected error occured")
                result.value = Resource.Error(it.localizedMessage ?: "An unexpected error occured")
            })
    }

}