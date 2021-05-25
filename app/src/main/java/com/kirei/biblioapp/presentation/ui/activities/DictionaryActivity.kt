package com.kirei.biblioapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirei.biblioapp.*
import com.kirei.biblioapp.presentation.ui.adapters.DictionaryAdapter
import com.kirei.biblioapp.databinding.ActivityDictionaryBinding
import com.kirei.biblioapp.data.response.Word
import com.kirei.biblioapp.data.utils.ERROR_STATUS
import com.kirei.biblioapp.data.utils.ErrorHelper
import com.kirei.biblioapp.data.utils.Intent1
import com.kirei.biblioapp.data.utils.observeWith
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModel
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModelFactory


class DictionaryActivity : AppCompatActivity() {

    private lateinit var dictionaryViewModel: DictionaryViewModel
    private lateinit var binding : ActivityDictionaryBinding

    private val dictionaryViewModelFactory by inject<DictionaryViewModelFactory>()

    private lateinit var dictionaryAdapter: DictionaryAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_dictionary)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dictionary)

        dictionaryViewModel = ViewModelProviders.of(this,dictionaryViewModelFactory ).get(DictionaryViewModel::class.java)
        recycler_word.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        dictionaryAdapter = DictionaryAdapter()
        recycler_word.adapter = dictionaryAdapter


        dictionaryViewModel.progress.observeWith(this, this::onProgress)
        dictionaryViewModel.uiModel.observeWith(this, this:: onSuccess)
        dictionaryViewModel.errorBase.observeWith(this, this::onError)


        binding.searchButton
            .setOnClickListener{
                dictionaryViewModel.getDefinition(word_search.text.toString().trim())

            }


        dictionaryAdapter.setOnClickListener(object : DictionaryAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Word) {
                startActivity(Intent1(data))
            }


        })

    }

    private fun onSuccess(data: List<Word>){
        display(this)
        dictionaryAdapter.addWord(data)
    }

    private fun display(activity: Activity){
        val methodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        methodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun onProgress(isShown: Boolean){
        progress_search_bar.visibility =
            if(isShown) View.VISIBLE
            else View.GONE
    }

    // Menu Item
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)

    }

    private fun onError(errorHelper: ErrorHelper) = when (errorHelper.errorStatus) {
        ERROR_STATUS.ERR -> {
            Toast.makeText(this, "Sorry word does not exist, you can create it :)", Toast.LENGTH_SHORT).show()
        }
        ERROR_STATUS.NETWORK -> {
            Toast.makeText(this, getString(R.string.network_issue), Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show()
        }
    }
}

