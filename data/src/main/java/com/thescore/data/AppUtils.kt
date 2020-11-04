package com.thescore.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import java.io.File

object AppUtils {
    const val cacheSize = (5 * 1024 * 1024).toLong()
    var BASE_URL = "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/"
    var sampleSuccessResponse = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"full_name\": \"Boston Celtics\",\n" +
            "    \"wins\": 45,\n" +
            "    \"losses\": 20,\n" +
            "    \"players\": [\n" +
            "      {\n" +
            "        \"id\": 37729,\n" +
            "        \"first_name\": \"Kadeem\",\n" +
            "        \"last_name\": \"Allen\",\n" +
            "        \"position\": \"SG\",\n" +
            "        \"number\": 45\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"

    var onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }


    fun isInternetAvailable(context: Context?): Boolean {
        var result = false
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }


}