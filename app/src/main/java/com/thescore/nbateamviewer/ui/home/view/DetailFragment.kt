package com.thescore.nbateamviewer.ui.home.view

import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thescore.data.model.TeamModel
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.extensions.makeBold
import com.thescore.nbateamviewer.ui.base.BaseFragment
import com.thescore.nbateamviewer.ui.home.adapter.PlayerDetailAdapter
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recyclerView

class DetailFragment : BaseFragment() {

    private var adapter: PlayerDetailAdapter? = null

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PlayerDetailAdapter()
    }

    override fun viewReady() {

        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        recyclerView?.adapter = adapter
        var model = arguments?.getParcelable<TeamModel>("data")
        team_name?.text = model?.full_name
        team_details?.text =
            "Wins: ".makeBold().append(model?.wins?.toString()).append("   Losses: ".makeBold())
                .append(model?.losses?.toString()).append("   Players: ".makeBold())
                .append(model?.players?.size?.toString())
        adapter?.setData(model?.players)

        back?.setOnClickListener {
            onBackPressed()
        }

    }


    override fun getLayout(): Int {
        return R.layout.fragment_detail
    }


}