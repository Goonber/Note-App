package com.example.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    private var notes by remember { mutableStateOf(listOf<Note>()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                NoteListScreen(
                    notes = notes,
                    onNoteClick = { /* Handle note click */ },
                    onAddNoteClick = { /* Handle add note click */ }
                )
            }
        }
    }

    private fun editNote(position: Int, updatedNote: Note) {
        val validationError = getValidationError(updatedNote)
        if (validationError != null) {
            showErrorMessage(validationError)
        } else {
            notes = notes.toMutableList().also { it[position] = updatedNote }
        }
    }

    private fun deleteNote(position: Int) {
        notes = notes.toMutableList().also { it.removeAt(position) }
    }

    private fun getNote(position: Int): Note {
        return notes[position]
    }

    private fun isValidNote(note: Note): Boolean {
        val titleLength = note.title.length
        val textLength = note.text.length
        val isTitleValid = titleLength in 3..50
        val isTextValid = textLength <= 120
        return isTitleValid && isTextValid
    }

    private fun getValidationError(note: Note): String? {
        val titleLength = note.title.length
        val textLength = note.text.length

        if (titleLength < 3) {
            return "Title is too short (minimum 3 characters)."
        } else if (titleLength > 50) {
            return "Title is too long (maximum 50 characters)."
        } else if (textLength > 120) {
            return "Text is too long (maximum 120 characters)."
        }

        return null
    }

    private fun showErrorMessage(errorMessage: String) {
        val density = LocalDensity.current.density
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }
}
