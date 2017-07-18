package com.auto.bean

/**
 * Created by Administrator on 2017/7/18 0018.
 */
class AutoBean {

    //贴子id,视频id
    var id: String? = null

    //贴子标题，视频标题
    var title: String? = null

    //贴子副标题，视频副标题
    var su_title: String? = null

    //贴子/视频图片标题
    var img_url: String? = null

    //1:贴子 2:视频
    var type: Int = 0

    //播放数
    var play_num: Int = 0

    //收藏数
    var coll_num: Int = 0

}