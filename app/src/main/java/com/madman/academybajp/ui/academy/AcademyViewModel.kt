package com.madman.academybajp.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.source.local.entity.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}