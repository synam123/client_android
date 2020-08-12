package com.fpoly.assignemnt_gd1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ass_androidnetworking_kotlin.Data.repository.AppRepository
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppFactory
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.AppRemoteDataSource
import com.example.ass_androidnetworking_kotlin.R
import com.example.ass_androidnetworking_kotlin.UI.login.LoginFragment
import com.fpoly.code4fun.ui.home.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var appRepository: AppRepository
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appRepository =
            AppRepository.getInstance(AppRemoteDataSource.getInstance(AppFactory.instance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleData()

    }

    private fun handleData() {
        GlobalScope.launch(Dispatchers.IO) {
            val response=appRepository.getProducts().unwrap()
            withContext(Dispatchers.Main){
                homeAdapter.submitList(response.products)
            }
        }
    }

    private fun initView() {
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeAdapter= HomeAdapter(this).apply {
            onItemClick = {
                Toast.makeText(activity, "${it.title}", Toast.LENGTH_SHORT).show()
            }
        }
        homeRecyclerView.adapter =homeAdapter
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}