package com.gautam.mynotes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gautam.mynotes.databinding.ActivityMainBinding.bind
import com.gautam.mynotes.databinding.NotesCardLayoutBinding
import com.gautam.mynotes.room.Notes




class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var data = emptyList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =
            NotesCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = data[position]

        holder.binding.tvTitle.text = notes.noteTitle
        holder.binding.tvDesc.text = notes.notesDesc

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class NotesViewHolder(val binding: NotesCardLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)

    fun setData(notes : List<Notes>) {
        this.data = notes
        notifyDataSetChanged()
    }


}
