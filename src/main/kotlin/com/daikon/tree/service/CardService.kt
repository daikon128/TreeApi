package com.daikon.tree.service

import com.daikon.tree.entity.CardEntity
import com.daikon.tree.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(private val cardRepository: CardRepository) {

    fun findAll(): List<CardEntity> = cardRepository.findAll()

    fun findByUserId(id: Long): List<CardEntity> = cardRepository.findByUserIdAndAndDelFlgIs(id)

    fun findById(id: Long) = cardRepository.findById(id)

    fun save(card: CardEntity) = cardRepository.save(card)

    fun delete(id: Long) = cardRepository.deleteById(id)

}