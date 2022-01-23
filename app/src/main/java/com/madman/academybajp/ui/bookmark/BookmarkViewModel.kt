package com.madman.academybajp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarked(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}