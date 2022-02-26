package com.yeyannaung.codemanagement.di

import android.content.Context
import androidx.room.Room
import com.yeyannaung.codemanagement.db.AppDatabase
import com.yeyannaung.codemanagement.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constant.DB)
            .allowMainThreadQueries()
            .build()
}