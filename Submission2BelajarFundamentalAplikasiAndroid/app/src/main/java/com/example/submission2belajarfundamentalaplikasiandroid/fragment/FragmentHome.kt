package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import androidx.fragment.app.Fragment
import com.example.submission2belajarfundamentalaplikasiandroid.adapter.AdapterUser
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding

class FragmentHome: Fragment() {


    private lateinit var bindingHome: FragmentHomeBinding
    private lateinit var AdapterH : AdapterUser
    //private lateinit var homeVM = HomeVM
   // private var show_state = ShowState(homeStateId)

    companion object{
        const val homeStateId = 1
    }
}