package com.example.ankilightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankilightapp.model.Card

class CardDetailViewModel : ViewModel() {

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    // Временно хранение карточек
    private val cardList = listOf(
        Card(1, "Word1", "Translation1", "Example1"),
        Card(2, "Word2", "Translation2", "Example2")
    )

    fun loadCard(cardId: Long) {
        // Поиск карточки в списке
        val card = cardList.find { it.id == cardId }
        card?.let {
            _card.value = it
        }
    }
}
