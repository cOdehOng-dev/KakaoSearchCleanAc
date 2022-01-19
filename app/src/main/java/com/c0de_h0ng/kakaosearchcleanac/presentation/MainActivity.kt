package com.c0de_h0ng.kakaosearchcleanac.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.c0de_h0ng.kakaosearchcleanac.R
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseActivity
import com.c0de_h0ng.kakaosearchcleanac.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val viewModel by viewModels<BlogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        thread(start=true) {
//            for (i in 1..5) {
//                Thread.sleep(1000)
//            }
//            viewModel.isReady = true
//        }
//        val content: View = findViewById(android.R.id.content)
//        content.viewTreeObserver.addOnPreDrawListener(
//            object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    // Check if the initial data is ready.
//                    return if (viewModel.isReady) {
//                        // The content is ready; start drawing.
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        // The content is not ready; suspend.
//                        false
//                    }
//                }
//            }
//        )
        viewModel.getBlogResultList("카카오톡")
    }

}