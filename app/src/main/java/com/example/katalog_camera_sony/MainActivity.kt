package com.example.katalog_camera_sony

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val TAG = "2200000"

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnSortAZ: Button
    private lateinit var btnSortZA: Button
    private lateinit var tvCounter: TextView
    private lateinit var layoutEmpty: View

    private lateinit var adapter: KameraAdapter
    private val daftarSemua = mutableListOf<Kamera>()
    private val daftarKamera = mutableListOf<Kamera>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        btnSortAZ = findViewById(R.id.btnSortAZ)
        btnSortZA = findViewById(R.id.btnSortZA)
        tvCounter = findViewById(R.id.tvCounter)
        layoutEmpty = findViewById(R.id.layoutEmpty)

        inisialisasiData()

        daftarKamera.addAll(daftarSemua)

        adapter = KameraAdapter(daftarKamera) { kamera ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("kamera", kamera)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        updateCounter()
        Log.i(TAG, "Aplikasi dimulai. Total data kamera: ${daftarSemua.size}")

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isEmpty()) {
                Toast.makeText(this, getString(R.string.validation_empty), Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Pencarian gagal: kolom kosong")
            } else {
                linearSearch(query)
            }
        }

        btnSortAZ.setOnClickListener {
            try {
                bubbleSort(ascending = true)
                adapter.notifyDataSetChanged()
                updateCounter()
                Toast.makeText(this, "Diurutkan A → Z", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Sorting A-Z berhasil. Urutan pertama: ${daftarKamera.firstOrNull()?.nama}")
            } catch (e: Exception) {
                Log.e(TAG, "Error saat sorting A-Z: ${e.message}")
            }
        }

        btnSortZA.setOnClickListener {
            try {
                bubbleSort(ascending = false)
                adapter.notifyDataSetChanged()
                updateCounter()
                Toast.makeText(this, "Diurutkan Z → A", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Sorting Z-A berhasil. Urutan pertama: ${daftarKamera.firstOrNull()?.nama}")
            } catch (e: Exception) {
                Log.e(TAG, "Error saat sorting Z-A: ${e.message}")
            }
        }
    }

    private fun inisialisasiData() {
        daftarSemua.add(
            Kamera(
                nama = "Sony Alpha A1",
                tipe = "Mirrorless",
                resolusi = "50.1 MP",
                sensor = "Full-Frame BSI-CMOS",
                video = "8K 30fps / 4K 120fps",
                harga = "Rp 89.999.000",
                deskripsi = "Kamera mirrorless flagship Sony dengan resolusi 50MP dan kemampuan video 8K. Dirancang untuk fotografer dan videografer profesional kelas dunia.",
                fitur = "50MP BSI-CMOS, 8K 30fps, 30fps burst, 759 phase-detect AF, 5-axis IBIS, Dual CFexpress Type A",
                gambar = "img_sony_a1"
            )
        )
        daftarSemua.add(
            Kamera(
                nama = "Sony Alpha A7 IV",
                tipe = "Mirrorless",
                resolusi = "33 MP",
                sensor = "Full-Frame BSI-CMOS",
                video = "4K 60fps",
                harga = "Rp 34.999.000",
                deskripsi = "Kamera mirrorless full-frame generasi keempat dengan sensor 33MP BSI-CMOS terbaru. Pilihan ideal untuk fotografer profesional dan videografer serba bisa.",
                fitur = "33MP BSI-CMOS, 4K 60fps, 10fps burst, Real-time Eye AF, 5-axis IBIS, Wi-Fi & Bluetooth",
                gambar = "img_sony_a7iv"
            )
        )
        daftarSemua.add(
            Kamera(
                nama = "Sony Alpha A6700",
                tipe = "Mirrorless",
                resolusi = "26 MP",
                sensor = "APS-C BSI-CMOS",
                video = "4K 120fps",
                harga = "Rp 21.499.000",
                deskripsi = "Kamera mirrorless APS-C flagship Sony dengan teknologi AI AF terdepan dan kemampuan video 4K 120fps tanpa crop. Ringan dan kompak untuk dibawa ke mana saja.",
                fitur = "AI Subject Recognition AF, 4K 120fps, 11fps burst, 5-axis IBIS, 759 phase-detect AF, Parabolic microphone",
                gambar = "img_sony_a6700"
            )
        )
        daftarSemua.add(
            Kamera(
                nama = "Sony RX100 VII",
                tipe = "Pocket",
                resolusi = "20.1 MP",
                sensor = "1.0\" Stacked CMOS",
                video = "4K 30fps",
                harga = "Rp 19.999.000",
                deskripsi = "Kamera saku premium dengan sensor 1 inci dan sistem autofokus Eye AF tercepat di kelasnya. Desain kompak namun bertenaga untuk berbagai situasi pemotretan.",
                fitur = "357 phase-detect AF, 20fps burst, Real-time Eye AF, Pop-up EVF, 3.5mm microphone jack, NFC & Wi-Fi",
                gambar = "img_sony_rx100vii"
            )
        )
        daftarSemua.add(
            Kamera(
                nama = "Sony ZV-1F",
                tipe = "Vlog",
                resolusi = "12.1 MP",
                sensor = "1/2.3\" CMOS",
                video = "4K 30fps",
                harga = "Rp 6.799.000",
                deskripsi = "Kamera vlog ringkas dengan lensa ultra-wide 18mm yang dirancang khusus untuk konten kreator dan vlogger. Mudah digunakan dengan fitur otomatis canggih.",
                fitur = "Ultra-wide 18mm f/2.8, Background Defocus, Product Showcase Setting, Face Auto Zoom, Directional microphone",
                gambar = "img_sony_zv1f"
            )
        )
        Log.i(TAG, "Data kamera berhasil dimuat: ${daftarSemua.size} item")
    }

    private fun linearSearch(query: String) {
        try {
            val hasil = mutableListOf<Kamera>()
            for (kamera in daftarSemua) {
                if (kamera.nama.contains(query, ignoreCase = true)) {
                    hasil.add(kamera)
                }
            }
            daftarKamera.clear()
            daftarKamera.addAll(hasil)
            adapter.notifyDataSetChanged()
            updateCounter()

            if (hasil.isEmpty()) {
                Toast.makeText(this, "Kamera \"$query\" tidak ditemukan", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Pencarian \"$query\": tidak ada hasil")
            } else {
                Toast.makeText(this, "${hasil.size} kamera ditemukan", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Pencarian \"$query\": ditemukan ${hasil.size} hasil")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error saat pencarian: ${e.message}")
        }
    }

    private fun bubbleSort(ascending: Boolean) {
        val n = daftarKamera.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - 1 - i) {
                val perlu_tukar = if (ascending) {
                    daftarKamera[j].nama > daftarKamera[j + 1].nama
                } else {
                    daftarKamera[j].nama < daftarKamera[j + 1].nama
                }
                if (perlu_tukar) {
                    val temp = daftarKamera[j]
                    daftarKamera[j] = daftarKamera[j + 1]
                    daftarKamera[j + 1] = temp
                }
            }
        }
    }

    private fun updateCounter() {
        tvCounter.text = "${daftarKamera.size} ${getString(R.string.total_kamera)}"
        if (daftarKamera.isEmpty()) {
            recyclerView.visibility = View.GONE
            layoutEmpty.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
        }
    }
}
