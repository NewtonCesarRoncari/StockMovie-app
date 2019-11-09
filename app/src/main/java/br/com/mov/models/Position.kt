package br.com.mov.models

import java.math.BigDecimal


class Position(
        val id: Long,

        val movie: Movie,

        val user: User,

        val quantity: Long,

        val mediumPrice: BigDecimal
)
