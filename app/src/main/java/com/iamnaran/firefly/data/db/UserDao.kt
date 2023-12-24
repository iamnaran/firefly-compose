package com.iamnaran.firefly.data.db

import androidx.room.Dao
import androidx.room.Query
import com.iamnaran.firefly.data.entities.User

@Dao
abstract class UserDao : BaseDao<User>() {

    @Query("SELECT * FROM user WHERE id = :id_")
    abstract suspend fun getUser(id_: Long): User?

}