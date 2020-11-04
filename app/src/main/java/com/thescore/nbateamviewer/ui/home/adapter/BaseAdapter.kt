package com.thescore.nbateamviewer.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thescore.nbateamviewer.ui.home.viewholder.BaseViewHolder

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var dataList: List<*>? = null
    open var onClicked: ((view: View) -> Any)? = null

    abstract fun onBindView(holder: BaseViewHolder, position: Int)

    fun setData(list: List<*>?) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindView(holder, position)
    }

}
