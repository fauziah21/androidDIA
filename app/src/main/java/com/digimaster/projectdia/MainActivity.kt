package com.digimaster.projectdia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.digimaster.projectdia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvSatu.setOnClickListener {
            if (binding.etPin.text.toString() == "123") {
                val intent = Intent(this, Class.forName("com.digimaster.projectdia.HalamanDua"))
                this.startActivity(intent)
            } else {
                Toast.makeText(this, "Pin salah", Toast.LENGTH_SHORT).show()
            }

        }

    }
}