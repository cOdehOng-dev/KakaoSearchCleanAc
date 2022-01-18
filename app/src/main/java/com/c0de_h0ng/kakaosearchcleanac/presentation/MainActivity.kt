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
        viewModel.getBlogResultList("카카오톡")
    }

}