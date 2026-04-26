package com.example.katalog_camera_sony

import java.io.Serializable

data class Kamera(
    val nama: String,
    val tipe: String,
    val resolusi: String,
    val sensor: String,
    val video: String,
    val harga: String,
    val deskripsi: String,
    val fitur: String
) : Serializable
