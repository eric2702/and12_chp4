package com.example.rockpaperscissors

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.rockpaperscissors.fragment.Slide1Fragment
import com.example.rockpaperscissors.fragment.Slide2Fragment
import com.example.rockpaperscissors.fragment.Slide3Fragment
import com.example.rockpaperscissors.listener.FragmentImageResIdListener
import com.example.rockpaperscissors.listener.FragmentTextListener
import com.github.appintro.AppIntro

class RPSIntro : AppIntro() {

    private val playerSection: Slide1Fragment by lazy {
        Slide1Fragment()
    }

    private val chooseEnemy: Slide2Fragment by lazy {
        Slide2Fragment()
    }

    private val enemySection: Slide3Fragment by lazy {
        Slide3Fragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(playerSection)
        addSlide(chooseEnemy)
        addSlide(enemySection)

        setIndicatorColor(Color.parseColor("#0070C0"), Color.parseColor("#C3DAE9"))
        setColorSkipButton(Color.parseColor("#0070C0"))
        setColorDoneText(Color.parseColor("#0070C0"))

        val separator: View = findViewById(com.github.appintro.R.id.bottom_separator)
        separator.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val viewPager: ViewPager = findViewById(com.github.appintro.R.id.view_pager)
        viewPager.currentItem = 2
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        launchToMenu()
    }

    private fun launchToMenu() {
        // player section
        val playerNameSection = playerSection as FragmentTextListener
        val playerName = playerNameSection.getTextValue()

        val playerAvatarSection = playerSection as FragmentImageResIdListener
        val playerAvatar = playerAvatarSection.getAvatarId()
        // end player section

        // enemy section
        val chooseEnemySection = chooseEnemy as FragmentTextListener
        val enemyType = chooseEnemySection.getTextValue()

        val enemyNameSection = enemySection as FragmentTextListener
        val enemyName = enemyNameSection.getTextValue()

        val enemyAvatarSection = enemySection as FragmentImageResIdListener
        val enemyAvatar = enemyAvatarSection.getAvatarId()
        // end enemy section

        // Section to menu Activity
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(MenuActivity.PLAYER_NAME, playerName)
        intent.putExtra(MenuActivity.PLAYER_AVATAR, playerAvatar)
        intent.putExtra(MenuActivity.ENEMY_NAME, enemyName)
        intent.putExtra(MenuActivity.ENEMY_AVATAR, enemyAvatar)
        intent.putExtra(MenuActivity.ENEMY_TYPE, enemyType)

        startActivity(intent)
        finish()
    }

}