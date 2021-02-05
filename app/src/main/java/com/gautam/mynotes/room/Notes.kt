package com.gautam.mynotes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes Table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int,
    @ColumnInfo(name = "Title of Notes")
    val noteTitle: String,
    @ColumnInfo(name = "Description of Notes")
    val notesDesc: String
) : Serializable {

}
