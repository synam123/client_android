package com.example.ass_androidnetworking_kotlin.UI.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.repository.AppRepository
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppFactory
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.AppRemoteDataSource
import com.example.ass_androidnetworking_kotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class RegisterFragment : Fragment (){
    private lateinit var appRepository: AppRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appRepository=
            AppRepository.getInstance(AppRemoteDataSource.getInstace(AppFactory.instance))
    }
    fun  handleData(){
        GlobalScope.launch( Dispatchers.IO) {
            appRepository.register(User("","","",0))
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }

}