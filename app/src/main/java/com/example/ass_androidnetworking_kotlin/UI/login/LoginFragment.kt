package com.example.ass_androidnetworking_kotlin.UI.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ass_androidnetworking_kotlin.Data.repository.AppRepository
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppFactory
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.AppRemoteDataSource
import com.example.ass_androidnetworking_kotlin.R

import com.fpoly.assignemnt_gd1.ui.home.HomeFragment
import com.fpoly.assignemnt_gd1.ui.register.RegisterFragment
import com.fpoly.assignemnt_gd1.utils.snack
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private lateinit var appRepository: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appRepository =
            AppRepository.getInstance(AppRemoteDataSource.getInstance(AppFactory.instance()))
        initView()
    }

    private fun initView() {
        registerTextview.setOnClickListener {
            activity?.run {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main, RegisterFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
        btnsignin.setOnClickListener {
            if (validate() > 0) {
                progressBar.visibility = View.VISIBLE
                handleData()
            }
        }
    }

    private fun handleData() {
        GlobalScope.launch(Dispatchers.IO) {
            val baseResponse = appRepository.login(
                "${userNameTextInputEdiText.text}",
                "${passwordTextInputEdiText.text}"
            )
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                if (baseResponse.statusCode == 200) {
                    view?.snack(message = baseResponse.messages)
                    activity?.run {
                        supportFragmentManager
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.main, HomeFragment.newInstance())
                            .commit()
                    }
                } else view?.snack(message = baseResponse.messages)
            }
        }
    }

    private fun validate(): Int {
        if (userNameTextInputEdiText.text.isNullOrEmpty()) {
            userNameTextInputLayout.error = "Do not empty user name"
            return 0
        } else userNameTextInputLayout.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(userNameTextInputEdiText.text.toString()).matches()) {
            userNameTextInputLayout.error = "Do not match email"
            return 0
        } else userNameTextInputLayout.error = null
        if (passwordTextInputEdiText.text.isNullOrEmpty()) {
            passwordTextInputLayout.error = "Do not empty password"
            return 0
        } else passwordTextInputLayout.error = null
        return 1
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}