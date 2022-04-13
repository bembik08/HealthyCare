package com.teck.healthycare.presentation.main.adapter.viewholders

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.teck.healthycare.R
import com.teck.healthycare.databinding.ItemDateBinding
import com.teck.healthycare.domain.model.Health
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ItemDateViewHolder(
    private val viewBinding: ItemDateBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(date: Health) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
        val currentDate = LocalDateTime.parse(date.time, formatter)
        val monthDateFormatter = DateTimeFormatter.ofPattern("dd MMMM")
        val monthDate = currentDate.format(monthDateFormatter)
        val timeDateFormatter = DateTimeFormatter.ofPattern("hh:mm", Locale.ENGLISH)
        val time = currentDate.format(timeDateFormatter)
        viewBinding.date.text = monthDate
        viewBinding.pulse.text = date.pulse.toString()
        viewBinding.heartBreak.text = date.heartBreak.toString()
        viewBinding.heart.text = date.heartBeat.toString()
        viewBinding.time.text = time
        if (date.heartBeat!! >= 90) {
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