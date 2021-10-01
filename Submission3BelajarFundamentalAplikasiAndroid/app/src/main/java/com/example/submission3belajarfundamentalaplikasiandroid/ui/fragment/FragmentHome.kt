package com.example.submission3belajarfundamentalaplikasiandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.ui.adapter.UserAdapter
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding
import com.example.submission3belajarfundamentalaplikasiandroid.others.MyStates
import com.example.submission3belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.HomeVM

class FragmentHome: Fragment(), ShowStates {

    private lateinit var bindingHome: FragmentHomeBinding
    private lateinit var homeAdapter : UserAdapter
    private val homeVM : HomeVM by navGraphViewModels(R.id.my_nav)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:Bundle?): View{
        bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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
        homeVM.searchResult.observe(viewLifecycleOwner, {
            it?.let {
                    resourceStats -> when(resourceStats.states){
                        MyStates.IS_SUCCESS ->{
                            resourceStats.data?.let { users ->
                                if (!users.isNullOrEmpty()){
                                    homeSuccess(bindingHome)
                                    homeAdapter.setUserData(users)
                                }else{
                                    homeError(bindingHome, null)
                                }
                            }
                        }
                        MyStates.IS_LOADING -> {
                            homeLoading(bindingHome)
                        }
                        MyStates.IS_ERROR -> {
                            homeError(bindingHome, it.message)
                        }
                    }
            }
        })
    }

    override fun homeLoading(bindingHome: FragmentHomeBinding): Int? {
        bindingHome.apply {
            errorLayout.mainNotFound.visibility = gone
            progress.start()
            progress.loadingColor = R.color.design_default_color_on_secondary
            recyclerHome.visibility = gone
        }
        return super.homeLoading(bindingHome)
    }

    override fun homeSuccess(bindingHome: FragmentHomeBinding): Int? {
        bindingHome.apply {
            errorLayout.mainNotFound.visibility = gone
            progress.stop()
            recyclerHome.visibility = visible
        }
        return super.homeSuccess(bindingHome)
    }

    override fun homeError(bindingHome: FragmentHomeBinding, message: String?): Int? {
        bindingHome.apply {
            errorLayout.apply {
                mainNotFound.visibility = visible
                emptyText.text = message ?: resources.getString(R.string.user_not_found)
            }
            progress.stop()
            recyclerHome.visibility = gone
        }
        return super.homeError(bindingHome, message)
    }
}