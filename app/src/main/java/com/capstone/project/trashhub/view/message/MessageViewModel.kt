package com.capstone.project.trashhub.view.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.project.trashhub.network.model.Message

class MessageViewModel: ViewModel() {
    val listBankSampah = MutableLiveData<ArrayList<Message>>()
}