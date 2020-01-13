package com.daikon.tree.domain

import com.daikon.tree.entity.TreeUserEntity

class TreeUser(val userName: String, val password: String){
    companion object {
        fun of(entity: TreeUserEntity) : TreeUser {
            return TreeUser(entity.email, entity.password)
        }
    }

}

