package com.c0de_h0ng.kakaosearchcleanac.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
abstract class BaseActivity<D: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)

    }
}