package com.daikon.tree.domain

import kotlin.math.roundToInt

// IDで等価性を判断。同名、同プログレスの場合が考えられる。
// 別のTreeに持たせることもできるようにする。
// => 一つのコンテンツが複数の目標に影響することもあるから。
data class Card(val id: Int, var children: Set<Card>, val title: String, var ownProgressValue: Float = 0.0f, val importance: Int = 1) {
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
    fun addCard(parentId: Int, card: Card) : Card{
        val retCard = copy()
        if (parentId == retCard.id) {
            retCard.children = children + setOf(card)
        }
        return retCard.copy(
                children = retCard
                            .children
                            .map{ c -> c.addCard( parentId, card) }
                            .toSet())
    }

    fun copy(): Card {
        return Card(id, children.map { c -> c.copy() }.toSet(),title, ownProgressValue, importance)
    }

}

class Tree(val id: Int, private val root: Card) {
    fun addCard(parentId: Int, card: Card) : Tree {
        return Tree(this.id, root.addCard(parentId, card))
    }
}
