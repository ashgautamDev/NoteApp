package com.gautam.mynotes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gautam.mynotes.databinding.NotesCardLayoutBinding
import com.gautam.mynotes.modle.Notes
import com.gautam.mynotes.screens.notes.NotesFragmentDirections
import java.lang.Float.min
import kotlin.math.min


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: NotesCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
      private  val MARGIN_SIZE = 10
    }

    private val differCallback = object : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NotesCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val cardwidth = parent.width /  - (MARGIN_SIZE * 2)
//        val cardheight = parent.height
//        val cardsidelength = min(cardwidth, cardheight)
//        val layoutParameters = view.cardViewNotes.layoutParams as ViewGroup.MarginLayoutParams
//        layoutParameters.width = cardsidelength
//        layoutParameters.height = cardsidelength
//        layoutParameters.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes = differ.currentList[position]

        holder.binding.tvTitle.text = currentNotes.noteTitle.toString()
        holder.binding.tvDesc.text = currentNotes.notesDesc.toString()

        holder.itemView.setOnClickListener {
val action = NotesFragmentDirections.actionNotesFragmentToDisplayFragment(currentNotes)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
