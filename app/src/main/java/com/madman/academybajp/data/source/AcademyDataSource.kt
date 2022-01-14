package com.madman.academybajp.data.source

import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.data.ModuleEntity

interface AcademyDataSource {
    fun getAllCourses(): ArrayList<CourseEntity>

    fun getBookmarkedCourses(): ArrayList<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}