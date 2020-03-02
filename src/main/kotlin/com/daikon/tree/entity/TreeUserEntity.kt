package com.daikon.tree.entity

import javax.persistence.*

@Entity
@Table(name = "tree_user")
data class TreeUserEntity(
    @Id
    @GeneratedValue
    val id: Long? = 0,

    @Column(nullable=false, unique=true)
    val username: String = "",

    @Column(nullable=false)
    val password: String = "",
    @Column(nullable=false)
    val delFlg: Int = 0
) {
    companion object {
        fun new(username: String, password: String) : TreeUserEntity {
            return TreeUserEntity(0, username, password, 0)
        }
    }
}
