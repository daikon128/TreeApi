package com.daikon.tree.domain

import java.lang.Math.round
import java.util.stream.Collectors.toSet
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
    set(progress: Float) {
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

    override fun equals(other: Any?): Boolean {
        val otherCard = other as Card
        return (this.id == otherCard.id
                && this.importance == otherCard.importance
                && this.ownProgressValue == otherCard.ownProgressValue
                && this.title == otherCard.title
                && this.children == otherCard.children)
    }

    fun copy(): Card {
        return Card(id, children.map { c -> c.copy() }.toSet(),title, ownProgressValue, importance)
    }

}

class Tree(val id: Int, private val root: Card) {
    fun addCard(parentId: Int, card: Card) {
        root.addCard(parentId, card)
    }
}
