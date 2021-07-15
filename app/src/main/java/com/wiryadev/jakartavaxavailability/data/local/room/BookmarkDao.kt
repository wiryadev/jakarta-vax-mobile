package com.wiryadev.jakartavaxavailability.data.local.room

import androidx.room.*
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarkedLocation(): Flow<List<VaccineBookmarkEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM bookmark WHERE nama_lokasi_vaksinasi=:query)")
    fun checkIsBookmarked(query: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToBookmark(entity: VaccineBookmarkEntity)

    @Delete
    suspend fun removeFromBookmark(entity: VaccineBookmarkEntity)

}