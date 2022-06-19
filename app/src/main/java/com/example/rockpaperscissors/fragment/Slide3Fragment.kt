package com.example.rockpaperscissors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.databinding.FragmentSlide3Binding
import com.example.rockpaperscissors.listener.FragmentImageResIdListener
import com.example.rockpaperscissors.listener.FragmentTextListener

class Slide3Fragment : Fragment(), FragmentTextListener, FragmentImageResIdListener {

    private var _binding: FragmentSlide3Binding? = null
    private val binding get() = _binding
    private var avatar: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlide3Binding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imgOpponentAvatar1?.setOnClickListener {
            binding!!.imgOpponentAvatar1.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgOpponentAvatar2.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar3.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar4.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar5.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing
        }

        binding?.imgOpponentAvatar2?.setOnClickListener {
            binding!!.imgOpponentAvatar2.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgOpponentAvatar1.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar3.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar4.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar5.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing2
        }

        binding?.imgOpponentAvatar3?.setOnClickListener {
            binding!!.imgOpponentAvatar3.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgOpponentAvatar2.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar1.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar4.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar5.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing3
        }

        binding?.imgOpponentAvatar4?.setOnClickListener {
            binding!!.imgOpponentAvatar4.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgOpponentAvatar2.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar3.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar1.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar5.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing4
        }

        binding?.imgOpponentAvatar5?.setOnClickListener {
            binding!!.imgOpponentAvatar5.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgOpponentAvatar2.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar3.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar4.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgOpponentAvatar1.background =
                ContextCompat.getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing5
        }

        binding?.btnSubmit?.setOnClickListener {
            if (binding!!.edtNameEnemy.text.isEmpty()) {
                binding!!.edtNameEnemy.error = "Please fill the name first"
            } else {
                if (avatar == 0) {
                    Toast.makeText(
                        this.context,
                        "Please select avatar first!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this.context,
                        "${binding?.edtNameEnemy?.text} Selected avatar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getAvatarId(): Int {
        return avatar
    }

    override fun getTextValue(): String {
        return binding?.edtNameEnemy?.text.toString()
    }
}