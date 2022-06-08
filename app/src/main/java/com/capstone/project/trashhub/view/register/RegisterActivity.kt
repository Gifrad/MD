package com.capstone.project.trashhub.view.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.project.trashhub.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            showLoading(true)
            val name: String = binding.nameEdtText.text.toString().trim()
            val email: String = binding.emailEdtText.text.toString().trim()
            val pass: String = binding.passwordEdtText.text.toString().trim()
            val confPass: String = binding.confPassEdtText.text.toString().trim()

            when {

                name.isEmpty() -> {
                    showLoading(false)
                    binding.nameEdtText.error = "Nama tidak boleh kosong"
                    binding.nameEdtText.requestFocus()
                    return@setOnClickListener
                }
                email.isEmpty() -> {
                    showLoading(false)
                    binding.emailEdtText.error = "Email tidak boleh kosong"
                    binding.emailEdtText.requestFocus()
                    return@setOnClickListener
                }
                pass.isEmpty() -> {
                    showLoading(false)
                    binding.passwordEdtText.error = "Password tidak boleh kosong"
                    binding.passwordEdtText.requestFocus()
                    return@setOnClickListener
                }
                confPass.isEmpty() || confPass != pass -> {
                    showLoading(false)
                    binding.confPassEdtText.error = "Silahkan masukan password yang sama"
                    binding.confPassEdtText.requestFocus()
                    return@setOnClickListener
                }
            }
        }
    }

        private fun showLoading(state: Boolean) {
            if (state) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
}
