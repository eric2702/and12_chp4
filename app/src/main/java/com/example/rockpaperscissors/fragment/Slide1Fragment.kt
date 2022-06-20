package com.example.rockpaperscissors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.databinding.FragmentSlide1Binding
import com.example.rockpaperscissors.listener.FragmentImageResIdListener
import com.example.rockpaperscissors.listener.FragmentTextListener

class Slide1Fragment : Fragment(), FragmentTextListener, FragmentImageResIdListener {

    private var _binding: FragmentSlide1Binding? = null
    private val binding get() = _binding
    var avatar: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlide1Binding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imgAvatar1?.setOnClickListener {
            binding!!.imgAvatar1.background =
                getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgAvatar2.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar3.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar4.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar5.background =
                getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing
        }

        binding?.imgAvatar2?.setOnClickListener {
            binding!!.imgAvatar2.background =
                getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgAvatar1.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar3.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar4.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar5.background =
                getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing2
        }

        binding?.imgAvatar3?.setOnClickListener {
            binding!!.imgAvatar3.background =
                getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgAvatar2.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar1.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar4.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar5.background =
                getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing3
        }

        binding?.imgAvatar4?.setOnClickListener {
            binding!!.imgAvatar4.background =
                getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgAvatar2.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar3.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar1.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar5.background =
                getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing4
        }

        binding?.imgAvatar5?.setOnClickListener {
            binding!!.imgAvatar5.background =
                getDrawable(it.context, R.drawable.background_selected)
            binding!!.imgAvatar2.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar3.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar4.background =
                getDrawable(it.context, R.drawable.background_unselect)
            binding!!.imgAvatar1.background =
                getDrawable(it.context, R.drawable.background_unselect)
            avatar = R.drawable.pngwing5
        }

        binding?.btnSubmit?.setOnClickListener {
            if (binding!!.edtNamePlayer.text.isEmpty()) {
                binding!!.edtNamePlayer.error = "Please fill the name first"
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
                        "${binding!!.edtNamePlayer.text} Selected avatar",
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

    override fun getTextValue(): String {
        return binding?.edtNamePlayer?.text.toString()
    }

    override fun getAvatarId(): Int {
        return avatar
    }
}