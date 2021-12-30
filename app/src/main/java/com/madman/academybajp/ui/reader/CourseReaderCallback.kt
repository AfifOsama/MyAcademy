package com.madman.academybajp.ui.reader

interface CourseReaderCallback {
    fun moveTo(position:Int, courseId:String)
}