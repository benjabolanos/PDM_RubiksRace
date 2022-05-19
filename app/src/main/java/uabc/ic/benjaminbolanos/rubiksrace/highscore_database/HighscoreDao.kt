package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Clase HighscoreDao que contiene las sentencias de SQLite necesarias para el funcionamiento de la
 * base de datos.
 */
@Dao
interface HighscoreDao {

    @Query("SELECT * FROM HIGHSCORE_TABLE")
    fun getAll(): Flow<List<Highscore>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(highscore: Highscore)

    @Query("DELETE FROM HIGHSCORE_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM HIGHSCORE_TABLE ORDER BY tiempo ASC, movimientos ASC")
    fun getOrdered(): Flow<List<Highscore>>
}