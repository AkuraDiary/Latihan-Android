package com.example.submission3belajarfundamentalaplikasiandroid.others

import android.content.res.Resources
import android.view.View
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentFollowBinding
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding

class OldShowStates(private val Id: Int) {

    fun onLoading(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                homeViewBinding?.progress?.visibility = View.VISIBLE
                homeViewBinding?.recyclerHome?.visibility = View.GONE
            }
            2 -> {
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                followViewBinding?.progress?.visibility = View.VISIBLE
                followViewBinding?.followRecycler?.visibility = View.GONE
            }
        }
    }

    fun onSuccess(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                homeViewBinding?.progress?.visibility = View.GONE
                homeViewBinding?.recyclerHome?.visibility = View.VISIBLE
            }
            2 -> {
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                followViewBinding?.progress?.visibility = View.GONE
                followViewBinding?.followRecycler?.visibility = View.VISIBLE
            }
        }
    }

    fun onError(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?, Message: String?, res: Resources){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.emptyText?.text = Message ?: res.getString(R.string.user_not_found)
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.VISIBLE
                homeViewBinding?.progress?.visibility = View.GONE
                homeViewBinding?.recyclerHome?.visibility = View.GONE
            }
            2 -> {
                followViewBinding?.errorLayout?.emptyText?.text = Message?: res.getString(R.string.kosong)
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.VISIBLE
                followViewBinding?.progress?.visibility = View.GONE
                followViewBinding?.followRecycler?.visibility = View.GONE
            }
        }
    }
}