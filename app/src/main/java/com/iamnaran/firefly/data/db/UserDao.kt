package com.iamnaran.firefly.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.firefly.data.model.User

@Dao
interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPosterList(posters: List<User>)

  @Query("SELECT * FROM user WHERE id = :id_")
  suspend fun getUser(id_: Long): User?

}