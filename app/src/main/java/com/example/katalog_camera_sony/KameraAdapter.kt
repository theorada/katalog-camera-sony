package com.example.katalog_camera_sony

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KameraAdapter(
    private val daftarKamera: MutableList<Kamera>,
    private val onItemClick: (Kamera) -> Unit
) : RecyclerView.Adapter<KameraAdapter.KameraViewHolder>() {

    inner class KameraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivKamera: ImageView = itemView.findViewById(R.id.ivKamera)
        val tvNamaKamera: TextView = itemView.findViewById(R.id.tvNamaKamera)
        val tvTipe: TextView = itemView.findViewById(R.id.tvTipe)
        val tvResolusi: TextView = itemView.findViewById(R.id.tvResolusi)
        val tvSensor: TextView = itemView.findViewById(R.id.tvSensor)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KameraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kamera, parent, false)
        return KameraViewHolder(view)
    }

    override fun onBindViewHolder(holder: KameraViewHolder, position: Int) {
        val kamera = daftarKamera[position]

        holder.tvNamaKamera.text = kamera.nama
        holder.tvTipe.text = kamera.tipe
        holder.tvResolusi.text = kamera.resolusi
        holder.tvSensor.text = kamera.sensor
        holder.tvHarga.text = kamera.harga

        when (kamera.tipe) {
            "Mirrorless" -> {
                holder.tvTipe.setBackgroundResource(R.drawable.bg_badge_mirrorless)
                holder.tvTipe.setTextColor(holder.itemView.context.getColor(R.color.badge_mirrorless))
            }
            "DSLR" -> {
                holder.tvTipe.setBackgroundResource(R.drawable.bg_badge_dslr)
                holder.tvTipe.setTextColor(holder.itemView.context.getColor(R.color.badge_dslr))
            }
            "Pocket" -> {
                holder.tvTipe.setBackgroundResource(R.drawable.bg_badge_pocket)
                holder.tvTipe.setTextColor(holder.itemView.context.getColor(R.color.badge_pocket))
            }
            "Vlog" -> {
                holder.tvTipe.setBackgroundResource(R.drawable.bg_badge_vlog)
                holder.tvTipe.setTextColor(holder.itemView.context.getColor(R.color.badge_vlog))
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick(kamera)
        }
    }

    override fun getItemCount(): Int = daftarKamera.size
}
