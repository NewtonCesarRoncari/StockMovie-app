package br.com.mov.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.mov.models.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User) : Long

    @Query("SELECT * FROM User")
    fun findUser(): LiveData<User>

    @Delete
    fun removeUser(user: User)
}