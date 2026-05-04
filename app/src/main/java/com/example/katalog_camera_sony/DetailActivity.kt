package com.example.katalog_camera_sony

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val TAG = "42430018"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.i(TAG, "DetailActivity dibuka")

        try {
            val kamera = intent.getSerializableExtra("kamera") as? Kamera

            val ivDetailKamera: ImageView = findViewById(R.id.ivDetailKamera)
            val tvDetailNama: TextView = findViewById(R.id.tvDetailNama)
            val tvDetailTipe: TextView = findViewById(R.id.tvDetailTipe)
            val tvDetailResolusi: TextView = findViewById(R.id.tvDetailResolusi)
            val tvDetailSensor: TextView = findViewById(R.id.tvDetailSensor)
            val tvDetailVideo: TextView = findViewById(R.id.tvDetailVideo)
            val tvDetailHarga: TextView = findViewById(R.id.tvDetailHarga)
            val tvDetailDeskripsi: TextView = findViewById(R.id.tvDetailDeskripsi)
            val tvDetailFitur: TextView = findViewById(R.id.tvDetailFitur)
            val btnBack: ImageButton = findViewById(R.id.btnBack)

            if (kamera == null) {
                Log.w(TAG, "Data kamera tidak ditemukan, menutup halaman detail")
                Toast.makeText(this, "Data kamera tidak tersedia", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            Log.i(TAG, "Menampilkan detail kamera: ${kamera.nama} (${kamera.tipe})")

            val resId = resources.getIdentifier(kamera.gambar, "drawable", packageName)
            ivDetailKamera.setImageResource(if (resId != 0) resId else R.drawable.ic_placeholder)

            tvDetailNama.text = kamera.nama
            tvDetailTipe.text = kamera.tipe
            tvDetailResolusi.text = kamera.resolusi
            tvDetailSensor.text = kamera.sensor
            tvDetailVideo.text = kamera.video
            tvDetailHarga.text = kamera.harga
            tvDetailDeskripsi.text = kamera.deskripsi
            tvDetailFitur.text = kamera.fitur

            Log.i(TAG, "Detail kamera berhasil ditampilkan: ${kamera.nama}")

            btnBack.setOnClickListener {
                Log.i(TAG, "Tombol back ditekan, kembali ke daftar kamera")
                finish()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error saat memuat detail kamera: ${e.message}")
            Toast.makeText(this, "Terjadi kesalahan saat memuat data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
