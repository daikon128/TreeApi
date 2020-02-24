package com.daikon.tree.domain

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class TreeUserDetails (treeUser: TreeUser): User( treeUser.username, treeUser.password,
        AuthorityUtils.createAuthorityList("ROLE_USER")) {

    val id: Long
    get() = this.treeUser.id

    private val treeUser: TreeUser = treeUser
}

