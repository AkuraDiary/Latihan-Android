package com.example.submission3belajarfundamentalaplikasiandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentFavoriteBinding
import com.example.submission3belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission3belajarfundamentalaplikasiandroid.ui.adapter.UserAdapter
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.FavoriteVM

class FragmentFavorite : Fragment(), ShowStates {
    private lateinit var bindingFavorite: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: UserAdapter
    private val favoriteViewModel: FavoriteVM by navGraphViewModels(R.id.my_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = context?.resources?.getString(R.string.favorite)
        bindingFavorite = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return bindingFavorite.root
    }

    private fun observeFavorite() {
        favoriteLoading(bindingFavorite)
        favoriteViewModel.favData.observe(viewLifecycleOwner, Observer {
            it?.let { users ->
                if (!users.isNullOrEmpty()){
                    favoriteSuccess(bindingFavorite)
                    favoriteAdapter.setUserData(users)
                } else {
                    favoriteError(
                        bindingFavorite,
                        resources.getString(R.string.kosong, "", resources.getString(R.string.favorite))
                    )
                }
            }
        })
    }

    override fun favoriteLoading(bindingFavorite: FragmentFavoriteBinding): Int? {
        bindingFavorite.apply {
            favErrorlayout.mainNotFound.visibility = gone
            progress.start()
            recyclerFav.visibility = gone
        }
        return super.favoriteLoading(bindingFavorite)
    }

    override fun favoriteSuccess(bindingFavorite: FragmentFavoriteBinding): Int? {
        bindingFavorite.apply {
            favErrorlayout.mainNotFound.visibility = gone
            progress.stop()
        }
        return super.favoriteSuccess(bindingFavorite)
    }

    override fun favoriteError(bindingFavorite: FragmentFavoriteBinding, message: String?): Int? {
        bindingFavorite.apply {
            favErrorlayout.apply {
                mainNotFound.visibility = visible
                emptyText.text = message ?: resources.getString(R.string.user_not_found)
            }
            progress.stop()
            recyclerFav.visibility = gone
        }
        return super.favoriteError(bindingFavorite, message)
    }
}