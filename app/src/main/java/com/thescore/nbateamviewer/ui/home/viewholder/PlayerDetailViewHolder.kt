package com.thescore.nbateamviewer.ui.home.viewholder

import android.view.ViewGroup
import com.thescore.data.model.Players
import com.thescore.nbateamviewer.extensions.makeBold
import kotlinx.android.synthetic.main.layout_player_item.view.*
import kotlinx.android.synthetic.main.layout_team_item.view.name

class PlayerDetailViewHolder(override var layout: Int, parent: ViewGroup?) :
    BaseViewHolder(layout, parent) {

    fun bindData(model: Players?) {
        itemView?.name?.text = model?.first_name + " " + model?.last_name
        itemView?.position?.text = "Position : ".makeBold().append(model?.position)
        itemView?.number?.text =
            "Number : ".makeBold().append(model?.number?.toString())

    }

}