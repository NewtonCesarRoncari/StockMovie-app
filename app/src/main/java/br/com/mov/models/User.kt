package br.com.mov.models

import com.fasterxml.jackson.annotation.JsonIgnore

class User(val name: String, val email: String, val password: String) {
    @JsonIgnore
    var id: Long? = null
    @JsonIgnore
    var wallet: List<Position> = arrayListOf()
    @JsonIgnore
    var buyOrders: List<BuyOrder> = arrayListOf()
}

