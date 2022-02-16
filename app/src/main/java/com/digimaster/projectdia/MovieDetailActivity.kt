package com.digimaster.projectdia

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.digimaster.projectdia.databinding.ActivityMovieDetailBinding

class MovieDetailActivity: AppCompatActivity(){
    lateinit var binding : ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}