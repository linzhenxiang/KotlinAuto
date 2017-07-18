package com.auto.bean

/**
 * 贴子
 */
open class Note {
    //贴子id
    var n_id: String? = null
    //用户id
    var u_id: String? = null
    // car brand id
    var brand_id: Int = 0
    var models_id: Int = 0
    var card_id: Int = 0
    //note title
    var title: String? = null
    //上牌时间
    var lis_time: Long = 0
    //车辆颜色
    var color: String? = null
    //行驶里程
    var mileage: Float = 0f
    //转让价格
    var price: Float = 0f
    //发布时间
    var rls_time: Long = 0
    //图集
    var imgs: List<String>? = null

    //商业险到期
    var crl_out: Long = 0
    //上牌地区
    var lis_area:String?=null
    //过户次数
    var trs_num:Int=0
    //联系人
    var u_name:String?=null

    //看车地点
    var look_area:String?=null
    //联系电话
    var tel:Int=0
    //销售状态
    var sl_state:Int=0
    //收藏次数
    var cl_num:Int=0
}