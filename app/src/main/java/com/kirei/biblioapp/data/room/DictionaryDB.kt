package com.kirei.biblioapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kirei.biblioapp.data.room.dao.WordDao
import com.kirei.biblioapp.data.room.entity.WordEntity


@Database(entities = [WordEntity::class], version = 1)
abstract class DictionaryDB : RoomDatabase(){

    abstract fun getWordDao(): WordDao
}