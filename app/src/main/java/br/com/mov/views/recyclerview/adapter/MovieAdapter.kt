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
import br.com.mov.models.Movie

class MovieAdapter(
        private val context: Context,
        private var movies: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>(), Filterable {

    private var movieListFull: MutableList<Movie> = mutableListOf()

    //regionFilter
    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Movie> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(movieListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (movie in movieListFull) {
                    if (movie.title.toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            movies.clear()
            if (results.values is List<*>){
                val movieList = (results.values as List<*>).filterIsInstance<Movie>()
                movies.addAll(movieList)
                notifyDataSetChanged()
            }
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

        holder.movieTitle.text = movies[position].title
        holder.movieImg.setImageResource(movies[position].thumbnail)

    }

    override fun getItemCount() = movies.size


    inner class MyViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        val movieTitle: TextView = itemView.findViewById(R.id.item_movie_title)
        val movieImg: ImageView = itemView.findViewById(R.id.item_movie_img)

    }
}
