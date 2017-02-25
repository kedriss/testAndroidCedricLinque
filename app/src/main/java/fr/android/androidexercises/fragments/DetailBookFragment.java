package fr.android.androidexercises.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.androidexercises.Book;
import fr.android.androidexercises.R;

/**
 * Created by kedri on 22/02/2017.
 */

public class DetailBookFragment extends Fragment {
    private static final String BOOK_KEY = "book";

    private TextView titleTextView;
    private TextView priceTextView;
    private TextView isbn;
    private TextView synopsis;
    private ImageView coverBook;
    Book book;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_book, container, false);
        titleTextView   = (TextView) view.findViewById(R.id.titleDetailBook);
        priceTextView   = (TextView) view.findViewById(R.id.PrixDetail);
        synopsis        = (TextView) view.findViewById(R.id.SynopsisDetail);
        isbn            = (TextView) view.findViewById(R.id.codeISBN);
        coverBook       = (ImageView) view.findViewById(R.id.CoverImageDetail);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.book = getArguments().getParcelable(BOOK_KEY);
        titleTextView.setText(book.getTitle());
        this.priceTextView.setText("Price = "+book.getPrice()+" €");
        this.isbn.setText("Code ISBN :" + book.getIsbn());
        this.synopsis.setText(book.getSynopsis());
        Glide.with(this.getContext())
                .load(book.getCover())
                .into(this.coverBook);
    }

    public interface ToBookDetail{
        public void goToDetail(Book book);
    }
}
