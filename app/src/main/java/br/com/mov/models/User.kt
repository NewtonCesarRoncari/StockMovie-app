package br.com.mov.models

import br.com.mov.models.dtos.UserRequest
import com.fasterxml.jackson.annotation.JsonIgnore

class User(@JsonIgnore
           val id: Long? = null,
           val name: String = "",
           val email: String = "",
           val password: String = "",
           @JsonIgnore
           val wallet: List<Position> = arrayListOf(),
           @JsonIgnore
           val buyOrders: List<BuyOrder> = arrayListOf()) {

    constructor(user: UserRequest) : this(
            id = user.id,
            name = user.name,
            email = user.email,
            wallet = user.wallet,
            buyOrders = user.buyOrders
    )
}

