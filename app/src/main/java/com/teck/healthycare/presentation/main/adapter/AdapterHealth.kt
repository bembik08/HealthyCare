package com.teck.healthycare.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teck.healthycare.databinding.ItemDataBinding
import com.teck.healthycare.databinding.ItemDateBinding
import com.teck.healthycare.domain.model.Health
import com.teck.healthycare.presentation.main.adapter.viewholders.ItemDateViewHolder
import com.teck.healthycare.presentation.main.adapter.viewholders.ItemHealthViewHolder
import com.teck.healthycare.presentation.main.adapter.viewholders.ViewHolderTypes
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AdapterHealth(
    private val data: List<Health>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderTypes.ITEM_HEALTH.ordinal -> ItemHealthViewHolder(
                ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ViewHolderTypes.ITEM_DATE.ordinal -> ItemDateViewHolder(
                ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ItemHealthViewHolder(
                ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemDateViewHolder -> holder.bind(data[position])
            is ItemHealthViewHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ViewHolderTypes.ITEM_DATE.ordinal
        }
        val currentDate = LocalDate.parse(data[position].time, formatter)
        val postDate = LocalDate.parse(data[position - 1].time, formatter)
        return when (currentDate == postDate) {
            true -> {
                ViewHolderTypes.ITEM_HEALTH.ordinal
            }
            false -> {
                ViewHolderTypes.ITEM_DATE.ordinal
            }
        }
    }


}