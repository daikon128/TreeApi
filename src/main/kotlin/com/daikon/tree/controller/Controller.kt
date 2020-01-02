package com.daikon.tree.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/tree")
class Controller {
    @PostMapping
    fun showTree(@RequestParam id: Int) {

    }

    @PostMapping("/add")
    fun addCard() {

    }

}