package com.example.katalog_camera_sony

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val kamera = intent.getSerializableExtra("kamera") as? Kamera

        val tvDetailNama: TextView = findViewById(R.id.tvDetailNama)
        val tvDetailTipe: TextView = findViewById(R.id.tvDetailTipe)
        val tvDetailResolusi: TextView = findViewById(R.id.tvDetailResolusi)
        val tvDetailSensor: TextView = findViewById(R.id.tvDetailSensor)
        val tvDetailVideo: TextView = findViewById(R.id.tvDetailVideo)
        val tvDetailHarga: TextView = findViewById(R.id.tvDetailHarga)
        val tvDetailDeskripsi: TextView = findViewById(R.id.tvDetailDeskripsi)
        val tvDetailFitur: TextView = findViewById(R.id.tvDetailFitur)
        val btnBack: ImageButton = findViewById(R.id.btnBack)

        kamera?.let {
            tvDetailNama.text = it.nama
            tvDetailTipe.text = it.tipe
            tvDetailResolusi.text = it.resolusi
            tvDetailSensor.text = it.sensor
            tvDetailVideo.text = it.video
            tvDetailHarga.text = it.harga
            tvDetailDeskripsi.text = it.deskripsi
            tvDetailFitur.text = it.fitur
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
