package com.daikon.tree.domain

// IDで等価性を判断。同名、同プログレスの場合が考えられる。
// 別のTreeに持たせることもできるようにする。
// => 一つのコンテンツが複数の目標に影響することもあるから。
class Card(val id: Int, val children: Set<Card>, val title: String, var ownProgressValue: Int = 0, val importance: Int = 1) {
    var progress: Int
    get() {
        if (children.isEmpty()) {
            return this.ownProgressValue
        }
        return children.map{ c -> c.progress }.sum() / children.size
    }
    set(progress: Int) {
        if (children.isEmpty()) {
            this.ownProgressValue = progress
        }
    }
}

