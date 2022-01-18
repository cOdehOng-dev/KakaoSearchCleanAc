package com.c0de_h0ng.kakaosearchcleanac.domain.repository

import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.BlogDto

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
interface KakaoRepository {

    suspend fun getBlogResult(searchWord: String): BlogDto

}