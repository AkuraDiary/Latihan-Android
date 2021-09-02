package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentDetailBinding
import com.example.submission2belajarfundamentalaplikasiandroid.others.myStates
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.DetailsVM
import com.google.android.material.tabs.TabLayoutMediator


class FragmentDetail : Fragment() {
    private lateinit var bindingDetail: FragmentDetailBinding
    private lateinit var pagerAdapter: PagerAdapter
    private val detailVM : DetailsVM by activityViewModels()//DetailsVM
    private val args: FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*detailVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(detailVM::class.java)*/
        Log.d("Detail Fragment on Create", args.toString())
        detailVM.setDetail(args.username)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)
        bindingDetail.lifecycleOwner = viewLifecycleOwner
        observeDetail()
        return bindingDetail.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDetail.contentDetailHolder.transitionName = args.username

        //detailVM.setDetail(args.Username)
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
        detailVM.dataDetail.observe(viewLifecycleOwner, {
            if (it.states == myStates.IS_SUCCESS) {
                bindingDetail.userData = it.data
            }
        })
    }

    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment : Fragment
    ): FragmentStateAdapter(fragment){

        override fun getItemCount(): Int { return tabList.size }

        override fun createFragment(position: Int): Fragment { return FragmentFollow.newInstance(username, tabList[position]) }
    }
}