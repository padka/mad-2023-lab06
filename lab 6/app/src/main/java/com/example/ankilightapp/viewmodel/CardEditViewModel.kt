package com.example.ankilightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankilightapp.model.Card

class CardEditViewModel : ViewModel() {

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    // Временно хранение карточек
    private val cardList = mutableListOf(
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

    fun saveCard(cardId: Long, word: String, translation: String, example: String) {
        if (cardId == 0L) {
            // Добавление новой карточки
            val newCard = Card(cardList.size.toLong() + 1, word, translation, example)
            cardList.add(newCard)
        } else {
            // Обновление существующей карточки
            val card = cardList.find { it.id == cardId }
            card?.apply {
                this.word = word
                this.translation = translation
                this.example = example
            }
        }
    }
}
