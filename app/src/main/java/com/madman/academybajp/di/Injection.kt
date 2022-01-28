package com.madman.academybajp.di

import android.content.Context
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.data.source.local.LocalDataSource
import com.madman.academybajp.data.source.local.room.AcademyDatabase
import com.madman.academybajp.data.source.remote.RemoteDataSource
import com.madman.academybajp.utils.AppExecutors
import com.madman.academybajp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}