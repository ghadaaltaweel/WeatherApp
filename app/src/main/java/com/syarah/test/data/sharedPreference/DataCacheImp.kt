package com.syarah.test.data.sharedPreference

import android.content.SharedPreferences
import com.syarah.test.core.DataCache
import javax.inject.Inject

class DataCacheImp @Inject constructor(
    private var sharedPref: SharedPreferences

):DataCache {
    private val TOKEN = "token"

    init {
        sharedPref.edit().putString(TOKEN, "c45719be9dea179c84566fc2b4e02db4").apply()
    }
    override fun getToken(): String {
        return sharedPref.getString(TOKEN, "") ?: ""
    }

}