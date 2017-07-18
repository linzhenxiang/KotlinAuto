package com.bumptech.glide.samples.gallery

import android.app.Activity
import android.os.Bundle
import com.auto.R
import com.auto.widget.BData
import kotlinx.android.synthetic.main.activity_main_pager.*

// 热门推荐 最新发帖  精选视频


open class MainActivityA : Activity() {
    var urls = listOf("http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic7.nipic.com/20100518/3409334_031036048098_2.jpg", "http://pic58.nipic.com/file/20150112/12299514_224005339000_2.jpg", "http://pic23.nipic.com/20120828/10705080_162953428152_2.jpg", "http://pic33.nipic.com/20131009/13592298_171559693183_2.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pager)
        initViews()
    }

    fun initViews() {
        var data = ArrayList<BData>()
        urls.forEach {
            var b_data = BData()
            b_data.url = it
            data.add(b_data)
        }
    pager.setDataList(data)
    }


}
