package com.daikon.tree.domain

import com.daikon.tree.entity.TreeUserEntity

data class TreeUser(val id: Long, val username: String, val password: String){
    companion object {
        fun of(entity: TreeUserEntity) : TreeUser {
            return TreeUser(entity.id!!, entity.username, entity.password)
        }
    }

}

