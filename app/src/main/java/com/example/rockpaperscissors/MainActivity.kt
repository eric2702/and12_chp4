package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import com.example.rockpaperscissors.presenter.*
import com.example.rockpaperscissors.sources.PlayerDao
import com.example.rockpaperscissors.sources.PlayerDatabase
import com.example.rockpaperscissors.view.CheckNameView
import com.example.rockpaperscissors.view.InsertView
import com.example.rockpaperscissors.view.UpdateView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), InsertView, CheckNameView, UpdateView {

    private val handler = Handler()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val checkNamePresenter: CheckNamePresenter = CheckNamePresenterImpl(this)
    private val updatePresenter: UpdatePresenter = UpdatePresenterImpl(this)
    private val playerDatabase: PlayerDatabase? by lazy {
        PlayerDatabase.getInstance(this)
    }
    private val playerDao: PlayerDao? by lazy {
        playerDatabase?.playerDao()
    }

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

    private val dialogWinLoseFragment = WinLoseDialogFragment()
    val bundle = Bundle()

    private var picked = false
    private var player1pick: Suit? = null
    private var player2pick: String? = null
    private var gameDone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvPlayerName?.text = playerName
        binding?.tvOpponentName?.text = enemyName
        "Game Type : VS $enemyType".also { binding?.tvGameType?.text = it }

        // player press rock section
        binding?.playerRock?.setOnClickListener {
            if (enemyType == "player") {
                if (!gameDone) {
                    if (player1pick == null) {
                        Toast.makeText(this, "$playerName Memilih Batu", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
                }
                playerClick(binding!!.playerRock)
            } else {
                playerVsCompClick(binding?.playerRock)
            }
        }
        // end section

        // player press paper section
        binding?.playerPaper?.setOnClickListener {
            if (enemyType == "player") {
                if (!gameDone) {
                    if (player1pick == null) {
                        Toast.makeText(this, "$playerName Memilih Kertas", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
                }
                playerClick(binding!!.playerPaper)
            } else {
                playerVsCompClick(binding?.playerPaper)
            }
        }
        // end section

        // player press scissors section
        binding?.playerScissors?.setOnClickListener {
            if (enemyType == "player") {
                if (!gameDone) {
                    if (player1pick == null) {
                        Toast.makeText(this, "$playerName Memilih Gunting", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
                }
                playerClick(binding!!.playerScissors)
            } else {
                playerVsCompClick(binding?.playerScissors)
            }
        }
        // end section

        // opponent press rock section
        binding?.enemyRock?.setOnClickListener {
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "$enemyName Memilih Batu", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            opponentClick(binding!!.enemyRock)
        }
        // end section

        // opponent press paper section
        binding?.enemyPaper?.setOnClickListener {
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "$enemyName Memilih Kertas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            opponentClick(binding!!.enemyPaper)
        }
        // end section

        // opponent press scissors section
        binding?.enemyScissors?.setOnClickListener {
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "$enemyName Memilih Gunting", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            opponentClick(binding!!.enemyScissors)
        }

        binding?.btnRefresh?.setOnClickListener {
            resetView()
        }

        binding?.btnClose?.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuActivity::class.java)

            intent.putExtra(MenuActivity.PLAYER_NAME, playerName)
            intent.putExtra(MenuActivity.PLAYER_AVATAR, playerAvatar)
            intent.putExtra(MenuActivity.ENEMY_NAME, enemyName)
            intent.putExtra(MenuActivity.ENEMY_AVATAR, enemyAvatar)
            intent.putExtra(MenuActivity.ENEMY_TYPE, enemyType)

            startActivity(intent)
        }
    }

    private fun showMessage(condition: String) {
        when (condition) {
            "win" -> {
                binding?.tvResult?.visibility = View.VISIBLE
                binding?.tvResult?.text = "$playerName Menang"
                binding?.tvVs?.visibility = View.GONE
                bundle.putString(PLAYER_NAME, playerName)
                if (enemyType == "player") {
                    GlobalScope.launch {
                        if (playerDao?.getPlayerByName(playerName)!!.isNotEmpty()) {
                            val player = playerDao?.getPlayerByName(playerName)?.first()
                            runOnUiThread {
                                if (player != null) {
                                    if (player.id!! > 0) {
                                        player.score += 5
                                        updatePresenter.updateDatabase(player)
                                    } else {
                                        val newPlayer = Player(
                                            name = playerName,
                                            score = 5,
                                            avatar = playerAvatar
                                        )
                                        insertPresenter.saveToDatabase(newPlayer)
                                    }
                                } else {
                                    val newPlayer = Player(
                                        name = playerName,
                                        score = 5,
                                        avatar = playerAvatar
                                    )
                                    insertPresenter.saveToDatabase(newPlayer)
                                }
                            }
                        } else {
                            val newPlayer = Player(
                                name = playerName,
                                score = 5,
                                avatar = playerAvatar
                            )
                            insertPresenter.saveToDatabase(newPlayer)
                        }
                    }
                }
            }
            "lose" -> {
                binding?.tvResult?.visibility = View.VISIBLE
                binding?.tvResult?.text = "$enemyName Menang"
                binding?.tvVs?.visibility = View.GONE
                bundle.putString(PLAYER_NAME, enemyName)
                if (enemyType == "player") {
                    GlobalScope.launch {
                        if (playerDao?.getPlayerByName(enemyName)!!.isNotEmpty()) {
                            val player = playerDao?.getPlayerByName(enemyName)?.first()
                            runOnUiThread {
                                if (player != null) {
                                    if (player.id!! > 0) {
                                        player.score += 5
                                        updatePresenter.updateDatabase(player)
                                    } else {
                                        val newPlayer = Player(
                                            name = enemyName,
                                            score = 5,
                                            avatar = enemyAvatar
                                        )
                                        insertPresenter.saveToDatabase(newPlayer)
                                    }
                                } else {
                                    val newPlayer = Player(
                                        name = enemyName,
                                        score = 5,
                                        avatar = enemyAvatar
                                    )
                                    insertPresenter.saveToDatabase(newPlayer)
                                }
                            }
                        } else {
                            val newPlayer = Player(
                                name = enemyName,
                                score = 5,
                                avatar = enemyAvatar
                            )
                            insertPresenter.saveToDatabase(newPlayer)
                        }
                    }
                }
            }
            else -> {
                binding?.tvResult?.visibility = View.GONE
                binding?.tvVs?.text = getString(R.string.draw)
                binding?.tvVs?.textSize = 32.0.toFloat()
                binding?.tvVs?.visibility = View.VISIBLE
            }
        }
    }

    // section player click on Player VS Player
    private fun playerClick(choice: CardView) {
        if (gameDone || player1pick != null) {
            return
        }
        choice.let {
            activateColor(choice)
            val suitPlayer1: Suit = when (choice) {
                binding?.playerRock -> BatuAction()
                binding?.playerPaper -> KertasAction()
                else -> GuntingAction()
            }
            if (picked) {
                player2pick?.let { it1 -> result(suitPlayer1, it1) }
            } else {
                player1pick = suitPlayer1
                picked = true
            }
        }
    }

    private fun opponentClick(choice: CardView? = null) {
        if (gameDone || player2pick != null) {
            return
        }
        choice?.let {
            activateColor(choice)
            val suitUser: String = when (choice) {
                binding?.enemyRock -> "batu"
                binding?.enemyPaper -> "kertas"
                else -> "gunting"
            }
            if (picked) {
                player1pick?.let { it1 -> result(it1, suitUser) }
            } else {
                player2pick = suitUser
                picked = true
            }
        }
    }

    private fun result(suitPlayer1: Suit, suitPlayer2: String) {
        val result = suitPlayer1.action(DataSources.convertStringToData(suitPlayer2).name)
        binding?.tvVs?.visibility = View.INVISIBLE
        showMessage(result)
        bundle.putString("RESULT", result)
        dialogWinLoseFragment.arguments = bundle
        dialogWinLoseFragment.show(supportFragmentManager, null)
        gameDone = true
    }

    private fun playerVsCompClick(choice: CardView? = null) {
        val compChoice = listOf(binding?.enemyRock, binding?.enemyPaper, binding?.enemyScissors)
        choice?.let {
            activateColor(choice)
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

    private fun activateColor(choice: CardView) {
        choice.setCardBackgroundColor(Color.parseColor("#C3DAE9"))
    }

    private fun disableColor(choice: CardView) {
        choice.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun resetView() = with(binding) {
        disableColor(this!!.playerRock)
        disableColor(playerPaper)
        disableColor(playerScissors)
        disableColor(enemyRock)
        disableColor(enemyPaper)
        disableColor(enemyScissors)
        tvVs.visibility = View.VISIBLE
        tvVs.text = getString(R.string.vs)
        tvVs.textSize = 65.toFloat()
        tvResult.visibility = View.GONE
        gameDone = false
        player1pick = null
        player2pick = null
        picked = false
    }

    override fun context(): Context {
        return this
    }

    override fun onUpdateDatabase() {}

    override fun onCheckDatabase(player: List<Player>) {}

    override fun onResume() {
        super.onResume()
        checkNamePresenter.getPlayerByName(playerName)
        checkNamePresenter.getPlayerByName(enemyName)
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