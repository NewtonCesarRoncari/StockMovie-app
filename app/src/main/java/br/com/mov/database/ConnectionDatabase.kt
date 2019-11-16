package br.com.mov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mov.database.dao.UserDAO
import br.com.mov.models.User

@Database(
    version = 1,
    entities = [User::class],
    exportSchema = false
)
abstract class ConnectionDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}