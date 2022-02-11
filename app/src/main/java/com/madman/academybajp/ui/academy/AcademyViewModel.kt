package com.madman.academybajp.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.madman.academybajp.data.source.local.entity.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getAllCourses()
}