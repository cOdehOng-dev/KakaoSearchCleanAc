package com.c0de_h0ng.kakaosearchcleanac.domain.use_case

import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import javax.inject.Inject

class GetRxJavaUseCase @Inject constructor(
    private val repository: KakaoRepository
) {

    operator fun invoke(searchWord: String) = repository.getRxJavaBlogResult(searchWord)
}