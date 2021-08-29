package com.example.submission2belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.adapter.AdapterUser
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.FragmentFollowBinding
import com.example.submission2belajarfundamentalaplikasiandroid.others.FollowView
import com.example.submission2belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission2belajarfundamentalaplikasiandroid.others.myStates
import com.example.submission2belajarfundamentalaplikasiandroid.view_model.FollowVM


class FragmentFollow : Fragment(){

    private lateinit var bindingFollow: FragmentFollowBinding
    private lateinit var adapterUser : AdapterUser
    private lateinit var followVM : FollowVM
    private lateinit var username:String
    private var type: String? = null
    private var showStates = ShowStates(followStateId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFollow = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return bindingFollow.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterUser = AdapterUser(arrayListOf()){
            user, _ -> Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
        }

        bindingFollow.followRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterUser
        }

        followVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowVM::class.java)

        when(type){
            resources.getString(R.string.txt_folowing)->followVM.setFollows(username, FollowView.FOLLOWINGS)
            resources.getString(R.string.txt_folower)->followVM.setFollows(username, FollowView.FOLLOWERS)
            else -> showStates.onError(null, bindingFollow, null, resources)
        }
        observeFollow()
    }

    private fun observeFollow(){
        followVM.dataFollows.observe(viewLifecycleOwner, Observer {
            when(it.states){
                myStates.IS_SUCCESS ->
                    if(!it.data.isNullOrEmpty()){
                        showStates.onSuccess(null, bindingFollow)
                        adapterUser.run{setData(it.data)}
                    }else{
                        val stringRes = resources.getString(R.string.kosong, username, type)
                        showStates.onError(null, bindingFollow, stringRes, resources)
                    }
                myStates.IS_LOADING -> showStates.onLoading(null, bindingFollow)
                myStates.IS_ERROR -> showStates.onError(null, bindingFollow, it.message, resources)
            }
        })
    }

    companion object{
        fun newInstance(username: String, type: String)=
            FragmentFollow().apply{
                arguments = Bundle().apply{
                    putString(USERNAME, username)
                    putString(TYPE, type)
                }
            }
        const val followStateId = 2
        private const val TYPE = "type"
        private const val USERNAME = "username"
    }

}