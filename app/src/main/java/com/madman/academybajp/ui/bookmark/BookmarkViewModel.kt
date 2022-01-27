package com.madman.academybajp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.source.local.entity.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarked(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}