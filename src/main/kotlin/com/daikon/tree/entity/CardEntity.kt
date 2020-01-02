package com.daikon.tree.entity

import javax.persistence.*

@Entity
@Table(name="card")
data class CardEntity (
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(name="user_id", nullable=false)
    val userId: Long? = null,

    @Column(name="parent_id")
    val parentId: Long? = null,

    @Column(name="title", length=255, nullable=false)
    val title: String = "",

    @Column(name="description", length=255, nullable=false)
    val description: String = ""
)
