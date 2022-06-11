package com.capstone.project.trashhub.view.riwayat

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.project.trashhub.databinding.ActivityRiwayatBinding
import com.capstone.project.trashhub.network.model.ListBankSampah
import com.capstone.project.trashhub.network.model.RiwayatPesanan
import com.capstone.project.trashhub.network.model.RiwayatPesanan2
import com.capstone.project.trashhub.view.adapter.RiwayatTransaksiAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class RiwayatActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var fireStore : FirebaseFirestore
    private lateinit var databaseReferences : DatabaseReference
    private lateinit var riwayatViewModel : RiwayatViewModel
    private lateinit var riwayatBinding: ActivityRiwayatBinding
    private lateinit var riwayatPesanan: ArrayList<RiwayatPesanan2>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        riwayatBinding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(riwayatBinding.root)

        auth = FirebaseAuth.getInstance()
        databaseReferences = FirebaseDatabase.getInstance().getReference("transaksi")
        fireStore = FirebaseFirestore.getInstance()

        riwayatPesanan = ArrayList<RiwayatPesanan2>()
        riwayatBinding.apply {
            recyclerviewRiwayat.setHasFixedSize(true)
            recyclerviewRiwayat.setLayoutManager(LinearLayoutManager(this@RiwayatActivity))
        }

        setupViewModel()
    }
    private fun setupViewModel() {
        riwayatViewModel = ViewModelProvider(this)[RiwayatViewModel::class.java]

        riwayatViewModel.listBankSampah.observe(this) {
            if (it != null) {
                getAdapter(it)
                Log.d(ContentValues.TAG, "onCreate: ${it[1]}")
            }
        }

        riwayatViewModel.getBankSampah()

    }


    private fun getAdapter(listBankSampah: ArrayList<ListBankSampah>) {

        for (i in listBankSampah){
            databaseReferences
                .addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            for (it in snapshot.children){
//                                if (it.equals())
                                val riwayatPesanan2 = it.getValue(RiwayatPesanan2::class.java)
                                riwayatPesanan.add(riwayatPesanan2!!)
                            Log.d("TAG", "onDataChangeValue: ${riwayatPesanan}")
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

        }

        riwayatBinding.recyclerviewRiwayat.adapter = RiwayatTransaksiAdapter(riwayatPesanan)

//        adapter.setOnClickCallback(object : ListBankSampahAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: ListBankSampah) {
//                Intent(this@RiwayatActivity, DetailBankSampahActivity::class.java).let {
//                    it.putExtra(DetailBankSampahActivity.EXTRA_DATA_BANK_SAMPAH, data)
//                    startActivity(it)
//                }
//                Log.d(HomeActivity.TAG, "onItemClicked: ${data}")
//            }
//        })
    }

    companion object{
        const val EXTRA_DATA_BANKSAMPAH = "EXTRA_DATA_BANKSAMPAH"
    }
}