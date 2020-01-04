package com.daikon.tree.service

import com.daikon.tree.entity.CardEntity
import com.daikon.tree.http.AddCardRequest
import com.daikon.tree.http.AddCardResponse
import com.daikon.tree.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(private val cardRepository: CardRepository) {

    fun findAll(): List<CardEntity> = cardRepository.findAll()

    fun findByUserId(id: Long): List<CardEntity> = cardRepository.findByUserIdAndDelFlgIs(id)

    fun findById(id: Long) = cardRepository.findById(id)

    fun saveCard(card: AddCardRequest) : AddCardResponse {
        val cardEntity = addCardRequestToCardEntity(card)
        val saveResult = save(cardEntity)
        return cardEntityToAddCardResponse(saveResult)
    }

    fun save(card: CardEntity) = cardRepository.save(card)

    fun delete(id: Long) = cardRepository.deleteById(id)

    fun deleteById(id: Long) : Boolean {
        val result = findById(id)
        val target = result.get()
        val deleteCard = target.copy(delFlg = 1)
        save(deleteCard)
        return true
    }

}

fun addCardRequestToCardEntity(addCardRequest: AddCardRequest) : CardEntity {
    val parentId = if (addCardRequest.parentId != null && addCardRequest.parentId <= 0) {
        null
    } else {
        addCardRequest.parentId
    }
    return CardEntity(null,
            addCardRequest.userId,
            parentId,
            addCardRequest.title,
            addCardRequest.description,
            addCardRequest.progress,
            addCardRequest.importance,
            0)
}

fun cardEntityToAddCardResponse (card: CardEntity) : AddCardResponse {
    return AddCardResponse(card.parentId,
            card.title,
            card.description,
            card.progress,
            card.importance)
}
