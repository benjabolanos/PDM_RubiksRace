package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Clase Abstracta HighscoreDatabase, extiende de RoomDatabase. En su constructor se encuentra
 * que cuenta con una tabla de Highscores
 */
@Database(entities = arrayOf(Highscore::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HighscoreDatabase: RoomDatabase() {

    abstract  fun highscoreDao(): HighscoreDao

    private class HighscoreDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        /**
         * Metodo onCreate que al crear la base de datos inserta 2 datos aleatorios.
         */
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val hsDao = database.highscoreDao()
                    hsDao.deleteAll()
                    var hs = Highscore.random()
                    hsDao.insert(hs)
                    hs = Highscore.random()
                    hsDao.insert(hs)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HighscoreDatabase? = null

        /**
         * Metodo para obtener la base de datos y que esta sea un singleton para no tener multiples
         * instancias de la misma.
         */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HighscoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HighscoreDatabase::class.java,
                    "highscore_database"
                )
                    .addCallback(HighscoreDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}