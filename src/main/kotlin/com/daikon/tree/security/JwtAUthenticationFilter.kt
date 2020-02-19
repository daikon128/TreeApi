package com.daikon.tree.security

import com.daikon.tree.domain.TreeUser
import com.daikon.tree.entity.TreeUserEntity
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(private val authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    override fun getAuthenticationManager(): AuthenticationManager {
        return super.getAuthenticationManager()
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val treeUser: TreeUserEntity = ObjectMapper().readValue(request.inputStream, TreeUserEntity::class.java)
        val authenticationToken = UsernamePasswordAuthenticationToken(treeUser.username, treeUser.password)
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse,
                                          filterChain: FilterChain?, authentication: Authentication) {
        val user = authentication.getPrincipal() as User
        val roles = user.getAuthorities()
                .stream()
                .map({ obj: GrantedAuthority -> obj.authority })
                .collect(Collectors.toList())
        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()
        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact()
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token)
    }

    init {
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL)
    }
}

