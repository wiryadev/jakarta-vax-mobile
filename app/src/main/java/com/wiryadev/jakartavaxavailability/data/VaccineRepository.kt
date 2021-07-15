package com.wiryadev.jakartavaxavailability.data

import com.wiryadev.jakartavaxavailability.data.local.LocalDataSource
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity
import com.wiryadev.jakartavaxavailability.data.remote.RemoteDataSource
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.ui.home.SearchType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VaccineRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) {

    suspend fun getVaccines(
        isRefreshing: Boolean,
        query: String,
        searchType: SearchType,
    ): List<VaccineResponseItem> {
        return remoteDataSource.getVaccines(
            isRefreshing = isRefreshing,
            query = query,
            searchType = searchType
        )
    }

    suspend fun getLocationByName(name: String, isRefreshing: Boolean): VaccineResponseItem {
        return remoteDataSource.getLocationByName(
            name = name,
            isRefreshing = isRefreshing,
        )
    }

    fun getBookmarkList(): Flow<List<VaccineBookmarkEntity>> {
        return localDataSource.getBookmarkList()
    }

    fun checkBookmark(query: String): Flow<Int> = localDataSource.checkBookmark(query = query)

    suspend fun addToBookmark(entity: VaccineBookmarkEntity) {
        localDataSource.addToBookmark(entity = entity)
    }

    suspend fun removeFromBookmark(entity: VaccineBookmarkEntity) {
        localDataSource.removeFromBookmark(entity = entity)
    }

}