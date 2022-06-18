package com.example.rockpaperscissors

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rockpaperscissors.action.BatuAction
import com.example.rockpaperscissors.action.GuntingAction
import com.example.rockpaperscissors.action.KertasAction
import com.example.rockpaperscissors.databinding.ActivityMainBinding
import com.example.rockpaperscissors.fragment.WinLoseDialogFragment
import com.example.rockpaperscissors.model.Player
import com.example.rockpaperscissors.presenter.InsertPresenter
import com.example.rockpaperscissors.presenter.InsertPresenterImpl
import com.example.rockpaperscissors.view.InsertView
import java.util.*

class MainActivity : AppCompatActivity(), InsertView {
    private val handler = Handler()
    private var _binding: ActivityMainBinding? = null
    private lateinit var binding: ActivityMainBinding
    private val insertPresenter: InsertPresenter = InsertPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val rock = binding.rock
        val paper = binding.paper
        val scissors = binding.scissors
        val refresh = binding.refresh
        val rockCom = binding.rockCom
        val paperCom = binding.paperCom
        val scissorsCom = binding.scissorsCom
        val versus = binding.versus
        val win = binding.win
        val lose = binding.lose
        val draw = binding.draw
        val close = binding.close
        val pemain1 = binding.pemain1

        val allChoices = listOf(rock, paper, scissors, rockCom, paperCom, scissorsCom)
        val comChoices = listOf(rockCom, paperCom, scissorsCom)
        val nameData = intent.getStringExtra("NAME_DATA").toString()
        pemain1.text = nameData
        win.text = "$nameData\nMenang"

        fun reset() {
            allChoices.map { it.setCardBackgroundColor(Color.WHITE) }
            versus.visibility = View.VISIBLE
            win.visibility = View.INVISIBLE
            lose.visibility = View.INVISIBLE
            draw.visibility = View.INVISIBLE
        }

        fun activateColor(choice: CardView) {
            choice.setCardBackgroundColor(Color.parseColor("#C3DAE9"))
        }

        fun disableColor(choice: CardView) {
            choice.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        val dialogWinLoseFragment = WinLoseDialogFragment()
        val bundle = Bundle()

        fun showMessage(condition: String) {
            when (condition) {
                "win" -> {
                    win.visibility = View.VISIBLE
                    bundle.putString("NAME_DATA", nameData)
                    val newPlayer = Player(
                        name = nameData,
                        score = 5
                    )
                    insertPresenter.saveToDatabase(newPlayer)
                }
                "lose" -> {
                    lose.visibility = View.VISIBLE
                    bundle.putString("NAME_DATA", "CPU")
                    val newPlayer = Player(
                        name = "CPU",
                        score = 5
                    )
                    insertPresenter.saveToDatabase(newPlayer)
                }
                else -> {
                    draw.visibility = View.VISIBLE
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
                handler.removeCallbacksAndMessages(null) //remove postdelayed animation
                var delay : Long = 0
                for (i in 0..2) {
                    comChoices.map {
                        handler.postDelayed({
                            activateColor(it)
                        }, delay)
                        delay += 500
                        handler.postDelayed({
                            disableColor(it)
                        }, delay)
                    }
                }
                handler.postDelayed({
                    when (suitCom.name) {
                        "batu" -> activateColor(rockCom)
                        "kertas" -> activateColor(paperCom)
                        else -> activateColor(scissorsCom)
                    }
                    val toastMessage = "CPU Memilih " + suitCom.name.capitalize(Locale.ROOT)
                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
                    val result = suitUser.action(suitCom.name)
                    versus.visibility = View.INVISIBLE
                    showMessage(result)

                    bundle.putString("RESULT", result)
                    dialogWinLoseFragment.arguments = bundle
                    dialogWinLoseFragment.show(supportFragmentManager, null)
                }, delay)
            }
        }

        rock.setOnClickListener{
            playerClick(rock)
            Toast.makeText(this, "$nameData Memilih Batu", Toast.LENGTH_SHORT).show()
        }
        paper.setOnClickListener{
            playerClick(paper)
            Toast.makeText(this, "$nameData Memilih Kertas", Toast.LENGTH_SHORT).show()
        }
        scissors.setOnClickListener{
           playerClick(scissors)
           Toast.makeText(this, "$nameData Memilih Gunting", Toast.LENGTH_SHORT).show()
        }
        refresh.setOnClickListener{
            playerClick()
            handler.removeCallbacksAndMessages(null) //remove postdelayed animation

        }
        close.setOnClickListener{
            onBackPressed()
            handler.removeCallbacksAndMessages(null) //remove postdelayed animation
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacksAndMessages(null) //remove postdelayed animation
    }

    override fun context(): Context {
        return this
    }

    override fun onSaveDatabase() {
        binding.pemain1.text = ""
        binding.win.text = ""
    }
}