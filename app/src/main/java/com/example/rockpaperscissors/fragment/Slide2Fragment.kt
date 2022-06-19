package com.example.rockpaperscissors.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.databinding.FragmentSlide2Binding
import com.example.rockpaperscissors.listener.FragmentTextListener

class Slide2Fragment : Fragment(), FragmentTextListener {

    private var _binding: FragmentSlide2Binding? = null
    private val binding get() = _binding
    private var enemy: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlide2Binding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imgVsPlayer?.setOnClickListener {
            enemy = "player"
            binding!!.imgVsPlayer.background = getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgVsComp.background = getDrawable(it.context, R.drawable.background_unselect)
        }

        binding?.imgVsComp?.setOnClickListener {
            enemy = "computer"
            binding!!.imgVsPlayer.background = getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgVsComp.background = getDrawable(it.context, R.drawable.background_selected)
        }

        binding?.btnEnter?.setOnClickListener {
            Toast.makeText(this.context, "Player choose $enemy as enemy", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getTextValue(): String {
        return enemy
    }
}