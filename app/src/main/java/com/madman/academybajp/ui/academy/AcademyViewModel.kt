package com.madman.academybajp.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy

class AcademyViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}