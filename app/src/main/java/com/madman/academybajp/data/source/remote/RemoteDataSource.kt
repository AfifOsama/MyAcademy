package com.madman.academybajp.data.source.remote

import android.os.Handler
import android.os.Looper
import com.madman.academybajp.data.source.remote.response.ContentResponse
import com.madman.academybajp.data.source.remote.response.CourseResponse
import com.madman.academybajp.data.source.remote.response.ModuleResponse
import com.madman.academybajp.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    fun getAllCourses(callback: LoadCoursesCallback) {
        handler.postDelayed(
            { callback.onAllCoursedReceived(jsonHelper.loadCourses()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadCoursesCallback {
        fun onAllCoursedReceived(courseResponse: List<CourseResponse>)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        handler.postDelayed(
            { callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponse: List<ModuleResponse>)
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        handler.postDelayed(
            { callback.onContentReceived(jsonHelper.loadContent(moduleId)) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }
}