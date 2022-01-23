package com.madman.academybajp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.madman.academybajp.R
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.databinding.ActivityDetailCourseBinding
import com.madman.academybajp.databinding.ContentDetailCourseBinding
import com.madman.academybajp.ui.reader.CourseReaderActivity
import com.madman.academybajp.viewmodel.ViewModelFactory

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var contentDetailBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        contentDetailBinding = detailCourseBinding.detailContent
        setContentView(detailCourseBinding.root)

        setSupportActionBar(detailCourseBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {

                detailCourseBinding.progressBar.visibility = View.VISIBLE
                detailCourseBinding.content.visibility = View.INVISIBLE

                viewModel.selectedCourse(courseId)
                viewModel.getModules().observe(this, { modules ->
                    detailCourseBinding.progressBar.visibility = View.GONE
                    detailCourseBinding.content.visibility = View.VISIBLE

                    adapter.setModules(modules)
                    adapter.notifyDataSetChanged()
                })
                viewModel.getCourse().observe(this, { course -> populateCourse(course) })

            }
        }

        with(contentDetailBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        with(contentDetailBinding) {
            textTitle.text = courseEntity.title
            textDescription.text = courseEntity.description
            textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

            Glide.with(this@DetailCourseActivity)
                .load(courseEntity.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(contentDetailBinding.imagePoster)

            btnStart.setOnClickListener {
                val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
                intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
                startActivity(intent)
            }
        }

    }


    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}