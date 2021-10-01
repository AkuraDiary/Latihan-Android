package com.example.submission3belajarfundamentalaplikasiandroid.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.ActivityMainBinding
import com.example.submission3belajarfundamentalaplikasiandroid.others.SettingPreferences
import com.example.submission3belajarfundamentalaplikasiandroid.others.ViewModelFactory
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.SettingVM
import com.google.android.material.switchmaterial.SwitchMaterial
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        //navController = findNavController(R.id.nav_host_fragment_container)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)


        appBarConfiguration = AppBarConfiguration(navController.graph)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.details_destination -> binding.bottomNavView.visibility = View.GONE
                else -> binding.bottomNavView.visibility = View.VISIBLE
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        //setupActionBarWithNavController(navController, appBarrConfiguration)
        /*ViewModelProvider(this).get(DetailsVM::class.java)//inisialisasi detail vm*/

        //untuk mengubah tema
        //val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)!!

        /*val pref = SettingPreferences.getInstance(dataStore)
        val settingVM = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingVM::class.java
        )*/

        /*switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingVM.saveThemeSetting(isChecked)
        }*/

        /*settingVM.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    //switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    //switchTheme.isChecked = false
                }
            })*/
    }


    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp(appBarConfiguration)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //menuju pengaturan bahasa
    override fun onOptionsItemSelected(item:MenuItem): Boolean{
        if(item.itemId == R.id.menu_lang_setting){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }else if(item.itemId == R.id.menu_theme_settings){
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}