package com.example.tetris

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.tetris.models.AppModel
import com.example.tetris.storage.AppPreferences
import com.example.tetris.views.TetrisView

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrScore: TextView? = null
    var appPrefs: AppPreferences? = null
    private val appModel = AppModel()
    private lateinit var tetrisView: TetrisView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPrefs = AppPreferences(this)
        appModel.setPreferences(appPrefs)

        val bntRestart = findViewById<Button>(R.id.btn_restart)
        tvHighScore = findViewById(R.id.tv_high_score)
        tvCurrScore = findViewById(R.id.tv_current_score)

        tetrisView = findViewById(R.id.view_tetris)
        tetrisView.setActivity(this)
        tetrisView.setModel(appModel)
        tetrisView.setOnTouchListener(this::onTetrisViewTouch)

        bntRestart.setOnClickListener(this::btnRestartClick)

        UpdateHighScore()
        UpdateCurrScore()
    }

    private fun btnRestartClick(view: View) {
        appModel.restartGame()
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent): Boolean {
        if (appModel.isGameOver() || appModel.isGameAwaitingStart()) {
            appModel.startGame()
            tetrisView.setGameCommandWithDelay(AppModel.Motions.DOWN)
        } else if (appModel.isGameActive()) {
            when (resolveTouchDirection(view, event)) {
                0 -> moveTetromino(AppModel.Motions.LEFT)
                1 -> moveTetromino(AppModel.Motions.ROTATE)
                2 -> moveTetromino(AppModel.Motions.DOWN)
                3 -> moveTetromino(AppModel.Motions.RIGHT)
            }
        }
        return true
    }

    private fun moveTetromino(motion: AppModel.Motions) {
        if(appModel.isGameActive())
            tetrisView.setGameCommand(motion)
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int {
        val x = event.x / view.width
        val y = event.y / view.height
        val dir: Int = if (y > x) {
            if (x > 1 - y) 2 else 0
        } else {
            if (x > 1 - y) 3 else 1
        }
        return dir
    }

    private fun UpdateHighScore() {
        tvHighScore?.text = "${appPrefs?.getHighScore()}"
    }

    private fun UpdateCurrScore() {
        tvCurrScore?.text = "0"
    }
}