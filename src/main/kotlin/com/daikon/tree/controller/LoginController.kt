package com.daikon.tree.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController {
    @GetMapping
    fun login() : String {
        return "foo"
    }

    @PostMapping
    fun loginPost() : String {
        return "bar"
    }
}
