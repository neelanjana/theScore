package com.thescore.nbateamviewer.ui.home.viewholder

import android.view.ViewGroup
import com.thescore.data.model.TeamModel
import com.thescore.nbateamviewer.extensions.makeBold
import kotlinx.android.synthetic.main.layout_team_item.view.*

class ListViewHolder(override var layout: Int, parent: ViewGroup?) : BaseViewHolder(layout, parent) {

    fun bindData(model: TeamModel?) {

        itemView?.name?.text = model?.full_name
        itemView?.wins?.text = "Wins : ".makeBold().append(model?.wins?.toString())
        itemView?.losses?.text =
            "Losses : ".makeBold().append(model?.losses?.toString())
        itemView?.players?.text =
            "Players : ".makeBold().append(model?.players?.size?.toString())

        itemView?.setOnClickListener {
            it?.tag = model
            onClicked?.invoke(it)
        }

    }

}