package com.c0de_h0ng.kakaosearchcleanac

import android.app.Application
import com.c0de_h0ng.kakaosearchcleanac.common.Constants.KAKAO_APP_KEY
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
@HiltAndroidApp
class KakaoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, KAKAO_APP_KEY)
    }

}