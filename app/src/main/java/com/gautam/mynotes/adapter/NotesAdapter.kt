package com.gautam.mynotes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gautam.mynotes.databinding.NotesCardLayoutBinding
import com.gautam.mynotes.room.Notes


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    private val binding : NotesCardLayoutBinding? = null

    class NotesViewHolder(val binding : NotesCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Notes>() {
            override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem.noteId == newItem.noteId
            }

            override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
      val view = NotesCardLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
       holder.itemView.apply {
           binding?.tvTitle?.text = currentNote.noteTitle
           binding?.tvDesc?.text = currentNote.notesDesc
       }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}