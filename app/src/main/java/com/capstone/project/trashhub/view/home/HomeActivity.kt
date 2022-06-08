package com.capstone.project.trashhub.view.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.project.trashhub.databinding.ActivityHomeBinding
import com.capstone.project.trashhub.network.model.ListBankSampah
import com.capstone.project.trashhub.view.adapter.ListBankSampahAdapter
import com.capstone.project.trashhub.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        showLoading(false)
        setupAction()
        userValidation()

    }

    private fun setupAction() {
        binding.imgLogout.setOnClickListener {
            showLoading(true)
            signOut()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun userValidation() {
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        } else {
            binding.tvUsername.text = "Hai ${firebaseUser.displayName}"
        }
    }

    private fun getAdapter(listBankSampah: ArrayList<ListBankSampah>) {
        val adapter = ListBankSampahAdapter()
        adapter.setList(listBankSampah)
        binding.apply {
            rvRecomendasi.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvRecomendasi.adapter = adapter
        }
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}