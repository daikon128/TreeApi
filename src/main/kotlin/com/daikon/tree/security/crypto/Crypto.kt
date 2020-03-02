package com.daikon.tree.security.crypto

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

fun encoder() : PasswordEncoder {
    return BCryptPasswordEncoder()
}
