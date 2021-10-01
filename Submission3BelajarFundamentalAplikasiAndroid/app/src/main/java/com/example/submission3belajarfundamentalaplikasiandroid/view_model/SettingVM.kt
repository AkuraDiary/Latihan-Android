package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission3belajarfundamentalaplikasiandroid.others.SettingPreferences
import kotlinx.coroutines.*

class SettingVM(private val pref: SettingPreferences) : ViewModel(){
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}