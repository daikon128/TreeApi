package com.daikon.tree.service

import com.daikon.tree.domain.TreeUser
import com.daikon.tree.repository.TreeUserRepository
import org.springframework.stereotype.Service

@Service
class TreeUserService (private val treeUserRepository: TreeUserRepository){
    fun findByUsername(username: String) : TreeUser {
        val treeUserEntity = treeUserRepository.findByUsernameAndDelFlgIs(username)
        return TreeUser.of(treeUserEntity)
    }
}