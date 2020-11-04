package com.thescore.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.thescore.data.model.TeamModel
import com.thescore.data.networking.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    var mResponse = MutableLiveData<List<TeamModel>>()
    private lateinit var service: RetrofitService

    companion object {
        private var instance: Repository? = null
        fun instance(): Repository? {
            if (instance == null) {
                instance = synchronized(this) { Repository() }
            }
            return instance
        }
    }

    fun makeAPICall(context: Context) {
        service = RetrofitService.create(context)
        service.getMovies()?.enqueue(object : Callback<List<TeamModel>> {
            override fun onResponse(
                call: Call<List<TeamModel>>?,
                response: Response<List<TeamModel>>?
            ) {
                mResponse?.postValue(response?.body())
            }

            override fun onFailure(call: Call<List<TeamModel>>?, t: Throwable?) {
                mResponse?.postValue(null)
            }
        })

    }


}


