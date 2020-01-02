package com.daikon.tree.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {
    fun createTerminusCard(id: Int, title: String = "terminus", progress: Float = 0.0f) : Card {
        return Card(id, setOf(), title, progress)
    }
    fun createCardContainsChildren(nodeSize: Int, idOffset: Int = 0 , titlePrefix: String = "prefix", progress: Float = 0.0f) : Card {
        val parentId = idOffset + 1
        val childrenIdStart = parentId + 1
        val nodeFinitSize = childrenIdStart + nodeSize
        val children = (childrenIdStart.. nodeFinitSize).map{ i ->
            createTerminusCard( i, titlePrefix + i, progress)
        }.toSet()
        return Card(idOffset, children, titlePrefix + idOffset)
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
}