package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
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
import com.example.submission2belajarfundamentalaplikasiandroid.adapter.AdapterUser
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding
import com.example.submission2belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission2belajarfundamentalaplikasiandroid.others.myStates
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.HomeVM

class FragmentHome: Fragment() {


    private lateinit var bindingHome: FragmentHomeBinding
    private lateinit var adapterHome : AdapterUser
    private lateinit var homeVM : HomeVM
    private var showStates = ShowStates(homeStateId)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:Bundle?
    ): View?{
        bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        homeVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HomeVM::class.java)

        bindingHome.errorLayout.emptyText.text = resources.getString(R.string.search_placeholderHint)

        adapterHome = AdapterUser(arrayListOf()){
            username, iv -> findNavController().navigate(FragmentHomeDirections.detailsAction(),
            FragmentNavigatorExtras(iv to username))
        }

        bindingHome.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        bindingHome.searchBar.apply {
            queryHint = resources.getString(R.string.search_placeholderHint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    homeVM.setForSearch(query)
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
        homeVM.searchRes.observe(viewLifecycleOwner, {

            it?.let { resourceStats -> when(resourceStats.states){
                myStates.IS_SUCCESS ->{
                    resourceStats.data?.let { users ->
                        if (!users.isNullOrEmpty()){
                            showStates.onSuccess(bindingHome, null)
                        }else{
                            showStates.onError(bindingHome, null, null, resources)
                        }
                    }
                }
                myStates.IS_LOADING -> showStates.onLoading(bindingHome, null)
                myStates.IS_ERROR -> showStates.onError(bindingHome, null, it.message, resources)
                }
            }
        })
    }

    companion object{
        const val homeStateId = 1
    }
}