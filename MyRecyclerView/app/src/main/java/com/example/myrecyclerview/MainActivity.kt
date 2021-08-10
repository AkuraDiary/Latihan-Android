package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Hero>()
    private var title = "mode list"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
        setActionBarTitle(title)
    }

    fun getListHeroes():ArrayList<Hero>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()

        for (position in dataName.indices){
            val hero = Hero(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listHero.add(hero)
        }

        return listHero
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    private fun showRecyclerList(){
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        binding.rvHeroes.adapter = listHeroAdapter
    }

    private fun showRecyclerGrid(){
        binding.rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        binding.rvHeroes.adapter = gridHeroAdapter
    }

    private fun showRecyclerCardView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        binding.rvHeroes.adapter = cardViewHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode:Int){
        when(selectedMode){
            R.id.action_list ->{
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid ->{
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview ->{
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }
}