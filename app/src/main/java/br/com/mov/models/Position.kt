package br.com.mov.models

import java.math.BigDecimal


class Position(
        val id: kotlin.Long,

        val movie: Movie,

        val user: User,

        val quantity: kotlin.Long,

        val mediumPrice: BigDecimal
)
