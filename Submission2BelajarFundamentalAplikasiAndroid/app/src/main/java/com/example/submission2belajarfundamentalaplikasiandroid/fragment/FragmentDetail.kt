package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    private lateinit var detailVM : DetailsVM
    private val args : FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(detailVM::class.java)
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

        bindingDetail.contentDetailHolder.transitionName = args.Username

        detailVM.setForDetails(args.Username)

        val tabList = arrayOf(
            resources.getString(R.string.txt_folower),
            resources.getString(R.string.txt_folowing)
        )
        pagerAdapter = PagerAdapter(tabList, args.Username, this)
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
        private val tablist: Array<String>,
        private val username: String,
        fragment : Fragment
    ): FragmentStateAdapter(fragment){
        override fun getItemCount(): Int {
            return tablist.size
        }

        override fun createFragment(position: Int): Fragment {
            return FragmentFollow.newInstance(username, tablist[position])
        }
    }
}