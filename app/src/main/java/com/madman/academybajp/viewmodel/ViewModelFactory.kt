package com.madman.academybajp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.di.Injection
import com.madman.academybajp.ui.academy.AcademyViewModel
import com.madman.academybajp.ui.bookmark.BookmarkViewModel
import com.madman.academybajp.ui.detail.DetailCourseViewModel
import com.madman.academybajp.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }
}