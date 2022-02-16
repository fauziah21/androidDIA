package com.digimaster.projectdia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimaster.projectdia.activity.HomeActivity
import com.digimaster.projectdia.databinding.ActivityLoginBinding
import com.digimaster.projectdia.utils.PrefsKeys
import com.pixplicity.easyprefs.library.Prefs

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener{
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            Prefs.putString(PrefsKeys.USERNAME, username)
            Prefs.putString(PrefsKeys.PASSWORD, password)
            Prefs.putBoolean(PrefsKeys.IS_LOGIN, true)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}