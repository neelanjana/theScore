package com.thescore.nbateamviewer.ui.home.adapter

import android.view.ViewGroup
import com.thescore.data.model.Players
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.ui.home.viewholder.BaseViewHolder
import com.thescore.nbateamviewer.ui.home.viewholder.PlayerDetailViewHolder

class PlayerDetailAdapter : BaseAdapter() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder = PlayerDetailViewHolder(R.layout.layout_player_item, parent)
        holder?.onClicked = onClicked
        return holder

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        (holder as? PlayerDetailViewHolder?)?.bindData(dataList?.get(position) as Players)
    }
}