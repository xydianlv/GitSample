package com.example.base.manager

import android.content.Context
import android.content.SharedPreferences

enum class PreferencesManager {
    INSTANCE;

    companion object {

        private const val NORMAL_MODE = Context.MODE_PRIVATE

        private const val NAME_ACCOUNT = "account"
        private const val NAME_COMMON = "common"
    }

    private var preferencesAccount: SharedPreferences? = null
    private var preferencesCommon: SharedPreferences? = null

    private fun getSharedPreferences(name: String): SharedPreferences {
        return AnalyticManager.manager.appContext()!!.getSharedPreferences(name, NORMAL_MODE)
    }

    fun commonSharedPreferences(): SharedPreferences {
        if (preferencesCommon == null) {
            preferencesCommon = getSharedPreferences(NAME_COMMON)
        }
        return preferencesCommon!!
    }

    fun accountSharedPreferences(): SharedPreferences {
        if (preferencesAccount == null) {
            preferencesAccount = getSharedPreferences(NAME_ACCOUNT)
        }
        return preferencesAccount!!
    }
}