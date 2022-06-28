package com.example.test

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users: List<User> = ArrayList()

    // создает RecyclerView.ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    // связывает views с содержимым
    override fun onBindViewHolder(viewHolder: UserHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // Передаем данные и оповещаем адаптер о необходимости обновления списка
    @SuppressLint("NotifyDataSetChanged")
    fun refreshUsers(users: List<User>){
        this.users = users
        notifyDataSetChanged()
    }

    // Внутренний класс ViewHolder описывает элементы представления списка и привязывает их к RecyclerView
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(user: User) = with(itemView) {
            userName.text = user.name
            userDescription.text = user.description
        }
    }
}