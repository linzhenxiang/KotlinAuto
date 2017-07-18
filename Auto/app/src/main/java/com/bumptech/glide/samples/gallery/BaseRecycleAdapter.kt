package com.bumptech.glide.samples.gallery

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.auto.widget.BData

/**
 * Created by Administrator on 2017/7/17 0017.
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<T>? = null


abstract fun OnClick(view: RecyclerView)

    fun setData(list: List<T>) {
        data?.clear()
        data?.addAll(list)
        notifyDataSetChanged()
    }

    class MyAdayter:BaseRecyclerAdapter<BData>(){
        override fun OnClick(view: RecyclerView) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

}



