package com.daikon.tree.entity

import javax.persistence.*

@Entity
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
)
