package com.kirei.biblioapp.data.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.kirei.biblioapp.data.room.entity.WordEntity
import retrofit2.http.DELETE
import retrofit2.http.Query
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface WordDao {


    @Query("SELECT * FROM word")
    fun getWordList(): List<WordEntity>

    @Query("SELECT * FROM word WHERE word.defId==:defId")
    fun getWordByDefId(defId: Int): WordEntity?

    @Query("DELETE FROM word WHERE word.defId==:id")
    fun deleteById(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWord(wordEntity: WordEntity)

    @Update(onConflict = REPLACE)
    fun updateWord(wordEntity: WordEntity): Int

    @DELETE
    fun delete(wordEntity: WordEntity)

}
