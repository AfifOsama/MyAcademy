package com.madman.academybajp.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madman.academybajp.R

class CourseReaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)
    }

    companion object{
        const val EXTRA_COURSE_ID="extra_course_id"
    }
}