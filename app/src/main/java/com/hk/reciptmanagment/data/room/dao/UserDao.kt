package com.hk.reciptmanagment.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hk.reciptmanagment.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(vararg user: User?)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("select * from user")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from user where id=:id")
    fun getUserById(id: Long): LiveData<User>


    @Query("select * from user where userName=:email AND password =:password")
    suspend fun login(email: String, password: String): List<User>

}