package com.example.submission3belajarfundamentalaplikasiandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentDetailBinding
import com.example.submission3belajarfundamentalaplikasiandroid.others.MyStates
import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.DetailsVM
import com.google.android.material.tabs.TabLayoutMediator
import com.shashank.sony.fancytoastlib.FancyToast


class FragmentDetail : Fragment() {
    private var isFavorite: Boolean = false
    private lateinit var bindingDetail: FragmentDetailBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var detailVM : DetailsVM //by activityViewModels()//DetailsVM
    private lateinit var user : User
    private val args: FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailVM = ViewModelProvider(
            this
        ).get(DetailsVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)
        bindingDetail.lifecycleOwner = viewLifecycleOwner

        observeDetail()

        return bindingDetail.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDetail.contentDetailHolder.transitionName = args.username
        bindingDetail.btnFavorite.setOnClickListener { addOrRemoveFavorite() }

        val tabList = arrayOf(
            resources.getString(R.string.txt_folower),
            resources.getString(R.string.txt_folowing)
        )
        pagerAdapter = PagerAdapter(tabList, args.username, this)
        bindingDetail.pager.adapter = pagerAdapter

        TabLayoutMediator(bindingDetail.tabs, bindingDetail.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    private fun observeDetail() {
        detailVM.data(args.username).observe(viewLifecycleOwner, {
            if(it.states == MyStates.IS_SUCCESS){
                user = it.data!!
                bindingDetail.userData = it.data
            }
        })

        detailVM.isFavorite.observe(viewLifecycleOwner, {
            Log.d("observe it : ", it.toString())
            isFavorite = it
            Log.d("isFavorit : ", isFavorite.toString())
            changeFavorite(it)
        })

        /*detailVM.dataDetail.observe(viewLifecycleOwner, {
            if (it.states == MyStates.IS_SUCCESS) {
                bindingDetail.userData = it.data
            }
        })*/
    }

    private fun addOrRemoveFavorite(){
        Log.d("add or Remove !isFavorite value : ", (!isFavorite).toString())
        if (!isFavorite){
            detailVM.addFavorite(user)
            FancyToast.makeText(
                context, resources.getString(R.string.favorite_add, user.login), Toast.LENGTH_SHORT, FancyToast.SUCCESS, false
            ).show()
        } else {
            detailVM.removeFavorite(user)
            FancyToast.makeText(
                context, resources.getString(R.string.favorite_remove, user.login), Toast.LENGTH_SHORT, FancyToast.ERROR, false
            ).show()
        }
    }

    private fun changeFavorite(condition: Boolean){
        Log.d("change fav : ", condition.toString())
        if (condition){
            bindingDetail.btnFavorite.setImageResource(R.drawable.ic_favorite_bottom_nav)
        } else {
            bindingDetail.btnFavorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }


    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment : Fragment
    ): FragmentStateAdapter(fragment){

        override fun getItemCount(): Int {
            return tabList.size
        }

        override fun createFragment(position: Int): Fragment {
            return FragmentFollow.newInstance(username, tabList[position])
        }
    }
}