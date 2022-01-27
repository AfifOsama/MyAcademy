package com.madman.academybajp.ui.bookmark

import com.madman.academybajp.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
