package com.thescore.nbateamviewer.ui.home.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.thescore.data.model.TeamModel
import com.thescore.data.repository.Repository
import com.thescore.nbateamviewer.ui.base.BaseViewModel

class HomeListViewModel : BaseViewModel() {
    var data = MutableLiveData<List<TeamModel>>()
    var repository: Repository? = Repository.instance()

    init {

        repository!!.mResponse?.observeForever { it ->
            data.postValue(it?.sortedWith(compareBy { it.full_name }))
        }
    }


    fun getData(context: Context) {
        repository?.makeAPICall(context)
    }

    fun sortList(pos: Int) {
        var list: List<TeamModel>? = null
        when (pos) {
            1 -> {
                list = data.value?.sortedWith(compareBy { it.full_name })
            }
            2 -> {
                list = data.value?.sortedWith(compareBy { it.wins })
            }
            3 -> {
                list = data.value?.sortedWith(compareBy { it.losses })
            }

        }
        data?.postValue(list)
    }

    override fun onCleared() {
        super.onCleared()
        repository!!.mResponse.removeObserver { this }
    }


}