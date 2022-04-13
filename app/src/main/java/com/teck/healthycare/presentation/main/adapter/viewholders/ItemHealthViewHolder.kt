package com.teck.healthycare.presentation.main.adapter.viewholders

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.teck.healthycare.R
import com.teck.healthycare.databinding.ItemDataBinding
import com.teck.healthycare.domain.model.Health
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ItemHealthViewHolder(
    private val viewBinding: ItemDataBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(health: Health) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
        val currentDate = LocalDateTime.parse(health.time, formatter)
        val timeDateFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = currentDate.format(timeDateFormatter)
        viewBinding.pulse.text = health.pulse.toString()
        viewBinding.heartBreak.text = health.heartBreak.toString()
        viewBinding.heart.text = health.heartBeat.toString()
        viewBinding.time.text = time
        if (health.heartBeat!! >= 90) {
            viewBinding.containerItemHealthInfo.background =
                AppCompatResources.getDrawable(viewBinding.root.context, R.drawable.back_ground_red)
        } else {
            viewBinding.containerItemHealthInfo.background =
                AppCompatResources.getDrawable(
                    viewBinding.root.context,
                    R.drawable.back_ground_green
                )
        }
    }
}