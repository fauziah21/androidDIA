package com.digimaster.projectdia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimaster.projectdia.databinding.ActivityHalamanDuaBinding

class HalamanDua : AppCompatActivity() {
    lateinit var binding : ActivityHalamanDuaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}