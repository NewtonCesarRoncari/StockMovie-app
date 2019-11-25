package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.*
import java.math.BigDecimal
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "name", "email", "balance", "wallet", "buyOrders")
class UserRequest {

    var id: Long? = null

    var name: String = ""

    var email: String = ""

    var balance: BigDecimal = BigDecimal.ZERO

    var wallet: List<PositionRequest>? = null

    var buyOrders: List<BuyOrderRequest> = arrayListOf()
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