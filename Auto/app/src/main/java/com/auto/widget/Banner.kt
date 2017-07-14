package com.auto.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.samples.gallery.GlideHelp
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by lzx on 2017/7/11 0011.
 */
class Banner : FrameLayout, ViewPager.OnPageChangeListener {

    var mOldPosition = 0
    var mPagerMargin: Int = -1
    var mViewPager: ViewPager? = null
    var mBrList = mutableListOf<BrData>()
    var mViews = mutableListOf<ImageView>()
    var mPageTransformer: ViewPager.PageTransformer? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mViewPager = ViewPager(context)
        mViewPager?.addOnPageChangeListener(this)
        addView(mViewPager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
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


    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        var paint = Paint()
        paint.color = Color.RED
        canvas?.drawCircle(10.0f,10.0f,10.0f,paint)

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

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
                mViewPager?.offscreenPageLimit = mViews.size
                if (mPagerMargin > 0) mViewPager?.pageMargin = mPagerMargin
                if (mPageTransformer != null) mViewPager?.setPageTransformer(true, mPageTransformer)
                mViewPager?.adapter = BannerAdapter(mViews, mBrList)
                mViewPager?.currentItem = 1
            }
        }
    }





    /**
     * ViewPager 适配器
     */
    internal class BannerAdapter(var list: MutableList<ImageView>, val data: List<BrData>) : PagerAdapter() {


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
