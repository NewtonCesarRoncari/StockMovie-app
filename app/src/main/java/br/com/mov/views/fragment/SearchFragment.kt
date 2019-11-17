package br.com.mov.views.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.mov.R
import br.com.mov.views.recyclerview.adapter.MovieAdapter
import br.com.mov.views.viewmodel.MovieViewModel
import br.com.mov.views.viewmodel.StateAppComponentsViewModel
import br.com.mov.views.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_search_layout.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private var adapter: MovieAdapter? = null
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val movieViewModel: MovieViewModel by sharedViewModel()
    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        appComponentsViewModel.havCoponent = VisualComponents(true)

        return inflater.inflate(R.layout.fragment_search_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMovieAdapter()

        setHasOptionsMenu(true)
    }

    private fun initMovieAdapter() {
        movieViewModel.checkMoviesReturned()?.observe(viewLifecycleOwner,
                androidx.lifecycle.Observer { movieList ->
                    if (movieList != null) {
                        this.adapter = context?.let { context ->
                            MovieAdapter(context, movieList)
                        }!!
                        rv_search.adapter = adapter
                    }
                })
    }

    private fun goToMovieDetailFragment() {
        navController.navigate(R.id.action_searchFragment_to_movieDetailFragment)
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
                    adapter?.filter?.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}
