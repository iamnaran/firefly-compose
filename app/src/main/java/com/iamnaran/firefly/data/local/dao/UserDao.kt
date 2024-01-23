package com.iamnaran.firefly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.firefly.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface  UserDao {

    @Query("SELECT * FROM user WHERE id = :_id")
    suspend fun getUserById(_id: Long): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

}