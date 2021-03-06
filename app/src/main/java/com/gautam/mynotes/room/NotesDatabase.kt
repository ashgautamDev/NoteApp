package com.gautam.mynotes.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gautam.mynotes.modle.Notes


@Database(entities = [Notes::class], version = 4, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

  companion object {

          @Volatile
          private var INSTANCE: NotesDatabase? = null

          fun getDatabase(context: Context): NotesDatabase {
              synchronized(this) {
                  var instance = INSTANCE

                  if (instance == null) {
                      instance = Room.databaseBuilder(
                          context.applicationContext,
                          NotesDatabase::class.java,
                          "notes_database"
                      ).fallbackToDestructiveMigration()
                          .build()
                      INSTANCE = instance
                  }
                  return instance
              }
          }
      }

}

