package com.madman.academybajp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madman.academybajp.databinding.ActivityDetailCourseBinding
import com.madman.academybajp.databinding.ContentDetailCourseBinding

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var contentDetailBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        contentDetailBinding = detailCourseBinding.detailContent
        setContentView(detailCourseBinding.root)

        setSupportActionBar(detailCourseBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}