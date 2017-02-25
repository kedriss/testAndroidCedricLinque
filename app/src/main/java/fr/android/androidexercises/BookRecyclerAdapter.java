package fr.android.androidexercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kedri on 21/02/2017.
 */
public class BookRecyclerAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<Book> books;

    public BookRecyclerAdapter(LayoutInflater inflater, List<Book> books) {
        this.inflater = inflater;
        this.books= books;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_book, parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BookItemView itemView =(BookItemView)holder.itemView;
        itemView.bindView(this.books.get(position));
    }

    @Override
    public int getItemCount() {
        return (books==null)?0:books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
