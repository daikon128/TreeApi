package com.daikon.tree.domain

import kotlin.math.roundToInt

// IDで等価性を判断。同名、同プログレスの場合が考えられる。
// 別のTreeに持たせることもできるようにする。
// => 一つのコンテンツが複数の目標に影響することもあるから。
data class Card(val id: Long, val parentId: Long?, var children: Set<Card>, val title: String, val description: String, var ownProgressValue: Float = 0.0f, val importance: Int = 1) {
    var progress: Float
    get() {
        if (children.isEmpty()) {
            return this.ownProgressValue
        }
        return ((children.map { c -> c.progress / c.importance }.sum()
                / (children.map { c -> 1.0f / c.importance }.sum())) * 10).roundToInt() / 10.0f
    }
    set(progress) {
        if (children.isEmpty()) {
            this.ownProgressValue = progress
        }
    }
    fun addCard(card: Card) : Card{
        val retCard = copy()
        if (card.parentId == retCard.id) {
            retCard.children = children + setOf(card)
        }
        return retCard.copy(
                children = retCard
                            .children
                            .map{ c -> c.addCard( card ) }
                            .toSet())
    }

    fun findCard(id: Long) : Boolean {
        return if (this.id == id) true
               else children.any { c -> c.findCard(id) }
    }

    fun copy(): Card {
        return Card(id, parentId, children.map { c -> c.copy() }.toSet(),title, description, ownProgressValue, importance)
    }

}

class Tree(val id: Long, val root: Card) {
    fun addCard(card: Card) : Tree {
        return Tree(this.id, root.addCard(card))
    }

    fun findCard(id: Long) : Boolean {
        return root.findCard(id)
    }

}
fun constructTree(card: Card, entities: List<Card>) : Card {
    val newCard = entities.filter { e -> e.parentId != null }
            .fold(card) { c1, c2 -> c1.addCard(c2) }
    return if (card == newCard) card
    else constructTree(newCard, entities)
}
