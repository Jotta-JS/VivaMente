package com.example.vivamente.connectionchecker

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionChecker(private val connectivityManager: ConnectivityManager?) {

    fun hasInternetConnection() : Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities: NetworkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        }else{
            val  activeNetworkInfo = connectivityManager?.activeNetworkInfo
            if (activeNetworkInfo != null){
                return activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
                        ||  activeNetworkInfo.type ==ConnectivityManager.TYPE_MOBILE
            }
            false
        }
    }

}