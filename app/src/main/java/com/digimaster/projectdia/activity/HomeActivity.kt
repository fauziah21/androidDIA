package com.digimaster.projectdia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.digimaster.projectdia.R
import com.digimaster.projectdia.databinding.ActivityHomeBinding
import com.digimaster.projectdia.fragment.HomeFragment
import com.digimaster.projectdia.fragment.MovieFragment
import com.digimaster.projectdia.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //untuk load content saat masuk pertama kali ke page setelah splash screen
        val fragmentt: Fragment = HomeFragment()
        val transactionn:FragmentTransaction = supportFragmentManager.beginTransaction()
        transactionn.replace(R.id.content, fragmentt).commit()

        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.menuSatu ->{
                    val fragment: Fragment = HomeFragment()
                    val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.content, fragment).commit()
                    true
                }
                R.id.menuDua ->{
                    val fragment:Fragment = ProfileFragment()
                    val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.content, fragment).commit()
                    true
                }
                R.id.menuMovie ->{
                    val fragment:Fragment = MovieFragment()
                    val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.content, fragment).commit()
                    true
                }

                else -> false
            }

        }
    }
}