package com.madman.academybajp.ui.detail

import com.madman.academybajp.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse=DataDummy.generateDummyCourses()[0]
    private val courseId=dummyCourse.courseId

    @Before
    fun setUp(){
        viewModel= DetailCourseViewModel()
        viewModel.selectedCourse(courseId)
    }

    @Test
    fun getCourse(){
        viewModel.selectedCourse(dummyCourse.courseId)
        val courseEntity=viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId,courseEntity.courseId)
        assertEquals(dummyCourse.title,courseEntity.title)
        assertEquals(dummyCourse.description,courseEntity.description)
        assertEquals(dummyCourse.deadline,courseEntity.deadline)
        assertEquals(dummyCourse.imagePath,courseEntity.imagePath)
    }

    @Test
    fun getModule(){
        val moduleEntity=viewModel.getModules()
        assertNotNull(moduleEntity)
        assertEquals(7,moduleEntity.size.toLong())
    }

}