package com.example.acronymserviceinterviewtask.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymserviceinterviewtask.databinding.RvItemAcromineRespItemBinding
import com.willor.lib_data.domain.dataobjects.AcromineRespItem


/**
 * RecyclerView.Adapter used to display Acronym's from the AcromineRespItem.
 * RecyclerView Xml Item is rv_item_acromine_resp_item.xml.
 * Acronym Card is clickable and will trigger a new Recycler View to be displayed showing the
 * Long forms and Variations of the Acronym.
 */
class SearchResultsRvAdapter(
    val data: List<AcromineRespItem>,
    val onItemClicked: (text: String) -> Unit
) : RecyclerView.Adapter<SearchResultsRvAdapter.RvItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvItem {
        val binding = RvItemAcromineRespItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RvItem(binding)
    }

    override fun onBindViewHolder(holder: RvItem, position: Int) {
        holder.initView(data[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class RvItem(private val binding: RvItemAcromineRespItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun initView(acronym: AcromineRespItem, onItemClicked: (String) -> Unit) {

            binding.acromineItem = acronym

            binding.tvSearchresulttitle.setOnClickListener {
                onItemClicked(acronym.shortForm)
            }
        }
    }


}