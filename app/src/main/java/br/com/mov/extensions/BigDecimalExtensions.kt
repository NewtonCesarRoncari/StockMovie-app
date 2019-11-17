package br.com.mov.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatForBrazilianCoin(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat
        .format(this)
}

fun BigDecimal.formatForUSACoin(): String {
    val usaFormat = DecimalFormat
            .getCurrencyInstance(Locale("en", "us"))
    return usaFormat
            .format(this)
}