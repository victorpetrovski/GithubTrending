package com.victor.xapogithubtrending.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.victor.xapogithubtrending.R
import kotlinx.android.synthetic.main.repository_info_cell.view.*

/**
 * Created by victor on 10/8/18
 */
class RepositoryInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)  {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.repository_info_cell, this, true)
    }

    fun setTextValue ( text : String){
        tv_value.text = text
    }

    fun setTextInfo ( resId : Int, textInfo : String ){
        tv_info.text = textInfo
        iv_info.setImageResource(resId)
    }

    fun setValue ( resId : Int, textInfo : String, textValue : String ){
        setTextInfo(resId,textInfo)
        setTextValue(textValue)
    }

}