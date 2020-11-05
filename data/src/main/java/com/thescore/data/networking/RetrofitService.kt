package com.thescore.data.networking

import android.content.Context
import com.thescore.data.AppUtils
import com.thescore.data.AppUtils.onlineInterceptor
import com.thescore.data.model.TeamModel
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitService {

    @GET("input.json")
    fun getMovies(): Call<List<TeamModel>>

    companion object {
        fun create(context: Context): RetrofitService {

            var offlineInterceptor = Interceptor { chain ->
                var request: Request = chain.request()
                if (!AppUtils.isInternetAvailable(context)) {
                    val maxStale = 60 * 60 * 24
                    request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .removeHeader("Pragma")
                        .build()
                }
                chain.proceed(request)
            }

            var cache = Cache(context?.cacheDir, AppUtils.cacheSize)
            val okHttpClient =
                OkHttpClient.Builder()
                    .addNetworkInterceptor(onlineInterceptor)
                    .addInterceptor(offlineInterceptor)
                    .cache(cache)
                    .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppUtils.BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(RetrofitService::class.java)

        }


    }


}