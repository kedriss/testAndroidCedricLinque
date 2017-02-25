package fr.android.androidexercises;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Book implements Parcelable{
    protected Book(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        price = in.readString();
        cover = in.readString();
        synopsis = in.readArrayList(ClassLoader.getSystemClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getSynopsis() {
        if (synopsis != null )return synopsis.get(0);
        return "";
    }

    public void setSynopsis(String synopsis) {
        this.synopsis.add(synopsis);
    }

    private String isbn;
    private String title;
    private String price;
    private String cover;
    private ArrayList<String> synopsis;



    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn.equals(book.isbn);

    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(cover);
        dest.writeArray(synopsis.toArray());
    }
}
