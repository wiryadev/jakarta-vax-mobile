package com.wiryadev.jakartavaxavailability.data.local

import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.data.local.room.BookmarkDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: BookmarkDao) {

    fun getBookmarkList(): Flow<List<VaccineBookmarkEntity>> = dao.getAllBookmarkedLocation()

    fun checkBookmark(query: String): Flow<Int> = dao.checkIsBookmarked(query = query)

    suspend fun addToBookmark(entity: VaccineBookmarkEntity) {
        dao.addToBookmark(entity = entity)
    }

    suspend fun removeFromBookmark(entity: VaccineBookmarkEntity) {
        dao.removeFromBookmark(entity = entity)
    }
}