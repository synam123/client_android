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
import com.fpoly.assignemnt_gd1.utils.snack
import kotlinx.android.synthetic.main.fragment_sign_in.*
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appRepository =
            AppRepository.getInstance(AppRemoteDataSource.getInstace(AppFactory.instance))
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
            edtName.error = "Do not empty user name"
            return 0
        } else edtName.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(userNameTextInputEdiText.text.toString()).matches()) {
            edtName.error = "Do not match email"
            return 0
        } else edtName.error = null
        if (passwordTextInputEdiText.text.isNullOrEmpty()) {
            edtPass.error = "Do not empty password"
            return 0
        } else edtPass.error = null
        return 1
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}