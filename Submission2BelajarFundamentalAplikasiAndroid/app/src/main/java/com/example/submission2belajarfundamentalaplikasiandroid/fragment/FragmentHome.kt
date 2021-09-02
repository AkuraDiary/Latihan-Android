package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.adapter.UserAdapter
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding
import com.example.submission2belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission2belajarfundamentalaplikasiandroid.others.MyStates
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.HomeVM

class FragmentHome: Fragment() {

    private lateinit var bindingHome: FragmentHomeBinding
    private lateinit var homeAdapter : UserAdapter
    private lateinit var homeVM : HomeVM
    private var showStates = ShowStates(homeStateId)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:Bundle?): View{
        bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        homeVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HomeVM::class.java)
        Log.d("fragment home", "getVM")

        bindingHome.errorLayout.emptyText.text = resources.getString(R.string.search_placeholderHint)

        homeAdapter = UserAdapter(arrayListOf()){ username, iv ->
            findNavController().navigate(FragmentHomeDirections.detailsAction(username),
                FragmentNavigatorExtras(
                    iv to username
                )
            )
        }

        bindingHome.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
            Log.d("homeRV", "apply $context")
        }

        bindingHome.searchBar.apply {
            queryHint = resources.getString(R.string.search_placeholderHint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    homeVM.setForSearch(query)
                    Log.d("search", "submit $query")
                    bindingHome.searchBar.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        observeHome()
    }

    private fun observeHome(){
        homeVM.searchResult.observe(viewLifecycleOwner, {
            Log.d("OBSERVE HOME", "start")
            it?.let { resourceStats ->
                when(resourceStats.states){
                    MyStates.IS_SUCCESS ->{
                        resourceStats.data?.let { users ->
                            if (!users.isNullOrEmpty()){
                                showStates.onSuccess(bindingHome, null)
                                homeAdapter.setData(users)
                                Log.d("OBSERVE HOME", "set Data $users")
                            }else{
                                showStates.onError(bindingHome, null, null, resources)
                            }
                        }
                    }
                MyStates.IS_LOADING -> {
                    showStates.onLoading(bindingHome, null)
                    Log.d("OBSERVE HOME", "loading state")
                }
                MyStates.IS_ERROR -> {
                    showStates.onError(bindingHome, null, it.message, resources)
                    Log.d("OBSERVE HOME", "Error Stats")
                }
                }
            }
        })
        Log.d("OBSERVE HOME", "finnish")
    }

    companion object{
        const val homeStateId = 1
    }
}