package com.c0de_h0ng.kakaosearchcleanac.data.remote

import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
interface KakaoApi {

    //Coroutine + Flow
    @GET("/v2/search/blog")
    suspend fun getBlogResult(
        @Query("query") searchWord: String
    ): BlogDto

    //RxJava
    @GET("/v2/search/blog")
    fun getRxJavaBlogResult(
        @Query("query") searchWord: String
    ): Observable<BlogDto>

    //RxJava(Single)
    @GET("/v2/search/blog")
    fun getSingleRxJavaBlogResult(
        @Query("query") searchWord: String
    ): Single<BlogDto>
}