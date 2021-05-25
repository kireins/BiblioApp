package com.kirei.biblioapp.presentation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kirei.biblioapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //open Dictionary activity class to display the urban dictionary content
        val openDictionaryButton = findViewById<Button>(R.id.open_dictionary_button)
        openDictionaryButton.setOnClickListener{
            val intent = Intent(this, DictionaryActivity::class.java )
            startActivity(intent)
        }
    }

}