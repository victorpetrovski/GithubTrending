package com.victor.xapogithubtrending.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.victor.xapogithubtrending.di.extension.Injectable

/**
 * Created by victor on 10/7/18
 */
abstract  class BaseActivity : AppCompatActivity() , Injectable {

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setupViews(savedInstanceState)
    }

    abstract fun setupViews(savedInstanceState: Bundle?)

    fun parentLayout() = findViewById<View>(android.R.id.content)
}