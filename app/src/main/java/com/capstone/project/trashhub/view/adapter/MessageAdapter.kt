package com.capstone.project.trashhub.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.trashhub.R
import com.capstone.project.trashhub.network.model.ListBankSampah
import com.capstone.project.trashhub.network.model.Message
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MessageAdapter(): RecyclerView.Adapter<MessageAdapter.ViewHolderMessage>() {
    private val list = ArrayList<Message>()
    class ViewHolderMessage(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pictureUser : CircleImageView = itemView.findViewById(R.id.image_profile_message)
        var name : TextView = itemView.findViewById(R.id.tv_name_user_profile)
        var lastMessage : TextView = itemView.findViewById(R.id.tv_last_seen_message)
        var unseenMessage : TextView = itemView.findViewById(R.id.unseen_message)
    }

    fun setList(users: ArrayList<Message>) {
        val diffCallback = MessageDiffCallback(list, users)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(users)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderMessage {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_message,parent,false)
        return ViewHolderMessage(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMessage, position: Int) {
        val list2 = list.get(position)

        if (!list2.profilePic.isEmpty()){
            Picasso.get().load(list2.profilePic).into(holder.pictureUser)
        }
        holder.name.text = list2.name
        holder.lastMessage.text = list2.lastMessage

        if (list2.unseenMessage == 0){
            holder.unseenMessage.visibility = View.GONE
        }else{
            holder.unseenMessage.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = list.size
}


class MessageDiffCallback(
    private val mOldUserList: ArrayList<Message>,
    private val mNewUserList: ArrayList<Message>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].name == mNewUserList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mNewUserList[newItemPosition]
        return oldEmployee.name == newEmployee.name
    }
}
