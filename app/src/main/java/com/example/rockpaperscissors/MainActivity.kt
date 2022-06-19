package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
    private val binding get() = _binding

    private val insertPresenter: InsertPresenter = InsertPresenterImpl(this)

    private val playerName: String by lazy {
        intent.getStringExtra(PLAYER_NAME).toString()
    }
    private val playerAvatar: Int by lazy {
        intent.getIntExtra(PLAYER_AVATAR, R.drawable.pngwing)
    }
    private val enemyName: String by lazy {
        intent.getStringExtra(ENEMY_NAME).toString()
    }
    private val enemyAvatar: Int by lazy {
        intent.getIntExtra(ENEMY_AVATAR, R.drawable.pngwing)
    }
    private val enemyType: String by lazy {
        intent.getStringExtra(ENEMY_TYPE).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvPlayerName?.text = playerName
        binding?.tvOpponentName?.text = enemyName

        val compChoice = listOf(binding?.enemyRock, binding?.enemyPaper, binding?.enemyScissors)

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
                    binding?.tvResult?.visibility = View.VISIBLE
                    binding?.tvVs?.visibility = View.GONE
                    bundle.putString(PLAYER_NAME, playerName)
                    val newPlayer = Player(
                        name = playerName,
                        score = 5,
                        avatar = playerAvatar
                    )
//                    insertPresenter.saveToDatabase(newPlayer)
                }
                "lose" -> {
                    binding?.tvResult?.visibility = View.VISIBLE
                    binding?.tvVs?.visibility = View.GONE
                    bundle.putString(PLAYER_NAME, enemyName)
                    val newPlayer = Player(
                        name = enemyName,
                        score = 5,
                        avatar = enemyAvatar
                    )
//                    insertPresenter.saveToDatabase(newPlayer)
                }
                else -> {
                    binding?.tvVs?.text = getString(R.string.draw)
                    binding?.tvVs?.visibility = View.VISIBLE
                }
            }
        }

        fun playerClick(choice: CardView? = null) {
            choice?.let {
                val suitUser: Suit = when (choice) {
                    binding?.playerRock -> BatuAction()
                    binding?.playerPaper -> KertasAction()
                    else -> GuntingAction()
                }
                val suitCom = DataSources.getRandomSuit()
                var delay: Long = 0
                for (i in 0..2) {
                    compChoice.map {
                        handler.postDelayed({
                            if (it != null) {
                                activateColor(it)
                            }
                        }, delay)
                        delay += 500
                        handler.postDelayed({
                            if (it != null) {
                                disableColor(it)
                            }
                        }, delay)
                    }
                }
                handler.postDelayed({
                    when (suitCom.name) {
                        "batu" -> activateColor(binding!!.enemyRock)
                        "kertas" -> activateColor(binding!!.enemyPaper)
                        else -> activateColor(binding!!.enemyScissors)
                    }
                    val toastMessage = "$enemyName Memilih " + suitCom.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
                    val result = suitUser.action(suitCom.name)

                    showMessage(result)

                    bundle.putString(RESULT, result)
                    dialogWinLoseFragment.arguments = bundle
                    dialogWinLoseFragment.show(supportFragmentManager, null)
                }, delay)
            }
        }

        binding?.playerRock?.setOnClickListener {
            playerClick(binding?.playerRock)
            Toast.makeText(this, "$playerName Memilih Batu", Toast.LENGTH_SHORT).show()
        }

        binding?.playerPaper?.setOnClickListener {
            playerClick(binding?.playerPaper)
            Toast.makeText(this, "$playerName Memilih Kertas", Toast.LENGTH_SHORT).show()
        }

        binding?.playerScissors?.setOnClickListener {
            playerClick(binding?.playerScissors)
            Toast.makeText(this, "$playerName Memilih Gunting", Toast.LENGTH_SHORT).show()
        }

        binding?.btnRefresh?.setOnClickListener {
            resetView()
        }

        binding?.btnClose?.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun resetView() = with(binding){
        this!!.playerRock.background = getDrawable(R.drawable.background_unselect)
        this.playerPaper.background = getDrawable(R.drawable.background_unselect)
        this.playerScissors.background = getDrawable(R.drawable.background_unselect)
        this.enemyRock.background = getDrawable(R.drawable.background_unselect)
        this.enemyPaper.background = getDrawable(R.drawable.background_unselect)
        this.enemyScissors.background = getDrawable(R.drawable.background_unselect)
        this.tvVs.visibility = View.VISIBLE
        this.tvVs.text = getString(R.string.vs)
        this.tvResult.visibility = View.GONE
    }

    override fun context(): Context {
        return this
    }

    override fun onSaveDatabase() {}

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
        const val PLAYER_AVATAR = "PLAYER_AVATAR"
        const val ENEMY_NAME = "ENEMY_NAME"
        const val ENEMY_TYPE = "ENEMY_TYPE"
        const val ENEMY_AVATAR = "ENEMY_AVATAR"
        const val RESULT = "RESULT"
    }
}