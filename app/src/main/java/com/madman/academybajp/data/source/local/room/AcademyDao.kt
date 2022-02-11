package com.madman.academybajp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.madman.academybajp.data.source.local.entity.CourseEntity
import com.madman.academybajp.data.source.local.entity.CourseWithModule
import com.madman.academybajp.data.source.local.entity.ModuleEntity

@Dao
interface AcademyDao {
    @Query("SELECT * FROM course_entities")
    fun getCourses(): DataSource.Factory<Int, CourseEntity>

    @Query("SELECT * FROM course_entities where bookmarked = 1")
    fun getBookmarkedCourse(): DataSource.Factory<Int, CourseEntity>

    @Transaction
    @Query("SELECT * FROM course_entities WHERE courseId = :courseId")
    fun getCourseWithModuleById(courseId: String): LiveData<CourseWithModule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(courses: List<CourseEntity>)

    @Update
    fun updateCourse(course: CourseEntity)

    @Query("SELECT * FROM module_entities WHERE courseId = :courseId")
    fun getModulesByCourseId(courseId: String): LiveData<List<ModuleEntity>>

    @Query("SELECT * FROM module_entities WHERE moduleId = :moduleId")
    fun getModuleById(moduleId: String): LiveData<ModuleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModules(module: List<ModuleEntity>)

    @Update
    fun updateModule(module: ModuleEntity)

    @Query("UPDATE module_entities SET content = :content WHERE moduleId = :moduleId")
    fun updateModuleByContent(content: String, moduleId: String)
}