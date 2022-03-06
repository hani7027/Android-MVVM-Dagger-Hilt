package com.hk.reciptmanagment.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.hk.reciptmanagment.data.room.AppDatabase
import com.hk.reciptmanagment.data.room.dao.UserDao
import com.hk.reciptmanagment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDatabaseName(): String = Constants.DATABASE_NAME

    @Singleton
    @Provides
    fun getRoomDb(@ApplicationContext context: Context, databaseName: String): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()

    @Singleton
    @Provides
    fun getUserDoa(db: AppDatabase): UserDao = db.getUserDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("com.hk.reciptmanagment_preferences", Context.MODE_PRIVATE)
    }

}