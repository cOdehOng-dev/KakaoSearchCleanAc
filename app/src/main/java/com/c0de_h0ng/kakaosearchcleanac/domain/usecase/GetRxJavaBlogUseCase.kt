package com.c0de_h0ng.kakaosearchcleanac.domain.usecase

import com.c0de_h0ng.kakaosearchcleanac.common.base.UseCase
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
class GetRxJavaBlogUseCase @Inject constructor(
    private val repository: KakaoRepository
) : UseCase<String, BlogDto>() {

    override fun buildUseCaseFlowable(param: String): Flowable<BlogDto> {
        return repository.getRxJavaBlogResult(param).subscribeOn(Schedulers.io())
    }

}