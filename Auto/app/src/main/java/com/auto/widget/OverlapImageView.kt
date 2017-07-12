package com.auto.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Administrator on 2017/7/12 0012.
 */
class OverlapImageView : ImageView {
    var paint = Paint()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    fun initView() {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        //底层背景
        var rect = Rect((width * 0.1f).toInt(), 0, (width * 0.9f).toInt(), (height * 0.04f).toInt())
        paint.color = Color.parseColor("#BFD9D9D9")
        canvas?.drawRect(rect, paint)

        //中间层背景
        var rect2 = Rect((width * 0.04f).toInt(), (height * 0.04f).toInt(), (width * 0.96f).toInt(), (height * 0.09f).toInt())
        paint.color = Color.parseColor("#D0ABABAB")
        canvas?.drawRect(rect2, paint)

        //顶层图片
        drawable.setBounds(0, (height * 0.08f).toInt(), width, (height * 0.92f).toInt())
        drawable.draw(canvas)
    }

}