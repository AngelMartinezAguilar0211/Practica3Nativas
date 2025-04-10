package com.example.practica3nativas.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.practica3nativas.databinding.FragmentAudioBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AudioFragment : Fragment() {

    private lateinit var binding: FragmentAudioBinding
    private var recorder: MediaRecorder? = null
    private var outputFile: String = ""
    private var isRecording = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 101)
        }

        binding.btnRecord.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                startRecording()
            }
        }
    }

    private fun startRecording() {
        outputFile = File(requireContext().externalMediaDirs.first(),
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date()) + ".3gp").absolutePath

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            prepare()
            start()
        }

        isRecording = true
        binding.btnRecord.text = "Detener"
        Toast.makeText(context, "Grabando...", Toast.LENGTH_SHORT).show()
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        isRecording = false
        binding.btnRecord.text = "Grabar"
        Toast.makeText(context, "Grabación guardada: $outputFile", Toast.LENGTH_SHORT).show()
    }

    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        recorder?.release()
        recorder = null
    }
}