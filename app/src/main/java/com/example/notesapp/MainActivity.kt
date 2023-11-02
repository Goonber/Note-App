package com.example.notesapp
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val notes = mutableListOf<Note>()
    private fun addNote(note: Note) {
        val validationError = getValidationError(note)
        if (validationError != null) {
            showErrorMessage(validationError)
        } else {
            notes.add(note)
            adapter.notifyItemInserted(notes.size - 1)
        }
    }

    private fun editNote(position: Int, updatedNote: Note) {
        val validationError = getValidationError(updatedNote)
        if (validationError != null) {
            // Handle the validation error, e.g., display an error message to the user
            // Show the error message to the user
            showErrorMessage(validationError)
        } else {
            notes[position] = updatedNote
            adapter.notifyItemChanged(position)
        }
    }

    private fun deleteNote(position: Int) {
        notes.removeAt(position)
        adapter.notifyItemRemoved(position)
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
        val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        val view = toast.view
        view.setBackgroundColor(resources.getColor(R.color.colorPrimary)) // Change the background color
        toast.setGravity(Gravity.TOP, 0, 0) // Set the gravity to top
        toast.show()
    }

}

data class Note(
    val title: String,
    val text: String
)



