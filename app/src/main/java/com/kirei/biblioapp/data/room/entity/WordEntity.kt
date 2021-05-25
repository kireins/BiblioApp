package com.kirei.biblioapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word")
class WordEntity{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var definition: String? = null
    var thumbsUpNumber: Int? = null
    var defId: Int? = null
    var authorName: String? = null
    var word: String? = null
    var writtenOn: String? = null
    var example: String? = null
    var thumbsDownNumber: Int? = null



}
