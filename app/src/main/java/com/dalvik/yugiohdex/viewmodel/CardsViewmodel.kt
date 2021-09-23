package com.dalvik.yugiohdex.viewmodel


import android.util.Log
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalvik.yugiohdex.R
import com.dalvik.yugiohdex.models.Card
import com.dalvik.yugiohdex.network.CardsResult
import com.dalvik.yugiohdex.repository.CardRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class CardsViewmodel : ViewModel() {

    var cardList = MutableLiveData<ArrayList<Card>>(arrayListOf())
    var cardListFilter = MutableLiveData<ArrayList<Card>>(arrayListOf())


    fun getAllCards() {
        viewModelScope.launch {
            when (val resultCards = CardRepository.getInstance().getAllCardsRetrofit()) {
                is CardsResult.Success -> {
                    cardList.postValue(resultCards.data)
                    cardListFilter.postValue(resultCards.data)
                }
                is CardsResult.Failure -> {
                    cardList.postValue(arrayListOf())
                    cardListFilter.postValue(arrayListOf())
                }
            }
        }
    }


    fun filterCards(filterId: Int) {
        cardList.value?.let {
            when (filterId) {
                R.id.menuTrapCard -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Trap Card"
                    } as ArrayList<Card>)

                }
                R.id.menuSpellCard -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Spell Card"
                    } as ArrayList<Card>)

                }
                R.id.menuRitualMonster -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Ritual Monster"
                    } as ArrayList<Card>)

                }
                R.id.menuNormalMonster -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Normal Monster"
                    } as ArrayList<Card>)

                }
                R.id.menuFusionMonster -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Fusion Monster"
                    } as ArrayList<Card>)

                }
                R.id.menuFlipEffectMonster -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Flip Effect Monster"
                    } as ArrayList<Card>)
                }
                R.id.menuEffectMonster -> {

                    cardListFilter.postValue(it.filter { card ->
                        card.type == "Effect Monster"
                    } as ArrayList<Card>)

                }
                else -> cardListFilter.postValue(cardList.value)
            }

        }
    }

    fun filterByName(value: String) {
        Log.e("value","value${value}")
        cardListFilter.value?.let {
            if (value.isEmpty() || it.isEmpty()) {
                cardListFilter.postValue(cardList.value)
            } else {
                cardListFilter.postValue(it.filter { card ->
                    card.name.lowercase(Locale.ROOT).contains(value.lowercase(Locale.ROOT))
                } as ArrayList<Card>)
            }

        }
    }
}