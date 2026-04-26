package com.example.katalog_camera_sony

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnSortAZ: Button
    private lateinit var btnSortZA: Button
    private lateinit var tvCounter: TextView
    private lateinit var layoutEmpty: View

    private lateinit var adapter: KameraAdapter
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

        adapter = KameraAdapter(daftarKamera) { kamera ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("kamera", kamera)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        updateCounter()

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isEmpty()) {
                Toast.makeText(this, getString(R.string.validation_empty), Toast.LENGTH_SHORT).show()
            } else {
                // Logika Linear Search akan diimplementasikan pada Minggu 3
                Toast.makeText(this, "Mencari: \"$query\"", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Bubble Sort akan diimplementasikan pada Minggu 3
        btnSortAZ.setOnClickListener {
            Toast.makeText(this, "Sort A-Z akan tersedia pada Minggu 3", Toast.LENGTH_SHORT).show()
        }

        btnSortZA.setOnClickListener {
            Toast.makeText(this, "Sort Z-A akan tersedia pada Minggu 3", Toast.LENGTH_SHORT).show()
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
