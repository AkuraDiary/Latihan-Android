package com.example.submission2belajarfundamentalaplikasiandroid.others

import android.content.res.Resources
import android.util.Log
import android.view.View
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentFollowBinding
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding

class ShowStates(private val Id: Int) {

    fun onLoading(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                homeViewBinding?.searchProgress?.visibility = View.VISIBLE
                homeViewBinding?.recyclerHome?.visibility = View.GONE
                Log.d("States id 1", "on loading")
            }
            2 -> {
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                followViewBinding?.followProgress?.visibility = View.VISIBLE
                followViewBinding?.followRecycler?.visibility = View.GONE
                Log.d("States id 2", "on loading")
            }
        }
    }

    fun onSuccess(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                homeViewBinding?.searchProgress?.visibility = View.GONE
                homeViewBinding?.recyclerHome?.visibility = View.VISIBLE
                Log.d("States id 1", "on Success")
            }
            2 -> {
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.GONE
                followViewBinding?.followProgress?.visibility = View.GONE
                followViewBinding?.followRecycler?.visibility = View.VISIBLE
                Log.d("States id 2", "on Success")
            }
        }
    }

    fun onError(homeViewBinding: FragmentHomeBinding?, followViewBinding: FragmentFollowBinding?, Message: String?, res: Resources){
        when(Id){
            1 -> {
                homeViewBinding?.errorLayout?.emptyText?.text = Message ?: res.getString(R.string.user_not_found)
                homeViewBinding?.errorLayout?.mainNotFound?.visibility = View.VISIBLE
                homeViewBinding?.searchProgress?.visibility = View.GONE
                homeViewBinding?.recyclerHome?.visibility = View.GONE
                Log.d("States id 1", "on Error")
            }
            2 -> {
                followViewBinding?.errorLayout?.emptyText?.text = Message?: res.getString(R.string.user_not_found)
                followViewBinding?.errorLayout?.mainNotFound?.visibility = View.VISIBLE
                followViewBinding?.followProgress?.visibility = View.GONE
                followViewBinding?.followRecycler?.visibility = View.GONE
                Log.d("States id 2", "on Error")
            }
        }
    }
}