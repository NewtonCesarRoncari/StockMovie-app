package br.com.mov.models

import java.math.BigDecimal

class BuyOrder(
        val id: Long,

        val movie: Movie,

        val user: User,

        val quantity: Long,

        val price: BigDecimal

//        val date: LocalDateTime = LocalDateTime.now()
)

