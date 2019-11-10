package br.com.mov.models.dtos

import br.com.mov.models.BuyOrder
import br.com.mov.models.Position
import com.fasterxml.jackson.annotation.JsonIgnore

class UserRequest(val id: Long? = null,
                  val name: String = "",
                  val email: String = "",
                  @JsonIgnore
                  val wallet: List<Position> = arrayListOf(),
                  @JsonIgnore
                  val buyOrders: List<BuyOrder> = arrayListOf())