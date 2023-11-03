package com.example.notesapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (Note) -> Unit
) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note = note, onNoteClick)
        }
    }
}
