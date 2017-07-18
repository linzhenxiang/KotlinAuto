package com.auto.bean

/**
 * Created by Administrator on 2017/7/18.
 */
class AutoItem {
    //类型标题
    var title: String? = null
    //数据类型 1:banner 1:热帖推荐 ,2:精选视频  3:新帖发布 4:最新资讯 5:商家发帖
    var type: Int = 0
    //数据列表
    var data: List<AutoBean>? = null
}