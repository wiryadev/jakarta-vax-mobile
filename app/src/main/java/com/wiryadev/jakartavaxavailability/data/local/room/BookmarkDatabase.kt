package com.wiryadev.jakartavaxavailability.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wiryadev.jakartavaxavailability.data.local.entity.VaccineBookmarkEntity

@Database(
    entities = [VaccineBookmarkEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun dao(): BookmarkDao

}