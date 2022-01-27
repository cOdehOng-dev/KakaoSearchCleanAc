package com.c0de_h0ng.kakaosearchcleanac.domain.use_case

import com.c0de_h0ng.kakaosearchcleanac.common.base.SingleUseCase
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/26.
 * https://ghs-dev.tistory.com/m/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-MVVM-Clean-Architecture
 */
class GetSingleRxJavaBlogUseCase @Inject constructor(
    private val repository: KakaoRepository
) : SingleUseCase<String, BlogDto>() {

    override fun execute(t: String): Observable<BlogDto> {
        return repository.getRxJavaBlogSingleResult(t)
    }

}