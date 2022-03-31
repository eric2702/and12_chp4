package com.example.rockpaperscissors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rockpaperscissors.action.BatuAction
import com.example.rockpaperscissors.action.GuntingAction
import com.example.rockpaperscissors.action.KertasAction
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var root = findViewById(R.id.root) as ConstraintLayout
        var rock = findViewById(R.id.rock) as CardView
        var paper = findViewById(R.id.paper) as CardView
        var scissors = findViewById(R.id.scissors) as CardView
        var refresh = findViewById(R.id.refresh) as ImageView
        var rockCom = findViewById(R.id.rockCom) as CardView
        var paperCom = findViewById(R.id.paperCom) as CardView
        var scissorsCom = findViewById(R.id.scissorsCom) as CardView
        var versus = findViewById(R.id.versus) as TextView
        var win = findViewById(R.id.win) as TextView
        var lose = findViewById(R.id.lose) as TextView
        var draw = findViewById(R.id.draw) as TextView
        var allChoices = listOf<CardView>(rock, paper, scissors, rockCom, paperCom, scissorsCom)


        fun reset() {
            allChoices.map { it.setCardBackgroundColor(Color.WHITE) }
            versus.setVisibility(View.VISIBLE)
            win.setVisibility(View.INVISIBLE)
            lose.setVisibility(View.INVISIBLE)
            draw.setVisibility(View.INVISIBLE)
        }

        fun activateColor(choice: CardView) {
            choice.setCardBackgroundColor(Color.parseColor("#C3DAE9"))
        }

        fun showMessage(condition: String) {
            when (condition) {
                "win" -> {
                    win.setVisibility(View.VISIBLE)
                }
                "lose" -> {
                    lose.setVisibility(View.VISIBLE)
                }
                else -> {
                    draw.setVisibility(View.VISIBLE)
                }
            }
        }

        fun playerClick(choice: CardView? = null) {
            reset()
            choice?.let{
                activateColor(choice)
                val suitUser: Suit = when (choice) {
                    rock -> BatuAction()
                    paper -> KertasAction()
                    else -> GuntingAction()
                }
                val suitCom = DataSources.getRandomSuit()
                when (suitCom.name) {
                    "batu" -> activateColor(rockCom)
                    "kertas" -> activateColor(paperCom)
                    else -> activateColor(scissorsCom)
                }
                var result = suitUser.action(suitCom.name)
                versus.setVisibility(View.INVISIBLE)
                showMessage(result)
//                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            }
        }

        rock.setOnClickListener{
            playerClick(rock)
        }
        paper.setOnClickListener{
            playerClick(paper)
        }
        scissors.setOnClickListener{
           playerClick(scissors)
        }

        refresh.setOnClickListener{
            playerClick()
        }


    }




}