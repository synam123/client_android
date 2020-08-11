package com.example.ass_androidnetworking_kotlin.UI.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ass_androidnetworking_kotlin.R
import com.example.ass_androidnetworking_kotlin.UI.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main, LoginFragment.newInstance())
            .commit()
    }
}