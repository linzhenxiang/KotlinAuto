package com.auto.bean


/**
 * Created by Administrator on 2017/7/18 0018.
 */
data class AutoBean(var id: String? = null, var title: String? = null, var su_title: String? = null, var img_url: String? = null, var type: Int = 0, var play_num: Int = 0, var coll_num: Int = 0)

data class AutoItem(var title: String? = null, var type: Int = 0, var data: List<AutoBean>? = null)

data class AutoResult(var code: Int = 0, var data: List<AutoBean>? = null)