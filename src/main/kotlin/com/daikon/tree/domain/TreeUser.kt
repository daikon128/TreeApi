package com.daikon.tree.domain

import com.daikon.tree.entity.TreeUserEntity

data class TreeUser(val username: String, val password: String){
    companion object {
        fun of(entity: TreeUserEntity) : TreeUser {
            return TreeUser(entity.username, entity.password)
        }
    }

}

