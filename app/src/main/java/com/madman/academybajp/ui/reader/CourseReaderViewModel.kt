package com.madman.academybajp.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.academybajp.data.ContentEntity
import com.madman.academybajp.data.ModuleEntity
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId:String

    fun selectedCourse(courseId:String){
        this.courseId=courseId
    }

    fun selectedModule(moduleId: String){
        this.moduleId=moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContent(courseId,moduleId)


}