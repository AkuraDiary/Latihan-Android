package com.example.submission2belajarfundamentalaplikasiandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.ActivityMainBinding
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.DetailsVM
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.HomeVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarrConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        //navController = findNavController(R.id.nav_host_fragment_container)

        appBarrConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarrConfiguration)

        var detailVMInit = ViewModelProvider(this).get(DetailsVM::class.java)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp(appBarrConfiguration)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //menuju pengaturan bahasa
    override fun onOptionsItemSelected(item:MenuItem): Boolean{
        if(item.itemId == R.id.menu_setting){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}