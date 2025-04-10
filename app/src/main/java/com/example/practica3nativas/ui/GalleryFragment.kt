package com.example.practica3nativas.ui
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practica3nativas.databinding.FragmentGalleryBinding
import java.io.File

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: MediaAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mediaFiles = requireContext().externalMediaDirs.first().listFiles()?.toList() ?: emptyList()
        adapter = MediaAdapter(mediaFiles,
            onImageClick = { file ->
                // Aquí podrías implementar vista previa y edición básica de imágenes
                Toast.makeText(requireContext(), "Imagen: ${file.name}", Toast.LENGTH_SHORT).show()
            },
            onAudioClick = { file ->
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(file.absolutePath)
                    prepare()
                    start()
                }
            }
        )

        binding.recyclerGallery.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerGallery.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}