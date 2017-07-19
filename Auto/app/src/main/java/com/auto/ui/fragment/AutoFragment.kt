package com.auto.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auto.R
import com.auto.bean.AutoBean
import com.auto.bean.AutoItem

/**
 * Created by Administrator on 2017/7/19 0019.
 */
class AutoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fg_auto_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    internal class AutoAdapter(private var list: ArrayList<AutoBean>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            var view: View
            var viewHolder: RecyclerView.ViewHolder
            when (viewType) {
                1 -> {
                    view = LayoutInflater.from(parent!!.context).inflate(R.layout.fg_auto_item_banner_layout, parent, false)
                }
                2 -> {
                    view = LayoutInflater.from(parent!!.context).inflate(R.layout.fg_auto_item_title_layout, parent, false)

                }
                3 -> {
                    view = LayoutInflater.from(parent!!.context).inflate(R.layout.fg_auto_item_note_layout, parent, false)

                }
                4 -> {
                    view = LayoutInflater.from(parent!!.context).inflate(R.layout.fg_auto_item_news_layout, parent, false)

                }
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            return list?.size ?: 0
        }

        fun setList(list: List<AutoBean>) {
            this.list?.clear()
            this.list?.addAll(list)
            notifyItemRangeInserted(0, list.size)
        }

        override fun getItemViewType(position: Int): Int {
            return list?.get(position)?.type ?: super.getItemViewType(position)
        }


        class BannerHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

    }

    fun RecyclerView.ViewHolder.setData(bean: AutoBean) {
        
    }


    fun initData(): List<AutoItem> {
        var data = listOf<AutoItem>()
        (1..5).forEach {
            var item = AutoItem()
            val list = listOf<AutoBean>()
            (1..6).forEach {
                var bean = AutoBean()
                bean.id = "154"
                bean.coll_num = 102
                bean.img_url = "http://pic58.nipic.com/file/20150112/12299514_224005339000_2.jpg"
                bean.play_num = 4534
                bean.su_title = ""
                bean.title = "奔驰 E级 2016款 E 300 L 自动 豪华型-精品车况,支持检测,按揭"
                bean.type = 1
                list + bean
            }
            item.type = it //banner
            item.title = "热门推荐"
            item.data = list
            data + item
        }
        return data
    }
}