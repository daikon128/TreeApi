package com.daikon.tree.domain

import java.lang.Math.round
import kotlin.math.roundToInt

// IDで等価性を判断。同名、同プログレスの場合が考えられる。
// 別のTreeに持たせることもできるようにする。
// => 一つのコンテンツが複数の目標に影響することもあるから。
class Card(val id: Int, val children: Set<Card>, val title: String, var ownProgressValue: Float = 0.0f, val importance: Int = 1) {
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
}

