package com.madman.academybajp.ui.bookmark

import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}