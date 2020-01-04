package com.daikon.tree.controller

import com.daikon.tree.domain.Tree
import com.daikon.tree.entity.CardEntity
import com.daikon.tree.http.AddCardRequest
import com.daikon.tree.http.AddCardResponse
import com.daikon.tree.http.UpdateCardRequest
import com.daikon.tree.http.UpdateCardResponse
import com.daikon.tree.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tree")
class Controller {
    @Autowired
    private lateinit var cardService: CardService

    @GetMapping
    fun showTreeAll() : List<CardEntity> {
        return cardService.findAll()
    }

    @GetMapping("/{id}")
    fun showTreeByUserId(@PathVariable id: Long) : List<Tree> {
        return cardService.getTreesByUserId(id)
    }

    @PostMapping("/add")
    fun addCard(@RequestBody addCardRequest: AddCardRequest) : AddCardResponse {
        return cardService.saveCard(addCardRequest)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteCard(@PathVariable id: Long) : Boolean {
        return cardService.deleteById(id)
    }

    @PostMapping("/update")
    fun updateCard(@RequestBody updateCardRequest: UpdateCardRequest) : UpdateCardResponse {
        return cardService.updateCard(updateCardRequest)
    }

}

