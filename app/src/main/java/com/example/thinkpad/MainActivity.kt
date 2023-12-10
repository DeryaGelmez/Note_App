package com.example.thinkpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thinkpad.Adapter.NotesAdapter
import com.example.thinkpad.Database.NoteDatabase
import com.example.thinkpad.Models.Note
import com.example.thinkpad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
private lateinit var database: NoteDatabase
lateinit var viewModel: NoteViewModel
lateinit var adapter: NotesAdapter
lateinit var selectedNote:Note


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}