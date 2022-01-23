package com.madman.academybajp.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.academybajp.data.CourseEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecuterRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    //Memastikan data course not null dan jumlah data course sesuai dengan yang diharapkan.
    fun getBookmarked() {
        val dummyCourse = DataDummy.generateDummyCourses()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourse

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)
        val courseEntities = viewModel.getBookmarked().value
        Mockito.verify<AcademyRepository>(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getBookmarked().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}