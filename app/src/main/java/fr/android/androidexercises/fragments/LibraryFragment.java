package fr.android.androidexercises.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.android.androidexercises.Book;
import fr.android.androidexercises.BookRecyclerAdapter;
import fr.android.androidexercises.HenriPotierService;
import fr.android.androidexercises.LibraryActivity;
import fr.android.androidexercises.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class LibraryFragment extends Fragment  {
    private static  final String URL_POTIER="http://henri-potier.xebia.fr/";
    private static  final String BOOKS_KEY="books";
    RecyclerView recyclerView;
    List<Book> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        System.out.println("salut les mecs");
        View rootView = inflater.inflate(R.layout.activity_library_fragement, container, false);
        setRecyclerView(rootView);
        HenriPotierService service = getHenriPotierService();
        if(savedInstanceState == null){
            service.listBooks().enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    books =  response.body();
                    if(books !=null)
                        recyclerView.setAdapter(new BookRecyclerAdapter(LayoutInflater.from(getActivity()), response.body()));
                }
                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    Timber.e("Erreur de chargement des livres");
                }
        });
    }else{   // récuperation des livres préchargé
            this.books = savedInstanceState.getParcelableArrayList(BOOKS_KEY);
            recyclerView.setAdapter(
                    new BookRecyclerAdapter(LayoutInflater.from(getActivity()),this.books)
            );
        }
        return rootView;
    }

    private void setRecyclerView(View rootView) {
        recyclerView =(RecyclerView) rootView.findViewById(R.id.bookListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private HenriPotierService getHenriPotierService() {
        Retrofit retrofit = getretrofit();
        return retrofit.create(HenriPotierService.class);
    }

    @NonNull
    private Retrofit getretrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL_POTIER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
       if(this.books != null) {
           ArrayList<Book> savedBooks = new ArrayList<Book>();
           savedBooks.addAll(this.books);
           outState.putParcelableArrayList(BOOKS_KEY, savedBooks);
           super.onSaveInstanceState(outState);
       }
    }
}
