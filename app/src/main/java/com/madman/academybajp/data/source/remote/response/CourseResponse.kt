package com.madman.academybajp.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseResponse(
    val id: String,
    val title: String,
    var description: String,
    var date: String,
    var imagePath: String
) : Parcelable
