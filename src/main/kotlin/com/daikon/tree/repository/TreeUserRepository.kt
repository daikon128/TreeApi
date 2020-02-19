package com.daikon.tree.repository

import com.daikon.tree.entity.TreeUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeUserRepository: JpaRepository<TreeUserEntity, Long> {
    fun findByUsernameAndDelFlgIs(username: String, delFlg: Int = 0) : TreeUserEntity
}