package com.example.testfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testfilms.fragment.HomeFragment
import com.example.testfilms.fragment.LikeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Films : AppCompatActivity() {



    lateinit var navigation : BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        //меню навигации
        navigation = findViewById(R.id.navigation)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()


        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home ->  {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
                }
                R.id.like -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, LikeFragment()).commit()
                }
            }
            true
        }
    }

}