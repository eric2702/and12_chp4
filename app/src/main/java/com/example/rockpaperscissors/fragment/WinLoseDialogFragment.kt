package com.example.rockpaperscissors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rockpaperscissors.databinding.DialogWinloseBinding


class WinLoseDialogFragment : DialogFragment() {
    private lateinit var binding: DialogWinloseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWinloseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val nameData = bundle!!.getString("NAME_DATA", "")
        val result = bundle!!.getString("RESULT", "")
        when (result) {
            "win" -> binding.hasil.text = nameData + "\nMENANG!"
            "lose" -> binding.hasil.text = nameData + "\nMENANG!"
            else -> binding.hasil.text = "SERI!"
        }

        binding.kembaliMenu.setOnClickListener({
            dismiss()
            activity?.onBackPressed()
        })
        binding.mainLagi.setOnClickListener({
            dismiss()
        })
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}