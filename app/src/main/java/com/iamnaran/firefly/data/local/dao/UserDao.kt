package com.iamnaran.firefly.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.iamnaran.firefly.data.local.entities.User

@Dao
interface  UserDao {

    @Query("SELECT * FROM user WHERE id = :id_")
    abstract suspend fun getUser(id_: Long): User?

}