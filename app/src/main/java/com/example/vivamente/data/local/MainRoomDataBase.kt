package com.example.vivamente.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vivamente.data.local.daos.UserDao
import com.example.vivamente.data.local.models.Remedy
import com.example.vivamente.data.local.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [User::class, Remedy::class], version = 2, exportSchema = false )
abstract class MainRoomDataBase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: MainRoomDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MainRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainRoomDataBase::class.java,
                    "user_database"
                ).addCallback(MainDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class MainDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }

        suspend fun populateDatabase(userDao: UserDao) {
            // Delete all content here.
            userDao.deleteAll()

            // Add sample counts.
            var user = User( 0,"admin", "admin@gmail.com", "admin")
            userDao.insert(user)
            user = User( 0,"Paulo Silveira", "silveira.paulo@gmail.com", "1243568790")
            userDao.insert(user)
        }
        /*@RequiresApi(Build.VERSION_CODES.O)
        fun setDateNow(): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyy"))
        }*/
    }

}