package com.example.ankilightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankilightapp.model.Card

class CardListViewModel : ViewModel() {

    private val cardList = mutableListOf(
        Card(1, "Word1", "Translation1", "Example1"),
        Card(2, "Word2", "Translation2", "Example2")
    )

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> get() = _cards

    init {
        _cards.value = cardList
    }

    fun deleteCard(card: Card) {
        cardList.remove(card)
        _cards.value = cardList
    }
}
