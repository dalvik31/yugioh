package com.dalvik.yugiohdex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalvik.yugiohdex.databinding.ItemCardDetailBinding
import com.dalvik.yugiohdex.models.Card

class CardDetailAdapter(private var cardList: ArrayList<Card>) :
    RecyclerView.Adapter<CardDetailAdapter.CardViewHolder>() {

    class CardViewHolder(private val itemCardDetailBinding: ItemCardDetailBinding) :
        RecyclerView.ViewHolder(itemCardDetailBinding.root) {
        fun bindCard(card: Card){
            itemCardDetailBinding.card = card
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardDetailBinding = ItemCardDetailBinding.inflate(layoutInflater, parent,false)
        //val itemCardDetailBinding = ItemCardDetailBinding.inflate(layoutInflater)
        return CardViewHolder(itemCardDetailBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCard(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}