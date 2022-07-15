package com.example.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tetris.storage.AppPreferences

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrScore: TextView? = null
    var appPrefs: AppPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPrefs = AppPreferences(this)

        val bntRestart = findViewById<Button>(R.id.btn_restart)
        tvHighScore = findViewById(R.id.tv_high_score)
        tvCurrScore = findViewById(R.id.tv_current_score)

        UpdateHighScore()
        UpdateCurrScore()
    }

    private fun UpdateHighScore(){
        tvHighScore?.text = "${appPrefs?.getHighScore()}"
    }
    private fun UpdateCurrScore(){
        tvCurrScore?.text = "0"
    }
}