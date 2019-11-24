package br.com.mov.retrofit.service

import br.com.mov.models.BuyOrder
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BuyOrderService {

    @POST("orders")
    fun postBuyOrder(@Body buyOrder: BuyOrder): Call<Void>
}