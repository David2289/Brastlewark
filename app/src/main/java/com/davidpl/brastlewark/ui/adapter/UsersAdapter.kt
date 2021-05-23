package com.example.display.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davidpl.brastlewark.R
import com.davidpl.brastlewark.business.model.User
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class UsersAdapter(var userList: ArrayList<User>, val onItemClick: (user: User) -> Unit) : RecyclerView.Adapter<UsersAdapter.UserVH>() {

    lateinit var context: Context
    var filteredList = ArrayList<User>()
    var totalList = ArrayList<User>()

    fun updateTotalList() {
        this.totalList.clear()
        this.totalList.addAll(userList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.item_user, parent, false)
        return UserVH(rootView)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        val user = userList.get(position)
        Picasso.get().load(user.thumbnail).into(holder.photo)
        holder.name.text = user.name
        holder.age.text = String.format(context.resources.getString(R.string.screen_list_item_age), user.age.toString())
        holder.hair.text = String.format(context.resources.getString(R.string.screen_list_item_hair), user.hairColor)
        holder.view.setOnClickListener { onItemClick(user) }
    }

    class UserVH(val view: View): RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.photo)
        val name: TextView = view.findViewById(R.id.name)
        val age: TextView = view.findViewById(R.id.age)
        val hair: TextView = view.findViewById(R.id.hair)
    }

    // Filter Class
    fun filter(charText: String) {
        userList.clear()
        if (charText.length == 0) {
            userList.addAll(totalList)
        } else {
            for (item in totalList) {
                if (item.name.toLowerCase(Locale.getDefault()).contains(charText.toLowerCase(Locale.getDefault()))) {
                    filteredList.add(item)
                }
            }
            userList.addAll(filteredList)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}