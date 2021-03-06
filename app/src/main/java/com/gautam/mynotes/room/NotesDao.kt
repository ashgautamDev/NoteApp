package com.gautam.mynotes.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gautam.mynotes.modle.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Query("SELECT * FROM `Notes Table` ORDER BY noteId ASC ")
    fun getAllNotes() : LiveData<List<Notes>>




}