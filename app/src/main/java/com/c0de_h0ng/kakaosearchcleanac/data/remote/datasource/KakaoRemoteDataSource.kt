package com.c0de_h0ng.kakaosearchcleanac.data.remote.datasource

import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import io.reactivex.Observable

/**
 * Created by c0de_h0ng on 2022/01/31.
 */
interface KakaoRemoteDataSource {
    suspend fun getBlogResult(searchWord: String): BlogDto
    fun getRxJavaBlogResult(searchWord: String): Observable<BlogDto>
}