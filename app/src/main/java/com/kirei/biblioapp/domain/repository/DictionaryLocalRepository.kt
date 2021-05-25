package com.kirei.biblioapp.domain.repository


import com.kirei.biblioapp.data.room.dao.WordDao
import com.kirei.biblioapp.data.room.entity.WordEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*

class DictionaryLocalRepository (private val wordDao: WordDao){


    //we add the suspend modifier to the function to get word entries
    //the suspend modifier tells the compiler that this function needs to be executed inside a coroutine
    suspend fun getAllWordEntries(): List<WordEntity> {
        return withContext(Dispatchers.Default) {
            wordDao.getWordList()
        }
    }

    // get word by id
    suspend fun getWordById(defId: Int): WordEntity? {
        return withContext(Dispatchers.Default) {
            wordDao.getWordByDefId(defId)
        }
    }


    fun insertWord(wordEntity: WordEntity): Job {
        return GlobalScope.launch {
            wordDao.saveWord(wordEntity)
        }
    }



}