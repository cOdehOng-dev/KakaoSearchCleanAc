package com.c0de_h0ng.kakaosearchcleanac.presentation

import android.os.Bundle
import android.util.Log
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

        observeViewModel()
        //viewModel.getBlogResultList("카카오톡")
        //viewModel.getRxJavaBlogResult("카카오톡")
        //viewModel.getRxJavaBlogResult("카카오톡")

        viewModel.getRxJavaSingleBlogResult("카카오톡")
        //viewModel.getRxJavaBlog("카카오톡")
    }


    private fun observeViewModel() {
        viewModel.rxJavaSingleBlogList.observe(this) {
            Log.d("Blog Result >>> ", it.size.toString())
            for (i in it.indices) {
                Log.d("Blog Result >>> ", it[i].blogName)
            }
        }

        viewModel.blogList.observe(this) {
            for (i in it.indices) {
                Log.d("Blog Result >>> ", it[i].blogName)
            }
        }
    }



}