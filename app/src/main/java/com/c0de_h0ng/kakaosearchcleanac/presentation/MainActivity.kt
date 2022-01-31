package com.c0de_h0ng.kakaosearchcleanac.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.c0de_h0ng.kakaosearchcleanac.R
import com.c0de_h0ng.kakaosearchcleanac.common.base.BaseActivity
import com.c0de_h0ng.kakaosearchcleanac.databinding.ActivityMainBinding
import com.c0de_h0ng.kakaosearchcleanac.presentation.blog.BlogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val viewModel by viewModels<BlogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
        viewModel.getBlogResultList("카카오톡")
        //viewModel.getRxJavaSingleBlogResult("카카오톡")
    }


    private fun observeViewModel() {
        with(viewModel) {
            rxBlogList.observe(this@MainActivity) {
                Log.d("Blog Result2 >>> ", it.size.toString())
                for (i in it.indices) {
                    Log.d("Blog Result2 >>> ", it[i].blogName)
                }
            }

            blogList.observe(this@MainActivity) {
                for (i in it.indices) {
                    Log.d("Blog Result >>> ", it[i].blogName)
                }
            }

            isLoadingObservable.observe(this@MainActivity) {
                when {
                    it -> showLoadingDialog()
                    else -> hideLoadingDialog()
                }
            }
        }
    }

}