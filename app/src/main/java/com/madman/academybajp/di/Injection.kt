package com.madman.academybajp.di

import android.content.Context
import com.madman.academybajp.data.source.AcademyRepository
import com.madman.academybajp.data.source.remote.RemoteDataSource
import com.madman.academybajp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository{
        val remoteDataSource=RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}