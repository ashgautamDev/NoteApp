package com.gautam.mynotes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes Table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = 0,
    @ColumnInfo(name = "Title of Notes")
    val noteTitle: String,
    @ColumnInfo(name = "Optionall Description")
    val notesDesc: String? = null
)
