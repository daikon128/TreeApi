package com.daikon.tree.controller

import com.daikon.tree.http.RegistrationRequest
import com.daikon.tree.service.RegistrationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/registration")
class RegistrationController(private val registrationService: RegistrationService) {
    @PostMapping
    fun registration(@RequestBody registrationRequest: RegistrationRequest) {
        registrationService.registration(registrationRequest.username, registrationRequest.password)
    }
}
