package com.bumptech.glide.samples.gallery

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.lang.ref.WeakReference

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class MyAdapter : PagerAdapter() {

    var viewMaps: List<WeakReference<ImageView>>? = null

    fun setMap(list: List<WeakReference<ImageView>>) {
        viewMaps = list

    }

    override fun getCount(): Int {
        return viewMaps!!.size
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        if (viewMaps!![position].get() != null)
            container?.removeView(viewMaps!![position].get())
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        GlideHelp.load(container?.context, "", viewMaps!![position].get())
        container?.addView(viewMaps!![position].get())
        return viewMaps!![position].get()!!
    }
}