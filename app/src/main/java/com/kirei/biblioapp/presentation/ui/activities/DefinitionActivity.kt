package com.kirei.biblioapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModel
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModelFactory
import com.kirei.biblioapp.R
import com.kirei.biblioapp.data.response.Word
import com.kirei.biblioapp.databinding.ActivityDefinitionBinding
import com.kirei.biblioapp.data.utils.Constants

class DefinitionActivity : AppCompatActivity() {

    private lateinit var dictionaryViewModel: DictionaryViewModel
    private val dictionaryViewModelFactory by inject<DictionaryViewModelFactory>()

    private lateinit var word: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDefinitionBinding = DataBindingUtil.setContentView(this, R.layout.activity_definition)
        dictionaryViewModel = ViewModelProviders.of(this, dictionaryViewModelFactory).get(DictionaryViewModel::class.java)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val gson = Gson()
        val wordJsonBody = intent.getStringExtra(Constants.INTENT_WORD_JSON)

        word = gson.fromJson(wordJsonBody, Word::class.java)



    }
}