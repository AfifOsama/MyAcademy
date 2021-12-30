package com.madman.academybajp.ui.bookmark

import com.madman.academybajp.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
