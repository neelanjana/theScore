package com.thescore.nbateamviewer.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thescore.data.model.TeamModel
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.extensions.*
import com.thescore.nbateamviewer.ui.base.BaseFragment
import com.thescore.nbateamviewer.ui.home.adapter.ListAdapter
import com.thescore.nbateamviewer.ui.home.vm.HomeListViewModel
import kotlinx.android.synthetic.main.fab_content.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeListFragment : BaseFragment(), View.OnClickListener {

    private var listAdapter: ListAdapter? = null

    private val viewModel: HomeListViewModel by lazy {
        ViewModelProvider(this).get(HomeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToViewModel()
        listAdapter = ListAdapter()
        listAdapter?.onClicked = this::onClick
        if (context != null) {
            viewModel.getData(context!!)
        }


    }


    override fun viewReady() {
        recyclerView?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = listAdapter

        fab?.setOnClickListener(this)
        sort_name.setOnClickListener(this)
        sort_win.setOnClickListener(this)
        sort_loss.setOnClickListener(this)

        if (viewModel?.data?.value == null) {
            showLoading(progressBar = loader)
        }

    }


    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    private fun subscribeToViewModel() {
        viewModel.data.subscribe(this) {
            hideLoading(progressBar = loader)
            if (it != null) {
                listAdapter?.setData(it)
            } else {
                showError("Something went wrong!", view!!)
            }

        }
    }

    override fun onClick(p0: View?) {
        var id = p0?.id
        when (id) {
            R.id.list_item -> {
                var bundle = Bundle()
                bundle.putParcelable("data", p0?.tag as TeamModel)
                var fragment = DetailFragment.newInstance(bundle)
                activity?.pushFragment(fragment)
            }
            R.id.fab -> {
                if (list_container?.isVisible() == true) list_container?.hide() else list_container?.visible()
            }
            R.id.sort_name -> {
                viewModel.sortList(1)
                list_container?.hide()
            }
            R.id.sort_win -> {
                viewModel.sortList(2)
                list_container?.hide()
            }
            R.id.sort_loss -> {
                viewModel.sortList(3)
                list_container?.hide()
            }
        }
    }

}