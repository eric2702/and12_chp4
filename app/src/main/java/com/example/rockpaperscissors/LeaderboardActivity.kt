package com.example.rockpaperscissors

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.databinding.ActivityLeaderboardBinding
import com.example.rockpaperscissors.model.Player
import com.example.rockpaperscissors.presenter.MainPresenter
import com.example.rockpaperscissors.presenter.MainPresenterImpl
import com.example.rockpaperscissors.sources.PlayerDatabase
import com.example.rockpaperscissors.view.MainView

class LeaderboardActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding
    private val leaderBoardAdapter = LeaderboardAdapter()
    private val mainPresenter: MainPresenter = MainPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupView()
    }

    private fun setupView() = with(binding) {
        this?.noLeaderboardData?.text = getString(R.string.no_leaderboard_data)
        this?.rvLeaderboard?.layoutManager = LinearLayoutManager(context())
        this?.rvLeaderboard?.adapter = leaderBoardAdapter
    }

    override fun context(): Context {
        return this
    }

    override fun onResultDatabase(players: List<Player>) {
        binding?.rvLeaderboard?.isVisible = players.isNotEmpty()
        binding?.noLeaderboardData?.isVisible = players.isEmpty()
        val plays: ArrayList<Player> = ArrayList()
        for (player in players) {
            if (plays.isEmpty()) {
                plays.add(player)
            } else {
                var counter = 0
                for (play in plays) {
                    if (play.name == player.name) {
                        play.score += player.score
                    } else {
                        counter++
                    }
                }
                if (counter == plays.size) {
                    plays.add(player)
                }
            }
        }
        plays.sortByDescending {
            it.score
        }
        Log.e("players", plays.toString())
        leaderBoardAdapter.addList(plays)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        PlayerDatabase.destroyDatabase()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.getLeaderBoard()
    }

}