package com.c0de_h0ng.kakaosearchcleanac.domain.usecase

import com.c0de_h0ng.kakaosearchcleanac.common.CallResult
import com.c0de_h0ng.kakaosearchcleanac.data.remote.dto.blog.toBlog
import com.c0de_h0ng.kakaosearchcleanac.domain.model.Blog
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
class GetBlogUseCase @Inject constructor(
    private val repository: KakaoRepository
) {

    operator fun invoke(searchWord: String): Flow<CallResult<List<Blog>>> = flow {
        try {
            val blog = repository.getBlogResult(searchWord).toBlog()
            emit(CallResult.Success(blog))
        } catch (e: HttpException) {
            emit(CallResult.Error(e.localizedMessage ?: "An unexpected error occured", 400))
        } catch (e: IOException) {
            emit(CallResult.Error("Couldn't reach server. Check your internet", 400))
        } finally {
            emit(CallResult.Loading(isLoading = false))
        }
    }

}