package com.example.acronymserviceinterviewtask.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymserviceinterviewtask.databinding.RvItemCardLongFormBinding
import com.willor.lib_data.domain.dataobjects.LongFormWithVariations

/**
 * RecyclerView.Adapter used to display Long Form Versions of Acronyms.
 * The xml file for it's items is rv_item_card_long_form.xml
 * This RecyclerView's Items also contain a nested horizontal RecyclerView
 */
class LongFormAdapter(
    val data: List<LongFormWithVariations>
) : RecyclerView.Adapter<LongFormAdapter.LongFormCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LongFormCard {
        return LongFormCard(
            RvItemCardLongFormBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LongFormCard, position: Int) {
        holder.initView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class LongFormCard(private val binding: RvItemCardLongFormBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initView(longFormWithVariations: LongFormWithVariations) {

            binding.longFormWithVariations = longFormWithVariations

            binding.rvLongFormVariations.adapter = LongFormVariationsAdapter(
                longFormWithVariations.variations
            )

        }
    }


}