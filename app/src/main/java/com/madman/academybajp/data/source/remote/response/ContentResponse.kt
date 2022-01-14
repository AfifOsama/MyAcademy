package com.madman.academybajp.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentResponse(
    var moduelId: String,
    var content: String
) : Parcelable
