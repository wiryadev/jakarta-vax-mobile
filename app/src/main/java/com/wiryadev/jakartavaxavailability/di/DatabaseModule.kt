package com.wiryadev.jakartavaxavailability.di

import android.content.Context
import androidx.room.Room
import com.wiryadev.jakartavaxavailability.data.local.room.BookmarkDao
import com.wiryadev.jakartavaxavailability.data.local.room.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): BookmarkDatabase = Room
        .databaseBuilder(context, BookmarkDatabase::class.java, "bookmark.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDao(db: BookmarkDatabase): BookmarkDao = db.dao()

}