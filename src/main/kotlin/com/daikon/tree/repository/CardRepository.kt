package com.daikon.tree.repository

import com.daikon.tree.domain.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : JpaRepository<Card, Long> {

}