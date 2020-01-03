package com.daikon.tree.repository

import com.daikon.tree.entity.CardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : JpaRepository<CardEntity, Long> {
    fun findByUserIdAndDelFlgIs(userId: Long, delFlg: Int = 0) : List<CardEntity>
}