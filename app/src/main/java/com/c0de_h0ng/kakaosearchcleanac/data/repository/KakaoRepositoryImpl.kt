package com.c0de_h0ng.kakaosearchcleanac.data.repository

import com.c0de_h0ng.kakaosearchcleanac.data.remote.datasource.KakaoRemoteDataSource
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
class KakaoRepositoryImpl @Inject constructor(
    private val remote: KakaoRemoteDataSource
) : KakaoRepository {

    override suspend fun getBlogResult(searchWord: String): BlogDto {
        return remote.getBlogResult(searchWord)
    }

    override fun getRxJavaBlogResult(searchWord: String): Flowable<BlogDto> {
        return remote.getRxJavaBlogResult(searchWord).subscribeOn(Schedulers.io())
    }

}