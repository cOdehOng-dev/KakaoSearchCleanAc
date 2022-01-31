package com.c0de_h0ng.kakaosearchcleanac.data.remote.datasource

import com.c0de_h0ng.kakaosearchcleanac.data.remote.KakaoApi
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/31.
 */
class KakaoRemoteDataSourceImpl @Inject constructor(
    private val api: KakaoApi
) : KakaoRemoteDataSource {

    override suspend fun getBlogResult(searchWord: String): BlogDto {
        return api.getBlogResult(searchWord)
    }

    override fun getRxJavaBlogResult(searchWord: String): Observable<BlogDto> {
        return api.getRxJavaBlogResult(searchWord)
    }

}