package com.madman.academybajp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madman.academybajp.R
import com.madman.academybajp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter=SectionsPagerAdapter(this,supportFragmentManager)
        with(binding) {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
        supportActionBar?.elevation=0f
    }
}