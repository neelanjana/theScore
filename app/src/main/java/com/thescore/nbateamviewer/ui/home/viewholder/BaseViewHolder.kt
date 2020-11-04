package com.thescore.nbateamviewer.ui.home.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thescore.nbateamviewer.extensions.inflate

abstract class BaseViewHolder(open var layout: Int, open var parent: ViewGroup?) :
    RecyclerView.ViewHolder(parent.inflate(layout)) {
    open var onClicked: ((view: View) -> Any)? = null

}