package com.example.submission3belajarfundamentalaplikasiandroid.ui.activities

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.ActivitySplashscreenBinding
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.submission3belajarfundamentalaplikasiandroid.others.SettingPreferences
import com.example.submission3belajarfundamentalaplikasiandroid.others.ViewModelFactory
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.SettingVM


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SplashscreenActivity : AppCompatActivity() {

    private lateinit var bindingSplash : ActivitySplashscreenBinding
    //var themeS = SettingPreferences

    //private var pref = SettingPreferences.INSTANCE
    //val pref : Flow<Int> = context.dataStore.data

    /*fun saveThemeSetting(isDarkModeActive: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            pref?.saveThemeSetting(isDarkModeActive)
        }
    }*/
    //var prefs: SharedPreferences? = getSharedPreferences("settings", MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bindingSplash = ActivitySplashscreenBinding.inflate(layoutInflater)

        setContentView(bindingSplash.root)

        //val themeVal = prefs?.getBoolean("theme_setting", false)
        val pref = SettingPreferences.getInstance(dataStore)
        val settingVM = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingVM::class.java
        )

        // animasi progress bar
        val progressBar = bindingSplash.progressBar
        progressBar.max = 100
        val barAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100)
        barAnimation.duration = 3500

        //untuk mengubah tema
        settingVM.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })

        // start the progress bar animation
        barAnimation.start()

        val background = object : Thread(){
            override fun run(){
                try{
                    /* do some code here while loading */

                    /*switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                        settingVM.saveThemeSetting(isChecked)
                    }*/
                    sleep(3000)
                    // continue to the next activity
                    //val intentTheme = Intent(this@SplashscreenActivity, SettingActivity::class.java)

                    val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
        //val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
        //startActivity(intent)
        background.start()
    }
}