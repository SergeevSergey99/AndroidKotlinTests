package com.example.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    var userList : MutableLiveData<List<User>> = MutableLiveData()

    // инициализируем список и заполняем его данными пользователей
    init {
        userList.value = UserData.getUsers()
    }

    fun getListUsers() = userList

    // для обновления списка получаем второй список пользователей
    fun updateListUsers() {
        userList.value = UserData.getAnotherUser()
    }
}