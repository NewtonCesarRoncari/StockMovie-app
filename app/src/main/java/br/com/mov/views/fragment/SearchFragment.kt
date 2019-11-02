package br.com.mov.views.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import br.com.mov.R
import br.com.mov.models.Movie
import br.com.mov.views.recyclerview.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_search_layout.*

class SearchFragment : Fragment() {

    private var adapter: MovieAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_layout, container, false)
        activity?.title = "Search"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = arrayListOf(
                Movie("Moana", R.drawable.moana),
                Movie("Black P", R.drawable.blackp),
                Movie("The Martian", R.drawable.themartian),
                Movie("Moana", R.drawable.moana),
                Movie("Black P", R.drawable.blackp),
                Movie("The Martian", R.drawable.themartian),
                Movie("Moana", R.drawable.moana),
                Movie("Black P", R.drawable.blackp),
                Movie("The Martian", R.drawable.themartian),
                Movie("Moana", R.drawable.moana),
                Movie("Black P", R.drawable.blackp),
                Movie("The Martian", R.drawable.themartian)
        )

        this.adapter = context?.let { MovieAdapter(it, movies) }
        rv_search.adapter = adapter

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (adapter != null)
                    adapter!!.filter.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}
