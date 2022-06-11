package com.capstone.project.trashhub.view.detailbanksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.capstone.project.trashhub.R
import com.capstone.project.trashhub.network.model.ListBankSampah
import com.capstone.project.trashhub.view.detailbanksampah.fragment.DetailBankSampahFragment
import com.capstone.project.trashhub.view.home.HomeActivity
import com.capstone.project.trashhub.view.transaksi.TransaksiActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailBankSampahActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var buttonBerlangganan: Button
    private lateinit var buttonBackHome: ImageButton
    private lateinit var namaBankSampah : TextView
    private lateinit var namaJalanBankSampah : TextView
    private lateinit var imageBankSampah: ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var fireStore : FirebaseFirestore
    private lateinit var databaseReferences : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getParcelableExtra<ListBankSampah>(EXTRA_DATA_BANK_SAMPAH) as ListBankSampah
        Log.d("DetailBankSampah ", "onCreate: "+ data.toString())

        val mBundle = Bundle()
        mBundle.putParcelable(DetailBankSampahFragment.EXTRA_DATA_ALAMAT_FRAGMENT,data)
        Log.d(TAG, "onViewCreated: ${data.fullAddress}")
        setContentView(R.layout.activity_detail_bank_sampah)

//        Inisialisasi firebase
        auth = FirebaseAuth.getInstance()
        databaseReferences = FirebaseDatabase.getInstance().getReferenceFromUrl("https://trashhub-e7744-default-rtdb.firebaseio.com/")
        fireStore = FirebaseFirestore.getInstance()

        buttonBerlangganan = findViewById(R.id.button_berlangganan)
        imageBankSampah = findViewById(R.id.image_view_detail_bank_sampah)
        namaBankSampah = findViewById(R.id.tv_nama_bank_sampah_detail)
        namaJalanBankSampah = findViewById(R.id.tv_lokasi_bank_sampah_detail)
        buttonBackHome = findViewById(R.id.btn_back_home_detail)

        if (data.imageUrl.isNotEmpty()){
            Picasso.get().load(data.imageUrl).into(imageBankSampah)
        }

        if (data.name.isNotEmpty() == true){
            namaBankSampah.text = data.name
        }

        buttonBackHome.setOnClickListener {
            Intent(this,HomeActivity::class.java).let {
                startActivity(it)
            }
        }
        namaJalanBankSampah.text = data.street
//        fragment detail
        val sectionsPagerAdapter = SectionsPagerAdapter(this, mBundle)
        val viewPager : ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        tabLayout=findViewById(R.id.tabLayout)
        tabLayout.tabRippleColor = null
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        buttonBerlangganan.isActivated = false
        databaseReferences.addListenerForSingleValueEvent (object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (i in snapshot.child("transaksi").children){
                    Log.d(TAG, "onDataChange: ${i.key.equals(auth.currentUser?.uid.toString() + data.id)}")
                    if (!i.key.equals(auth.currentUser?.uid.toString() + data.id)){
                        buttonBerlangganan.isActivated = true
                        buttonBerlangganan.setOnClickListener {
                            val intent = Intent(this@DetailBankSampahActivity, TransaksiActivity::class.java)
                            intent.putExtra(TransaksiActivity.GET_ID_BANK_SAMPAH,data)
                            startActivity(intent)
                        }
                    }else{
                        buttonBerlangganan.isActivated = false
                        Toast.makeText(this@DetailBankSampahActivity,"Bank Sampah Sudah dipesan",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        } )

    }
    companion object{
        const val EXTRA_DATA_BANK_SAMPAH = "DATA_BANK_SAMPAH"
        const val TAG = "DetailBankSampahActivity"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_view
        )
    }
}