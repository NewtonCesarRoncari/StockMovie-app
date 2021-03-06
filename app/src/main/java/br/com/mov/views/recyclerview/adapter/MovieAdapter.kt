package br.com.mov.views.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mov.R
import br.com.mov.extensions.loadThumbnail
import br.com.mov.models.dto.MovieRequest
import java.util.*

class MovieAdapter(
        private val context: Context,
        private var movies: List<MovieRequest>,
        var onItemCliclListener:(movie: MovieRequest) -> Unit = {}
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>(), Filterable {

    private var movieListFull: List<MovieRequest> = movies.toList()

    //regionFilter
    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<MovieRequest> = mutableListOf()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(movieListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()

                for (movie in movieListFull) {
                    if (movie.title?.toLowerCase(Locale.getDefault())?.contains(filterPattern)!!) {
                        filteredList.add(movie)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            movies = (results.values as List<*>).filterIsInstance<MovieRequest>() as MutableList<MovieRequest>
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(movies[position])

    }

    override fun getItemCount() = movies.size

    inner class MyViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        private lateinit var movie: MovieRequest
        val movieTitle: TextView = itemView.findViewById(R.id.item_movie_title)
        val movieImg: ImageView = itemView.findViewById(R.id.fragment_movie_detail_item_movie_img)

        fun bind(movie: MovieRequest) {
            this.movie = movie
            movieTitle.text = movie.title
            movie.pictureUrl?.let { movieImg.loadThumbnail(it) }
        }

        init {
            itemView.setOnClickListener {
                if (::movie.isInitialized) {
                    onItemCliclListener(movie)
                }
            }
        }

    }

}
