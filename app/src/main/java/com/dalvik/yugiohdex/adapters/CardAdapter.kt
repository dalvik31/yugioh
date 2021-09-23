package com.dalvik.yugiohdex.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dalvik.yugiohdex.R
import com.dalvik.yugiohdex.databinding.ItemCardBinding
import com.dalvik.yugiohdex.models.Card
import com.dalvik.yugiohdex.view.CardDetailFragmentArgs
import com.dalvik.yugiohdex.view.GridFragment
import com.dalvik.yugiohdex.view.GridFragmentDirections
import kotlin.collections.ArrayList


class CardAdapter(
    private var cardList: ArrayList<Card>
) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cardsFilterList = ArrayList<Card>()

    init {
        cardsFilterList = cardList
    }

    class CardViewHolder(private val itemCardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemCardBinding.root) {
        fun bindCard(card: Card) {
            itemCardBinding.card = card
            itemCardBinding.executePendingBindings()
            itemCardBinding.root.setOnClickListener {
                Log.e("type","type${card.type}")
                val action = GridFragmentDirections.actionGridFragmentToCardDetailFragment(adapterPosition)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardBinding = ItemCardBinding.inflate(layoutInflater)
        return CardViewHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCard(cardsFilterList[position])
    }

    override fun getItemCount(): Int {
        return cardsFilterList.size
    }
}

