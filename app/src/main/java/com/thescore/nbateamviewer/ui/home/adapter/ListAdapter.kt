package com.thescore.nbateamviewer.ui.home.adapter

import android.view.ViewGroup
import com.thescore.data.model.TeamModel
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.ui.home.viewholder.BaseViewHolder
import com.thescore.nbateamviewer.ui.home.viewholder.ListViewHolder

class ListAdapter : BaseAdapter() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder = ListViewHolder(R.layout.layout_team_item, parent)
        holder?.onClicked = onClicked
        return holder

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        (holder as? ListViewHolder?)?.bindData(dataList?.get(position) as TeamModel)
    }
}