package br.com.mov.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatForBrazilianCoin(): String {
    val BrazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return BrazilianFormat
        .format(this)
}