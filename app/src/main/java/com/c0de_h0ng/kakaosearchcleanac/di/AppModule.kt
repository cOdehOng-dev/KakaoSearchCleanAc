package com.c0de_h0ng.kakaosearchcleanac.di

import com.c0de_h0ng.kakaosearchcleanac.common.Constants.BASE_URL
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.CONNECT_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.KAKAO_REST_API_KEY
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.READ_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.WRITE_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.PrettyHttpLogging
import com.c0de_h0ng.kakaosearchcleanac.data.remote.KakaoApi
import com.c0de_h0ng.kakaosearchcleanac.data.repository.KakaoRepositoryImpl
import com.c0de_h0ng.kakaosearchcleanac.domain.repository.KakaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(PrettyHttpLogging())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().apply {
                    addHeader("Authorization", KAKAO_REST_API_KEY)
                }.build())
            }
            .addInterceptor(interceptor)
            .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideKakaoApi(okHttpClient: OkHttpClient): KakaoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoRepository(api: KakaoApi): KakaoRepository {
        return KakaoRepositoryImpl(api)
    }
}