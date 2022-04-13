package com.teck.healthycare.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.teck.healthycare.R
import com.teck.healthycare.databinding.InputDialogBinding
import com.teck.healthycare.domain.model.Health
import com.teck.healthycare.presentation.main.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HealthDialog(
    private val mainViewModel: MainViewModel
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val viewBinding: InputDialogBinding =
                InputDialogBinding.inflate(LayoutInflater.from(requireContext()))
            builder.setView(viewBinding.root)
                .setPositiveButton(
                    R.string.add
                ) { dialog, _ ->
                    val current = LocalDateTime.now()

                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    val time = current.format(formatter)
                    mainViewModel.saveData(
                        Health(
                            time = time,
                            pulse = viewBinding.editTextPulse.editableText.toString().toInt(),
                            heartBreak = viewBinding.editTextHeartBreak.editableText.toString()
                                .toInt(),
                            heartBeat = viewBinding.editTextHeartBeat.editableText.toString()
                                .toInt()
                        )
                    )
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel
                ) { dialog, _ ->
                    dialog?.cancel()
                }
            builder.create()
        }
    }
}