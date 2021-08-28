package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentDetailBinding
import com.example.submission2belajarfundamentalaplikasiandroid.others.myStates
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.DetailsVM


class FragmentDetail : Fragment() {
    private lateinit var bindingDetail: FragmentDetailBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var detailVM : DetailsVM
    private val args: FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(detailVM::class.java)
        detailVM.setForDetails(args.Username)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)
        bindingDetail.lifecycleOwner = viewLifecycleOwner
        observeDetail()
        return bindingDetail.root
    }

    private fun observeDetail() {
        detailVM.dataDetail.observe(viewLifecycleOwner, Observer {
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