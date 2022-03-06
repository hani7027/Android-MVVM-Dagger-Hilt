package com.hk.reciptmanagment.data.repo

import com.hk.reciptmanagment.data.room.dao.UserDao
import com.hk.reciptmanagment.model.User
import javax.inject.Inject


class UserRepository @Inject constructor(val userDao: UserDao)  {

    suspend fun insert(user: User) = userDao.insert(user)
    suspend fun update(user: User) = userDao.update(user)
    suspend fun delete(user: User) = userDao.delete(user)
    fun selectAll() = userDao.getAllUser()
    fun selectById(id: Long) = userDao.getUserById(id)
}