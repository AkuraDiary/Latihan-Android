package com.example.submission3belajarfundamentalaplikasiandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3belajarfundamentalaplikasiandroid.R
import com.example.submission3belajarfundamentalaplikasiandroid.adapter.UserAdapter
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentFollowBinding
import com.example.submission3belajarfundamentalaplikasiandroid.others.FollowView
import com.example.submission3belajarfundamentalaplikasiandroid.others.MyStates
import com.example.submission3belajarfundamentalaplikasiandroid.others.ShowStates
import com.example.submission3belajarfundamentalaplikasiandroid.view_model.FollowVM


class FragmentFollow : Fragment(){

    private lateinit var bindingFollow: FragmentFollowBinding
    private lateinit var userAdapter : UserAdapter
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        bindingFollow = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return bindingFollow.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(arrayListOf()){ user, _ ->
            Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
        }

        bindingFollow.followRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
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
        followVM.dataFollows.observe(viewLifecycleOwner, {
            when(it.states){
                MyStates.IS_SUCCESS ->
                    if(!it.data.isNullOrEmpty()){
                        showStates.onSuccess(null, bindingFollow)
                        userAdapter.run{setUserData(it.data)}
                    }else{

                        showStates.onError(null,
                            bindingFollow,
                            resources.getString(R.string.kosong, username, type),
                            resources)
                    }
                MyStates.IS_LOADING -> showStates.onLoading(null, bindingFollow)
                MyStates.IS_ERROR -> showStates.onError(null, bindingFollow, it.message, resources)
            }
        })
    }

    companion object{
        fun newInstance(username: String, type: String)= FragmentFollow().apply{
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