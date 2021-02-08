package com.gautam.mynotes.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gautam.mynotes.getOrAwaitValue
import com.gautam.mynotes.modle.Notes
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: NotesDatabase
    private lateinit var dao: NotesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.notesDao()


    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insetingNotesInDatabase() = runBlockingTest {

        val notes = Notes(1, "Ashish", "Gautam")
        dao.insertNote(notes)

        val allNotes = dao.getAllNotes().getOrAwaitValue()
        assertThat(allNotes).contains(notes)

    }
    @Test
    fun deletingNotesInDatabase() = runBlockingTest {

        val notes = Notes(1, "Ashish", "Gautam")
        dao.insertNote(notes)
        dao.deleteNote(notes)

        val allNotes = dao.getAllNotes().getOrAwaitValue()
        assertThat(allNotes).doesNotContain(notes)
    }

}