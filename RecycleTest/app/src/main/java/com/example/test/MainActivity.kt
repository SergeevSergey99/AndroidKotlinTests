package com.example.test

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userViewModel by  lazy { ViewModelProvider(this).get(UserViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /// инициализируем адаптер и присваиваем его списку
        val adapter = UserAdapter()
        userList.layoutManager = LinearLayoutManager(this)
        userList.adapter = adapter

        // подписываем адаптер на изменения списка
        userViewModel.getListUsers().observe(this, Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })
    }


    // создаем меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // при нажатии refresh обновляем список
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.refresh -> {
                userViewModel.updateListUsers()
            }
        }

        return super.onOptionsItemSelected(item)
    }

//
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.run {
//            putString(textView.id.toString(), textView.text.toString())
//        }
//
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        savedInstanceState.run {
//            textView.text = getString(textView.id.toString())
//        }
//    }


}

