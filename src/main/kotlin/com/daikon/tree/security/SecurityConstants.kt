package com.daikon.tree.security

import java.lang.IllegalStateException

class SecurityConstants private constructor() {
    companion object {
        val AUTH_LOGIN_URL: String = "/api/authenticate"
        // Signing key for HS512 algorithm
        // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
        val JWT_SECRET: String = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y\$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf"
        // JWT token defaults
        val TOKEN_HEADER: String = "Authorization"
        val TOKEN_PREFIX: String = "Bearer "
        val TOKEN_TYPE: String = "JWT"
        val TOKEN_ISSUER: String = "secure-api"
        val TOKEN_AUDIENCE: String = "secure-app"
    }

    init {
        throw IllegalStateException("Cannot create instance of static util class")
    }
}

