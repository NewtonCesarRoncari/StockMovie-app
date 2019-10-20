package br.com.mov.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.mov.R;
import br.com.mov.models.Movie;
import br.com.mov.views.recyclerview.adapter.MovieAdapter;

public class SearchFragment extends Fragment {

    private MovieAdapter adapter;
    private RecyclerView movieList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_layout, container, false);
        getActivity().setTitle("Search");

        movieList = view.findViewById(R.id.Rv_search);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("The Martian", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("The Martian", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("The Martian", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("The Martian", R.drawable.themartian));

        this.adapter = new MovieAdapter(getContext(), movies);
        movieList.setAdapter(adapter);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null)
                    adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
