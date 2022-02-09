package com.c0de_h0ng.kakaosearchcleanac.di.module

import com.c0de_h0ng.kakaosearchcleanac.common.Constants.CONNECT_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.READ_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.WRITE_TIMEOUT
import com.c0de_h0ng.kakaosearchcleanac.common.PrettyHttpLogging
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by c0de_h0ng on 2022/02/09.
 */

abstract class NetworkModule {

    @NotNull
    fun getClientBuilder(baseUrl: String? = null): Retrofit.Builder {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClientModule.provideOkHttpClient())
            .apply {
                baseUrl?.let {
                    this.baseUrl(it)
                }
            }
    }

}

@Module
object OkHttpClientModule {

    @Provides
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(PrettyHttpLogging())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}