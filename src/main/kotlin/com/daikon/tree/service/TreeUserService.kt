package com.daikon.tree.service

import com.daikon.tree.domain.TreeUser
import com.daikon.tree.repository.TreeUserRepository
import org.springframework.stereotype.Service

@Service
class TreeUserService (private val treeUserRepository: TreeUserRepository){
    fun findByEmail(email: String) : TreeUser {
        val treeUserEntity = treeUserRepository.findByEmailAndDelFlgIs(email)
        return TreeUser.of(treeUserEntity)
    }
}