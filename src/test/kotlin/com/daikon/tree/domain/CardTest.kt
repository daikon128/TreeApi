package com.daikon.tree.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {
    fun createTerminusCard(id: Int, title: String = "terminus", progress: Int = 0) : Card {
        return Card(id, setOf(), title, progress)
    }
    fun createCardContainsChildren(nodeSize: Int, idOffset: Int = 0 , titlePrefix: String = "prefix", progress: Int = 0) : Card {
        val parentId = idOffset + 1
        val childrenIdStart = parentId + 1
        val nodeFinitSize = childrenIdStart + nodeSize
        val children = (childrenIdStart.. nodeFinitSize).map{ i ->
            createTerminusCard( i, titlePrefix + i, progress)
        }.toSet()
        return Card(idOffset, children, titlePrefix + idOffset)
    }
    @Test
    fun プログレス計算０() {
        val parentCard = createCardContainsChildren(10)
        assertEquals(0, parentCard.progress)

        val childCard = createTerminusCard(1)
        assertEquals(0, childCard.progress)
    }

    @Test
    fun プログレス計算３０() {
        val parentCard = createCardContainsChildren(10, progress=30)
        assertEquals(30, parentCard.progress)

        val childCard = createTerminusCard(1, progress=30)
        assertEquals(30, childCard.progress)
    }

    @Test
    fun プログレス計算100() {
        val parentCard = createCardContainsChildren(10, progress=100)
        assertEquals(100, parentCard.progress)

        val childCard = createTerminusCard(1, progress=30)
        assertEquals(100, childCard.progress)
    }
}