package com.daikon.tree.service

import com.daikon.tree.domain.Card
import com.daikon.tree.domain.Tree
import com.daikon.tree.domain.constructTree
import com.daikon.tree.entity.CardEntity
import com.daikon.tree.http.*
import com.daikon.tree.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(private val cardRepository: CardRepository) {

    fun findAll(): List<CardEntity> = cardRepository.findAll()

    fun findByUserId(id: Long): List<CardEntity> = cardRepository.findByUserIdAndDelFlgIs(id)

    fun findById(id: Long) = cardRepository.findById(id)

    fun getTreesByUserId(id: Long) : ShowTreeByUserIdResponse {
        val entities = findByUserId(id)
        return ShowTreeByUserIdResponse(cardEntitiesToTree(entities))
    }

    fun saveCard(card: AddCardRequest) : AddCardResponse {
        val cardEntity = addCardRequestToCardEntity(card)
        val saveResult = save(cardEntity)
        return cardEntityToAddCardResponse(saveResult)
    }

    fun save(card: CardEntity) = cardRepository.save(card)

    fun deleteById(id: Long) : Boolean {
        val result = findById(id)
        val target = result.get()
        val deleteCard = target.copy(delFlg = 1)
        save(deleteCard)
        return true
    }

    fun updateCard(card: UpdateCardRequest) : UpdateCardResponse {
        val entity = updateCardRequestToCardEntity(card)
        val result = save(entity)
        return cardEntityToUpdateCardResponse(result)
    }

}

fun cardEntitiesToTree(entities: List<CardEntity>) : List<Tree> {
    val nodeEntities = findNodes(entities, null)
    val nodeCards = nodeEntities
            .filter { r-> r.id != null }
            .map { r -> cardEntitieToChildressCard(r)}.toList()
    val childCardEntities = entities.filter { c -> c.parentId != null }.toList()
    val childCards = childCardEntities.map { c -> cardEntitieToChildressCard(c)}.toList()
    return nodeCards.map{ c -> constructTree(c, childCards) }.map { c -> Tree(c.id, c) }
}

fun findNodes(entities: List<CardEntity>, parentId: Long?) : List<CardEntity> =  entities.filter{ e-> e.parentId == parentId }.toList()

fun cardEntitieToChildressCard(cardEntity: CardEntity) : Card {
    return Card(cardEntity.id!!, cardEntity.parentId, setOf(), cardEntity.title, cardEntity.description, cardEntity.progress, cardEntity.importance)
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
    return AddCardResponse(
            card.id!!,
            card.parentId,
            card.title,
            card.description,
            card.progress,
            card.importance)
}

fun updateCardRequestToCardEntity(updateCardRequest: UpdateCardRequest) : CardEntity {
    return CardEntity(updateCardRequest.id,
            updateCardRequest.userId,
            updateCardRequest.parentId,
            updateCardRequest.title,
            updateCardRequest.description,
            updateCardRequest.progress,
            updateCardRequest.importance)
}

fun cardEntityToUpdateCardResponse (card: CardEntity) : UpdateCardResponse {
    return UpdateCardResponse(
            card.id!!,
            card.userId,
            card.parentId,
            card.title,
            card.description,
            card.progress,
            card.importance)
}
