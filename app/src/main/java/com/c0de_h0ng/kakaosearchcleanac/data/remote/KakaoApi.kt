package com.c0de_h0ng.kakaosearchcleanac.data.remote

import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
interface KakaoApi {

    @GET("/v2/search/blog")
    suspend fun getBlogResult(@Query("query") searchWord: String): BlogDto
}