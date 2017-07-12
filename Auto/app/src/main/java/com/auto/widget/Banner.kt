package com.auto.widget

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
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
class Banner : FrameLayout {

    var mViewPager: ViewPager? = null
    var list = mutableListOf<ImageView>()
    var oldPosition = 0
    var mData = mutableListOf<BData>()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews()
    }

    fun initViews() {
        mViewPager = ViewPager(context)
        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    var position = mViewPager?.currentItem
                    oldPosition = mViewPager?.currentItem ?: 0

                    Log.e("EE", "_____A  position:$position?SCROLL_STATE_IDLE")
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.e("EE", "_____B  position:$position?positionOffset:$positionOffset:item:${mViewPager?.currentItem}+$oldPosition")

                if (position > oldPosition) {
                    if (position == list.size - 1) {
                        mViewPager?.setCurrentItem(1, false)
                    } else if (position == 0) {
                        mViewPager?.setCurrentItem(list.size - 2, false)
                    }
                }
                oldPosition = position

            }

            override fun onPageSelected(position: Int) {
                Log.e("EE", "_____onPageSelected:$position?SCROLL_STATE_IDLE")

            }

        })
        addView(mViewPager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }


    fun setDataList(data: List<BData>) {
        doAsync {
            data.forEachIndexed { index, _ ->
                list.add(ImageView(context))
                if (index == data.size - 1) {
                    list.add(ImageView(context))
                    list.add(0, ImageView(context))
                }
            }
            mData.addAll(data)
            mData.add(data[0])
            mData.add(0, data[data.size - 1])
            uiThread {
                mViewPager?.currentItem = list.size - 2
                mViewPager?.offscreenPageLimit = list.size
                mViewPager?.adapter = BannerAdapter(list, mData)
            }
        }
    }


    internal class BannerAdapter(var list: MutableList<ImageView>, val data: List<BData>) : PagerAdapter() {


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
            GlideHelp.load(container?.context, data[position].url, list!![position])
            container?.addView(list[position])
            return list[position]
        }


    }


}
