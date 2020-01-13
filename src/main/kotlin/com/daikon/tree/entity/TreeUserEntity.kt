package com.daikon.tree.entity

import javax.persistence.*

data class TreeUserEntity(
    @Id
    @GeneratedValue
    val id: Long? = 0,
    @Column(nullable=false, unique=true)
    val email: String = "",
    @Column(nullable=false)
    val password: String = "",
    @Column(nullable=false)
    val delFlg: Int = 0
)
