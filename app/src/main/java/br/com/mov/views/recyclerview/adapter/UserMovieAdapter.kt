package br.com.mov.views.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mov.R
import br.com.mov.extensions.formatForUSACoin
import br.com.mov.extensions.loadThumbnail
import br.com.mov.models.dto.BuyOrderRequest

class UserMovieAdapter(
        private val context: Context,
        private val buyOrders: List<BuyOrderRequest>?
) : RecyclerView.Adapter<UserMovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_user_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = buyOrders!!.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(this.buyOrders!![position])

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var buyOrder: BuyOrderRequest
        val movieTitle: TextView = itemView.findViewById(R.id.item_user_movie_movie_title)
        val movieImg: ImageView = itemView.findViewById(R.id.item_user_movie_movie_thumbnail)
        val dateStock: TextView = itemView.findViewById(R.id.item_user_movie__date_stock)
        val stockPrice: TextView = itemView.findViewById(R.id.item_user_movie_stock_price)
        val stockQuantity: TextView = itemView.findViewById(R.id.item_user_movie_stock_quantity)

        @SuppressLint("SetTextI18n")
        fun bind(buyOrder: BuyOrderRequest) {
            this.buyOrder = buyOrder
            movieTitle.text = buyOrder.movie!!.title
            buyOrder.movie!!.pictureUrl.let { movieImg.loadThumbnail(it) }
            dateStock.text = buyOrder.date
            stockPrice.text = buyOrder.price.formatForUSACoin()
            stockQuantity.text = "x " + buyOrder.quantity.toString()
        }
    }

}