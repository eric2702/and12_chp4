package com.example.rockpaperscissors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rockpaperscissors.MainActivity
import com.example.rockpaperscissors.databinding.DialogWinloseBinding

class WinLoseDialogFragment : DialogFragment() {
    private var _binding: DialogWinloseBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogWinloseBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val nameData = bundle!!.getString(PLAYER_NAME, "")
        when (bundle.getString(RESULT, "")) {
            "win" -> binding?.hasil?.text = "$nameData\nMENANG!"
            "lose" -> binding?.hasil?.text = "$nameData\nMENANG!"
            else -> binding?.hasil?.text = "SERI!"
        }

        binding?.kembaliMenu?.setOnClickListener {
            dismiss()
            activity?.onBackPressed()
        }
        binding?.mainLagi?.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
        const val RESULT = "RESULT"
    }
}