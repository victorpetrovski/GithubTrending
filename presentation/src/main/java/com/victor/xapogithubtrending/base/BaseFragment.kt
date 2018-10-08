package com.victor.xapogithubtrending.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.xapogithubtrending.di.extension.Injectable

/**
 * Created by victor on 10/7/18
 */
abstract class BaseFragment : Fragment(), Injectable {

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.v(tag,"onCreateView")
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag,"onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag,"onPause")
    }

    abstract fun setupViews(savedInstanceState: Bundle?)

}