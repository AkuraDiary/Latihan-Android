package com.example.submission3belajarfundamentalaplikasiandroid.ui.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.others.SettingPreferences
import com.example.submission3belajarfundamentalaplikasiandroid.others.ViewModelFactory
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.SettingVM
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingActivity : AppCompatActivity() {
    //creating dataStore
    private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_setting)


        //untuk mengubah tema
        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)!!

        val pref = SettingPreferences.getInstance(dataStore)
        val settingVM = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingVM::class.java
        )

        Log.d("datastore " , dataStore.toString())
        Log.d("preference " , pref.toString())

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingVM.saveThemeSetting(isChecked)
        }

        settingVM.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })
    }
}

