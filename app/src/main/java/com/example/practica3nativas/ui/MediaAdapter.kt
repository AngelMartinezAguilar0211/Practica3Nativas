package com.example.practica3nativas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practica3nativas.R
import java.io.File

class MediaAdapter(
    private val files: List<File>,
    private val onImageClick: (File) -> Unit,
    private val onAudioClick: (File) -> Unit
) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val file = files[position]
        holder.bind(file)
    }

    override fun getItemCount(): Int = files.size

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val label: TextView = itemView.findViewById(R.id.mediaLabel)

        fun bind(file: File) {
            label.text = file.name
            if (file.extension == "jpg") {
                Glide.with(itemView.context).load(file).into(imageView)
                itemView.setOnClickListener { onImageClick(file) }
            } else if (file.extension == "3gp") {
                imageView.setImageResource(R.drawable.ic_audio) // ícono representativo de audio
                itemView.setOnClickListener { onAudioClick(file) }
            }
        }
    }
}