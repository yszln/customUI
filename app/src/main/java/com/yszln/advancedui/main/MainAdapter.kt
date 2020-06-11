package com.yszln.advancedui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yszln.advancedui.R

/**
 * @fileName: MainAdapter
 * @author: huwei
 * @date: 2020/6/11 15:53
 * @description:
 * @history:
 */
class MainAdapter : RecyclerView.Adapter<MainAdapter.VH>() {

    private var mDatas = ArrayList<MainItemBean>()
    private var onItemClickListener: OnItemClickListener? = null

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var textView = itemView.findViewById<TextView>(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = View.inflate(parent.context, R.layout.item_main, null)
        return VH(itemView)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = mDatas[position].name
        holder.imageView.setImageResource(mDatas[position].img)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(position, mDatas[position], holder)
        }
    }

    fun addData(list: ArrayList<MainItemBean>) {
        mDatas.addAll(list)
        notifyItemRangeChanged(mDatas.size - list.size, mDatas.size)
    }

    fun addData(item: MainItemBean) {
        mDatas.add(item)
        notifyItemRangeChanged(mDatas.size - 1, mDatas.size)
    }

    fun removeAllData() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    fun getDataByPosition(position: Int): MainItemBean {
        return mDatas[position]
    }

    interface OnItemClickListener {
        fun onClick(position: Int, item: MainItemBean, holder: VH)
    }
    public fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}