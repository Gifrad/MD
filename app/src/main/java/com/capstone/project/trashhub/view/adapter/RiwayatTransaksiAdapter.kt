package com.capstone.project.trashhub.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.project.trashhub.R
import com.capstone.project.trashhub.network.model.ListBankSampah
import com.capstone.project.trashhub.network.model.RiwayatPesanan
import com.capstone.project.trashhub.network.model.RiwayatPesanan2
import de.hdodenhof.circleimageview.CircleImageView

class RiwayatTransaksiAdapter(private val listBankSampah: ArrayList<RiwayatPesanan2>): RecyclerView.Adapter<RiwayatTransaksiAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: ListBankSampahAdapter.OnItemClickCallback

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var namaBankSampah : TextView = itemView.findViewById(R.id.tv_name_bank_sampah_history)
        var lokasiBankSampah : TextView = itemView.findViewById(R.id.tv_lokasi_bank_sampah_history)
        var durasiPengangkutan : TextView = itemView.findViewById(R.id.tv_durasi_pengangkutan_bank_sampah_history)
//        var imageBankSampah : CircleImageView = itemView.findViewById(R.id.image_bank_sampah_history)

    }

//    fun setList(users: ArrayList<RiwayatPesanan>) {
//        val diffCallback = RiwayatPesananDiffCallback(this.list, users)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        list.clear()
//        list.addAll(users)
//        diffResult.dispatchUpdatesTo(this)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val riwayat = listBankSampah.get(position)
        holder.namaBankSampah.text = riwayat.name
        holder.lokasiBankSampah.text = riwayat.alamatBankSampah
        holder.durasiPengangkutan.text = riwayat.durasiTransaksi
//        Glide.with(holder.itemView)
//            .load(riwayat.fotoBankSampah)
//            .into(holder.imageBankSampah)
    }

    override fun getItemCount(): Int = listBankSampah.size
}


//class RiwayatPesananDiffCallback(
//    private val mOldUserList: ArrayList<RiwayatPesanan>,
//    private val mNewUserList: ArrayList<RiwayatPesanan>
//) : DiffUtil.Callback() {
//    override fun getOldListSize(): Int {
//        return mOldUserList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return mNewUserList.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return mOldUserList[oldItemPosition].idBankSampah == mNewUserList[newItemPosition].idBankSampah
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldEmployee = mOldUserList[oldItemPosition]
//        val newEmployee = mNewUserList[newItemPosition]
//        return oldEmployee.idBankSampah == newEmployee.idBankSampah
//    }
//}
