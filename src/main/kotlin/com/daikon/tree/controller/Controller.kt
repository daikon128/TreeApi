package com.daikon.tree.controller

import com.daikon.tree.entity.CardEntity
import com.daikon.tree.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller {
    @Autowired
    private lateinit var cardService: CardService

    @GetMapping("/tree")
    fun showTreeAll() : List<CardEntity> {
        return cardService.findAll()
    }

    @GetMapping("/tree/{id}")
    fun showTreeById(@PathVariable id: Long) : List<CardEntity> {
        return cardService.findByUserId(id)
    }

    @PostMapping("/tree/add")
    fun addCard() {

    }

}