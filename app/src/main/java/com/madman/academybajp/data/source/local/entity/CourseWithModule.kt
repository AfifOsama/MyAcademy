package com.madman.academybajp.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithModule(
    @Embedded
    val mCourse: CourseEntity,

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModules: List<ModuleEntity>
)