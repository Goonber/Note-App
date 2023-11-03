package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NoteItem(
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNoteClick(note) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = note.title, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.text, fontSize = 14.sp, fontWeight = FontWeight.Normal)
        }
    }
}
