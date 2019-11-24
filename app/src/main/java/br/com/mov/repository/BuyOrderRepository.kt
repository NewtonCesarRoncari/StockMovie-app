package br.com.mov.repository

import android.util.Log
import br.com.mov.models.BuyOrder
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.BuyOrderService

class BuyOrderRepository(
        private val service: BuyOrderService
) {

    fun postBuyOrder(buyOrder: BuyOrder) {
        val call = service.postBuyOrder(buyOrder)
        call.enqueue(CallbackWithReturn(
                object: CallbackWithReturn.AnswerCallback<Void>{
                    override fun whenSucess(result: Void) {
                        Log.i("retrofit", "request sucess")
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                    }
                }
        ))
    }
}