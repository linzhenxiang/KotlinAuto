package com.auto.widget.banner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.samples.gallery.GlideHelp
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *循环的ViewPager
 */
class Banner : FrameLayout, BViewPager.OnPageChangeListener {

    private var mOldPosition = 0
    var mPagerMargin: Int = -1
    private var mViewPager: BViewPager? = null
    private var mBrList = mutableListOf<BrData>()
    private var mViews = mutableListOf<ImageView>()
    var mPageTransformer: BViewPager.PageTransformer? = null
    var mIndicatorPaddingBottom: Int = 0
    var mIndicatorMiddlePadding = 10.0f
    private var mIndicatorPaint: Paint? = null
    var mIndicatorRadius = 10.0f //半径
    var mIndicatorSelectedColor = Color.BLUE
    var mIndicatorDefaultColor = Color.GRAY
    var mScrollSpeed = 4.0f
    val mScrollTimeout = 3000L
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            Log.e("EE", "_____________________AAAAAAAA")
            if (msg?.what == 1) {
                removeMessages(1)
                mViewPager?.setScrollSpeed(mScrollSpeed)
                mViewPager?.setCurrentItem(mViewPager!!.currentItem + 1, true)
                sendEmptyMessageDelayed(1, mScrollTimeout)
            }
        }

    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mIndicatorPaint = Paint()
        mIndicatorPaint?.isAntiAlias = true
        mViewPager = BViewPager(context)
        mViewPager?.addOnPageChangeListener(this)
        mViewPager?.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mViewPager?.setScrollSpeed(1.0f)
                    mHandler.removeMessages(1)
                }
                MotionEvent.ACTION_UP ->
                    mHandler.sendEmptyMessageDelayed(1, mScrollTimeout)
            }
            false
        }
        addView(mViewPager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }


    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) mHandler.sendEmptyMessageDelayed(1, mScrollTimeout) else mHandler.removeMessages(1)
    }


    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
        if (screenState == View.SCREEN_STATE_OFF) mHandler.removeMessages(1) else mHandler.sendEmptyMessageDelayed(1, mScrollTimeout)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            mOldPosition = mViewPager!!.currentItem
            if (mOldPosition == 0) mViewPager!!.setCurrentItem(mViews.size - 2, false)
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position > mOldPosition && Math.abs(position - mOldPosition) == 1) {
            if (position == mViews.size - 1) {
                mViewPager?.setCurrentItem(1, false)
            } else if (position == 0) {
                mViewPager?.setCurrentItem(mViews.size - 2, false)
            }
        } else if (position < mOldPosition) {
            if (position == 0) {
                mViewPager?.setCurrentItem(mViews.size - 1, false)
            }
        }
        mOldPosition = position

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mHandler?.sendEmptyMessageDelayed(1, mScrollTimeout)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        drawDot(canvas)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 绘制游标
     */
    fun drawDot(canvas: Canvas?) {
        (0..mViewPager!!.childCount - 3).forEach {
            if (it == mViewPager!!.currentItem - 1) mIndicatorPaint?.color = mIndicatorSelectedColor else mIndicatorPaint?.color = mIndicatorDefaultColor
            canvas?.drawCircle(dotLeft() + it * (2 * mIndicatorRadius + mIndicatorMiddlePadding), dotHeight(), mIndicatorRadius, mIndicatorPaint)
        }
    }

    /**
     * 游标左侧圆心x 坐标
     */
    fun dotLeft(): Float = (measuredWidth - mIndicatorMiddlePadding * (mViewPager!!.childCount - 3) - (2 * (mViewPager!!.childCount - 2) - 1) * mIndicatorRadius) * 0.5f

    fun dotHeight(): Float = measuredHeight * 0.9f + mIndicatorPaddingBottom

    /**
     * 添加数据
     */
    fun setDataList(data: List<BrData>) {
        doAsync {
            data.forEachIndexed { index, _ ->
                mViews.add(ImageView(context))
                if (index == data.size - 1) {
                    mViews.add(ImageView(context))
                    mViews.add(0, ImageView(context))
                }
            }
            mBrList.addAll(data)
            mBrList.add(data[0])
            mBrList.add(0, data[data.size - 1])
            uiThread {
                with(mViewPager!!) {
                    setScrollSpeed(4.0f)
                    offscreenPageLimit = mViews.size
                    if (mPagerMargin > 0) pageMargin = mPagerMargin
                    if (mPageTransformer != null) setPageTransformer(true, mPageTransformer)
                    adapter = com.auto.widget.banner.Banner.BannerAdapter(mViews, mBrList)
                    currentItem = 1
                }

            }
        }
    }


    /**
     * ViewPager 适配器
     */
    internal class BannerAdapter(var list: MutableList<ImageView>, val data: List<BrData>) : BPagerAdapter() {


        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            container?.removeView(list[position])
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            GlideHelp.load(container?.context, data[position].getBrUrl(), list!![position])
            container?.addView(list[position])
            return list[position]
        }


    }


    /**
     * Banner 数据对象,需要实现此接口
     */
    interface BrData {
        fun getBrUrl(): String?
        fun getBrTitle(): String?
        fun getBrSuTitle(): String?
    }

}
