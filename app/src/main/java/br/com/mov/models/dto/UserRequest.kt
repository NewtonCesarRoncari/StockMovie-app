package br.com.mov.models.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class UserRequest(val id: Long? = null, val name: String = "", val email: String = "", val balance: BigDecimal = BigDecimal.ZERO)