package com.daikon.tree.service

import com.daikon.tree.entity.TreeUserEntity
import com.daikon.tree.repository.TreeUserRepository
import com.daikon.tree.security.crypto.encoder
import org.springframework.stereotype.Service

@Service
class RegistrationService(private val treeUserRepository: TreeUserRepository) {
    fun registration(username: String, password: String) {
        createUser(username, password)
    }

    private fun createUser(username: String, password: String) : TreeUserEntity {
        val user = TreeUserEntity.new(username, encoder().encode(password))
        treeUserRepository.save(user)
        return user
    }
}