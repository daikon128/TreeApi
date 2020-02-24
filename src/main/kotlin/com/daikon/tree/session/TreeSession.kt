package com.daikon.tree.session

import com.daikon.tree.entity.TreeUserEntity
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

class TreeSession {
    companion object {
        private val pool = JedisPool(JedisPoolConfig(), "localhost")

        fun set(key: TreeSessionKey, value: String) {
            pool.resource.use {
                it.sadd(key.value, value)
                it.close()
            }
        }

        fun setUser(treeUserEntity: TreeUserEntity) {
            pool.resource.use {
                it.sadd(TreeSessionKey.SESSION_USER_KEY_PREFIX.name + treeUserEntity.id, treeUserEntity.username)
                it.close()
            }
        }

        fun delete(key: TreeSessionKey, value: String) {
            pool.resource.use {
                it.srem(key.value, value)
                it.close()
            }
        }

        fun exists(key: TreeSessionKey, value: String) : Boolean {
            var result = false
            pool.resource.use {
                result = it.sismember(key.value, value)
                it.close()
            }
            return result
        }

    }
}

enum class TreeSessionKey(val value: String) {
   SESSION_USER_KEY("active-subject"),
   SESSION_USER_KEY_PREFIX("user:")
}