package com.kirei.biblioapp.presentation.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kirei.biblioapp.data.response.BaseResponse
import com.kirei.biblioapp.CoroutinesContextProvider
import com.kirei.biblioapp.data.response.Word
import com.kirei.biblioapp.data.room.entity.WordEntity
import com.kirei.biblioapp.data.response.Results
import com.kirei.biblioapp.domain.usecases.DictionaryLocalUseCase
import com.kirei.biblioapp.domain.usecases.DictionaryUseCase
import com.kirei.biblioapp.data.utils.ErrorHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DictionaryViewModel(private val dictionaryUseCase: DictionaryUseCase,
                          private val dictionaryLocalUseCase: DictionaryLocalUseCase,

                          private val coroutinesContextProvider: CoroutinesContextProvider
) : MainViewModel() {



    private val mutableLiveData = MutableLiveData<List<Word>>()
    val uiModel: LiveData<List<Word>>
        get() = mutableLiveData


    fun getDefinition(word: String) {
        progress.postValue(true)
        launchDefinition(word)
    }


    // I can use this for more functionalities like adding favorite and test when data added
    fun insertOrUpdateWord(word: Word) {

        val wordDB = WordEntity()
        wordDB.authorName = word.authorName
        wordDB.defId = word.defId
        wordDB.definition = word.definition
        wordDB.example = word.example
        wordDB.thumbsDownNumber = word.thumbsDownNumber
        wordDB.thumbsUpNumber = word.thumbsUpNumber
        wordDB.word = word.word
        wordDB.writtenOn = word.writtenOn

    }


    fun getAllWords() {
        launchGetAllWords()
    }

    private fun launchGetAllWords() =
        GlobalScope.launch(coroutinesContextProvider.io) {
            val result = dictionaryLocalUseCase.getAllWords()
            withContext(coroutinesContextProvider.io) { onResultWords(result) }
        }

    private fun onResultWords(result: List<WordEntity>) {
        val listWords = ArrayList<Word>()
        result.forEach {
            val word = Word(
                it.definition!!,
                "",
                it.thumbsUpNumber!!,
                it.defId!!,
                it.authorName!!,
                it.word!!,
                it.writtenOn!!,
                ArrayList(),
                it.example!!,
                it.thumbsDownNumber!!
            )
            listWords.add(word)
        }
    }

    private fun launchDefinition(word: String) = GlobalScope.launch(coroutinesContextProvider.io) {
        val result = dictionaryUseCase(word)
        withContext(coroutinesContextProvider.io) { onResult(result) }
    }


    private fun onResult(results: Results<BaseResponse>) {
        progress.postValue(false)
        when (results) {
            is Results.Success -> {
                model(results.data)
            }
            is Results.Error -> {
                errorBase.postValue(results.exception as ErrorHelper?)
            }
        }
    }
    private fun model(data: BaseResponse) =
        GlobalScope.launch(coroutinesContextProvider.main) {
            mutableLiveData.value = data.wordList
        }


}
