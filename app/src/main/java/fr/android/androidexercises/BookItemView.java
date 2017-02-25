package fr.android.androidexercises;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.androidexercises.fragments.DetailBookFragment;

public class BookItemView extends LinearLayout {

    private TextView nameTextView;
    private android.widget.ImageView imageView;
    DetailBookFragment.ToBookDetail listener;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listener= (DetailBookFragment.ToBookDetail) context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
         this.nameTextView = (TextView) findViewById(R.id.titleTextView);
         this.imageView = (ImageView) findViewById(R.id.coverBookItemView);
    }

    public void bindView(final Book book) {
        System.out.println(book.getTitle());
        this.nameTextView.setText(book.getTitle());
        Glide.with(this.getContext())
                        .load(book.getCover())
                .into(this.imageView);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.goToDetail(book);
            }
        });
    }
}
