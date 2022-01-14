package com.madman.academybajp.ui.bookmark

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp(){
        viewModel= BookmarkViewModel()
    }

    @Test
    //Memastikan data course not null dan jumlah data course sesuai dengan yang diharapkan.
    fun getCourses() {
        val courseEntities=viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5,courseEntities.size)
    }
}