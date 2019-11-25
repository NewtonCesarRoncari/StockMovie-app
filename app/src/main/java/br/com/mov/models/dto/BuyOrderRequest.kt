package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.math.BigDecimal
import java.util.*


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "quantity", "movie", "price", "date")
class BuyOrderRequest {

    var id: Long? = null

    var quantity: Long? = null

    var movie: MovieRequest? = null

    var price: BigDecimal = BigDecimal.ZERO

    var date: String = ""

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = HashMap()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}