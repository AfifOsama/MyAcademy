package com.madman.academybajp.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madman.academybajp.R
import com.madman.academybajp.ui.reader.content.ModuleContentFragment
import com.madman.academybajp.ui.reader.list.ModuleListFragment

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)
        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null) {
                populateFragment()
            }
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if (fragment == null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }


    override fun moveTo(position: Int, courseId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

}