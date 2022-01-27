package com.c0de_h0ng.kakaosearchcleanac.data.repository

import com.c0de_h0ng.kakaosearchcleanac.data.remote.KakaoApi
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
class KakaoRepositoryImpl @Inject constructor(
    private val api: KakaoApi
) : KakaoRepository {

    override suspend fun getBlogResult(searchWord: String): BlogDto {
        return api.getBlogResult(searchWord)
    }

    override fun getRxJavaBlogResult(searchWord: String): Observable<BlogDto> {
        return api.getRxJavaBlogResult(searchWord)
    }

    override fun getRxJavaBlogSingleResult(searchWord: String): Single<BlogDto> {
        return api.getSingleRxJavaBlogResult(searchWord)
    }


}