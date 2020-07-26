package com.example.ass_androidnetworking_kotlin.UI.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ass_androidnetworking_kotlin.Data.repository.AppRepository
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppFactory
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.AppRemoteDataSource
import com.example.ass_androidnetworking_kotlin.R
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var appRepository :AppRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_sign_in, container,false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appRepository = AppRepository.getInstance(AppRemoteDataSource.getInstace(AppFactory.instance))
        initView()

            }

    private fun initView() {
        registerTextview.setOnClickListener{
            activity?. run {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main, RegisterFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    fun handleDate(){
        GlobalScope.launch ( Dispatchers.IO ){
            appRepository.login("","")
        }
}

    companion object {
        fun newInstance() = LoginFragment()
    }
    
}