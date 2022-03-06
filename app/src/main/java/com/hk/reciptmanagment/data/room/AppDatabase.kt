package com.hk.reciptmanagment.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hk.reciptmanagment.data.room.dao.UserDao
import com.hk.reciptmanagment.model.User

@Database(entities = [User::class], version =1, exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}