package com.daikon.tree.service

import com.daikon.tree.domain.Card
import com.daikon.tree.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(private val cardRepository: CardRepository) {

    fun findAll(): List<Card> = cardRepository.findAll()

    fun findById(id: Long) = cardRepository.findById(id)

    fun save(card: Card) = cardRepository.save(card)

    fun delete(id: Long) = cardRepository.deleteById(id)

}