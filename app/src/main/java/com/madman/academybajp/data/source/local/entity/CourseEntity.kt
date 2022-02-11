package com.madman.academybajp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "course_entities")
class CourseEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "courseId")
    var courseId: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "deadline")
    var deadline: String,
    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,
    @ColumnInfo(name = "imagePath")
    var imagePath: String
)