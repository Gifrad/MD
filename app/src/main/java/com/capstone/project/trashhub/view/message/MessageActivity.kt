package com.capstone.project.trashhub.view.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.project.trashhub.databinding.ActivityTransaksiBinding
import com.capstone.project.trashhub.network.model.Message
import com.capstone.project.trashhub.view.adapter.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

@Suppress("CAST_NEVER_SUCCEEDS")
class MessageActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var activityTransaksiBinding: ActivityTransaksiBinding
    private lateinit var transaksiViewModel: MessageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTransaksiBinding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(activityTransaksiBinding.root)
//        var messageList = Message("","","","",0) as ArrayList<Message> /* = java.util.ArrayList<com.capstone.project.trashhub.network.model.Message> */
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://trashhub-e7744-default-rtdb.firebaseio.com/")
        auth = Firebase.auth
        transaksiViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        transaksiViewModel.listBankSampah.observe(this,{
            Log.d("TransaksiActivity", "onDataChange2: aku disini")
            getAdapter(it)
        })
    }

    private fun getAdapter(message: ArrayList<Message>){
        val adapter = MessageAdapter()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentUser = auth.currentUser

                for (i in snapshot.child("users").children){
                    val getName = i.child("users").child(currentUser?.displayName.toString())
                    if (i != getName){
//                        continue
                        val getName2 = i.child("name").value
                        val getProfilePict = i.child("profile_pict").value
                        Log.d("TransaksiActivity", "onDataChange2: ${getName2}")
//
//                        Log.d("TransaksiActivity", "onDataChange2: $getName2")
                        val messageList2 = Message(getName2.toString(),getProfilePict.toString(),currentUser?.email.toString(),"",0)
                        message.add(messageList2)
                    }
                }
                adapter.setList(message)
                Log.d("TAG", "onDataChange: $message")
//                activityTransaksiBinding.recyclerviewMessage.layoutManager = LinearLayoutManager(this@MessageActivity)
//                activityTransaksiBinding.recyclerviewMessage.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}