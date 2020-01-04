package com.daikon.tree.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CardTest {
    fun createTerminusCard(id: Long, parentId: Long? = 1, title: String = "terminus", progress: Float = 0.0f, importance: Int = 1) : Card {
        return Card(id, parentId, setOf(), title, progress, importance)
    }
    fun createCardContainsChildren(nodeSize: Long, idOffset: Long = 0 , titlePrefix: String = "prefix", progress: Float = 0.0f) : Card {
        val parentId = idOffset + 1
        val childrenIdStart = parentId + 1
        val nodeFinitSize = childrenIdStart + nodeSize
        val children = (childrenIdStart.. nodeFinitSize).map{ i ->
            createTerminusCard( i, parentId, titlePrefix + i, progress)
        }.toSet()
        return Card(idOffset, null, children, titlePrefix + idOffset)
    }
    @Test
    fun プログレス計算0() {
        val parentCard = createCardContainsChildren(10)
        assertEquals(0.0f, parentCard.progress)

        val childCard = createTerminusCard(1)
        assertEquals(0.0f, childCard.progress)
    }

    @Test
    fun プログレス計算30() {
        val parentCard = createCardContainsChildren(10, progress=30.0f)
        assertEquals(30.0f, parentCard.progress)

        val childCard = createTerminusCard(1, progress=30.0f)
        assertEquals(30.0f, childCard.progress)
    }

    @Test
    fun プログレス計算100() {
        val parentCard = createCardContainsChildren(10, progress=100.0f)
        assertEquals(100.0f, parentCard.progress)

        val childCard = createTerminusCard(1, progress=100.0f)
        assertEquals(100.0f, childCard.progress)
    }

    @Test
    fun バラバラなimportance100() {
        val children = setOf(
                createTerminusCard(2, progress = 100.0f, importance = 1),
                createTerminusCard(3, progress = 100.0f, importance = 2),
                createTerminusCard(4, progress = 100.0f, importance = 3),
                createTerminusCard(5, progress = 100.0f, importance = 4)
        )
        val parentCard = Card(1, null, children, "parent")
        assertEquals(100.0f, parentCard.progress)
    }

    @Test
    fun バラバラなimportance30() {
        val children = setOf(
                createTerminusCard(2, progress=30.0f, importance = 1),
                createTerminusCard(3, progress=30.0f, importance = 2),
                createTerminusCard(4, progress=30.0f, importance = 3),
                createTerminusCard(5, progress=30.0f, importance = 4)
        )
        val parentCard = Card(1,null, children, "parent")
        assertEquals(30.0f, parentCard.progress)
    }

    @Test
    fun バラバラなimportanceバラバラなprogress() {
        val children = setOf(
                createTerminusCard(2, progress=100.0f, importance = 1),
                createTerminusCard(3, progress=0.0f, importance = 2),
                createTerminusCard(4, progress=0.0f, importance = 2),
                createTerminusCard(5, progress=0.0f, importance = 4),
                createTerminusCard(6, progress=0.0f, importance = 4),
                createTerminusCard(7, progress=0.0f, importance = 4),
                createTerminusCard(8, progress=0.0f, importance = 4)
        )
        val parentCard = Card(1, null, children, "parent")
        assertEquals(33.3f, parentCard.progress)
    }

    @Test
    fun copy() {
        val children = setOf(
                createTerminusCard(2, progress=100.0f, importance = 1),
                Card(7,
                        null,
                        setOf(
                        createTerminusCard(8, progress=0.0f, importance = 4)
                ), "terminus", 0.0f, importance = 4)
        )
        val parentCard = Card(1,null, children, "parent")
        val otherParentCard = parentCard.copy()
        assertEquals(otherParentCard, parentCard)
        val newCard = createTerminusCard(3, progress=100.0f, importance = 1)
        val addedParentCard = parentCard.addCard(newCard)
        assertNotEquals(addedParentCard, otherParentCard)
    }

    @Test
    fun カード追加() {
        val children = setOf(
                createTerminusCard(2, 1, progress=100.0f, importance = 1),
                createTerminusCard(3, 1, progress=0.0f, importance = 2),
                createTerminusCard(4, 1, progress=0.0f, importance = 2),
                createTerminusCard(5, 1, progress=0.0f, importance = 4),
                createTerminusCard(6, 1, progress=0.0f, importance = 4),
                createTerminusCard(7, 1, progress=0.0f, importance = 4)
        )
        val addCard = createTerminusCard(8, progress=0.0f, importance = 4)
        val parentCard = Card(1, null, children, "parent")
        val addedParentCard = parentCard.addCard(addCard)
        val expectedCard = Card(1, null, children + setOf(addCard), "parent")
        assertEquals(expectedCard, addedParentCard)
    }

    @Test
    fun カード追加_子供() {
        val children = setOf(
                createTerminusCard(7, 1, progress=0.0f, importance = 4)
        )
        val expectedChildren = setOf(
                Card(7,
                        1,
                        setOf(
                        createTerminusCard(8, 7, progress=0.0f, importance = 4)
                ), "terminus", 0.0f, importance = 4)
        )
        val addCard = createTerminusCard(8, 7, progress=0.0f, importance = 4)
        val parentCard = Card(1,
                null,
                children, "parent")
        val addedParentCard = parentCard.addCard(addCard)
        val expectedCard = Card(1, null, expectedChildren, "parent")
        assertEquals(expectedCard, addedParentCard)
    }

    @Test
    fun tree構築() {
        val children = listOf(
                createTerminusCard(2, 1),
                createTerminusCard(3, 2),
                createTerminusCard(4, 1)
        )
        val parentCard = Card(1, null, setOf(), "parent")

        val expectedCard = Card(
                1,
                null,
                setOf(
                        createTerminusCard(2, 1).addCard(
                                createTerminusCard(3,2)
                        ),
                        createTerminusCard(4,1)
                ), "parent")

        val tree = constructTree(parentCard, children)

        assertEquals(expectedCard, tree)
    }
}