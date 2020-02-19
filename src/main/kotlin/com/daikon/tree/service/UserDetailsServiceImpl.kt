package com.daikon.tree.service

import com.daikon.tree.domain.TreeUser
import com.daikon.tree.domain.TreeUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var treeUserService: TreeUserService

    override fun loadUserByUsername(username: String): UserDetails {
        var treeUser : TreeUser? = null
        try {
            treeUser = treeUserService.findByUsername(username)
        } catch (e: Exception) {
            throw UsernameNotFoundException("user not found")
        }

        if (treeUser == null) {
            throw UsernameNotFoundException("user not found")
        }

        return TreeUserDetails(treeUser)
    }
}
