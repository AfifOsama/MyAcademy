package com.madman.academybajp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.data.ModuleEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun selectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)
    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}