package br.com.mov.views.viewmodel

import androidx.lifecycle.ViewModel
import br.com.mov.models.BuyOrder
import br.com.mov.repository.BuyOrderRepository

class BuyOrderViewModel(private val repository: BuyOrderRepository): ViewModel(){

    fun postBuyOrder(buyOrder: BuyOrder) = repository.postBuyOrder(buyOrder)
}