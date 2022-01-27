package com.madman.academybajp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.academybajp.data.source.local.entity.CourseEntity
import com.madman.academybajp.data.source.local.entity.ModuleEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse=DataDummy.generateDummyCourses()[0]
    private val courseId=dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var courseObserver:Observer<CourseEntity>

    @Mock
    private lateinit var moduleObserver:Observer<List<ModuleEntity>>

    @Before
    fun setUp(){
        viewModel= DetailCourseViewModel(academyRepository)
        viewModel.selectedCourse(courseId)
    }

    @Test
    fun getCourse(){
        val course=MutableLiveData<CourseEntity>()
        course.value=dummyCourse
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        val courseEntity=viewModel.getCourse().value as CourseEntity
        verify<AcademyRepository>(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId,courseEntity.courseId)
        assertEquals(dummyCourse.title,courseEntity.title)
        assertEquals(dummyCourse.description,courseEntity.description)
        assertEquals(dummyCourse.deadline,courseEntity.deadline)
        assertEquals(dummyCourse.imagePath,courseEntity.imagePath)

        viewModel.getCourse().observeForever(courseObserver)
        verify(courseObserver).onChanged(dummyCourse)
    }

    @Test
    fun getModule(){
        val module=MutableLiveData<List<ModuleEntity>>()
        module.value=dummyModules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(module)
        val moduleEntity=viewModel.getModules().value
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntity)
        assertEquals(7,moduleEntity?.size)

        viewModel.getModules().observeForever(moduleObserver)
        verify(moduleObserver).onChanged(dummyModules)
    }

}