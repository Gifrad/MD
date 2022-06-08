package com.capstone.project.trashhub.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.trashhub.R
import com.capstone.project.trashhub.network.model.ListBankSampah

class ListBankSampahAdapter: RecyclerView.Adapter<ListBankSampahAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<ListBankSampah>()

    fun setList(users: ArrayList<ListBankSampah>) {
        val diffCallback = BankSampahDiffCallback(this.list, users)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(users)
        diffResult.dispatchUpdatesTo(this)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tv_name_bank_sampah)
        private var tvLocation: TextView = itemView.findViewById(R.id.tv_location)

        fun bind(list: ListBankSampah) {
            with(itemView) {
                Log.d("ListBankSampahAdapter", "bind: ${list.name}")
                tvName.text = list.name
                tvLocation.text = list.street
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bank_sampah, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListBankSampah)
    }


}

class BankSampahDiffCallback(
    private val mOldUserList: ArrayList<ListBankSampah>,
    private val mNewUserList: ArrayList<ListBankSampah>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].id == mNewUserList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mNewUserList[newItemPosition]
        return oldEmployee.id == newEmployee.id
    }
}
