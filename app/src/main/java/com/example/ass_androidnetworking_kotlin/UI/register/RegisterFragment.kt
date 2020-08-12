package com.fpoly.assignemnt_gd1.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.repository.AppRepository
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppFactory
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.AppRemoteDataSource
import com.example.ass_androidnetworking_kotlin.R
import com.example.ass_androidnetworking_kotlin.UI.login.LoginFragment
import com.fpoly.assignemnt_gd1.utils.snack
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.passwordTextInputEdiText
import kotlinx.android.synthetic.main.fragment_register.passwordTextInputLayout
import kotlinx.android.synthetic.main.fragment_register.progressBar
import kotlinx.android.synthetic.main.fragment_register.userNameTextInputEdiText
import kotlinx.android.synthetic.main.fragment_register.userNameTextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {
    private lateinit var appRepository: AppRepository
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
            loginTextview.setOnClickListener {
                activity?.run {
                    supportFragmentManager
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.main, RegisterFragment.newInstancez())
                        .addToBackStack(null)
                        .commit()
                }
            }
        registerButton.setOnClickListener {
            if (validate() > 0) {
                progressBar.visibility = View.VISIBLE
                handleData()
            }
        }
    }

    private fun handleData() {
        GlobalScope.launch(Dispatchers.IO) {
            val baseResponse = appRepository.register(
                User(
                    "${fullNameTextInputEdiText.text}",
                    "${userNameTextInputEdiText.text}",
                    "${passwordTextInputEdiText.text}",
                    if (roleCheckBox.isChecked) 1 else 0
                )
            )
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                if (baseResponse.statusCode == 200) {
                    view?.snack(message = baseResponse.messages)
                } else view?.snack(message = baseResponse.messages)
            }
        }
    }



    private fun validate(): Int {
        if (fullNameTextInputEdiText.text.isNullOrEmpty()) {
            fullNameTextInputLayout.error = "Do not empty full name"
            return 0
        } else fullNameTextInputLayout.error = null
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
            Toast.makeText(context, "Signup Success", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = RegisterFragment()
        fun newInstancez() = LoginFragment()
    }
}