package fr.android.androidexercises;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fr.android.androidexercises.fragments.DetailBookFragment;
import fr.android.androidexercises.fragments.LibraryFragment;

public class LibraryActivity extends AppCompatActivity implements DetailBookFragment.ToBookDetail{

    Fragment detail ;
    /**
     * Dernier livre cliqué par l'utilisateur
     */
    Book currentBook;
    LibraryFragment libraryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        libraryFragment = getFragmentList();
        setContentView(R.layout.main_activity);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayoutList, this.libraryFragment,
                        LibraryFragment.class.getSimpleName()
                ).commit();
        if (!isPortrait()) // affichage du détail du livre
            goToDetailIfBook(savedInstanceState);

    }

    @Override
    public void goToDetail(Book book) {
        int idFragment = getMainFrameDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable("book", book);
        Fragment detailBook = new DetailBookFragment();
        detailBook.setArguments(bundle);
        this.detail = detailBook;
        this.currentBook = book;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().
                replace(idFragment,
                        detailBook,
                        DetailBookFragment.class.getSimpleName());
        if(isPortrait())transaction.addToBackStack("return_to_list");
        transaction.commit();
    }

    public boolean isPortrait(){

        return getResources().getBoolean(R.bool.portrait);
    }

    /**
     * Permet de determiner dans quelle Fram place le fragement détail, en fonction de l'orientation
     * @return idFram
     */
    public int getMainFrameDetail(){
        int idFragment = R.id.FrameLayoutList;
        if(!isPortrait()) {
            idFragment = R.id.FrameLayoutListDetail;
        }
        return idFragment;
    }

    public LibraryFragment getFragmentList(){
        LibraryFragment fragment = (LibraryFragment) getSupportFragmentManager().findFragmentByTag(LibraryFragment.class.getSimpleName());
        return  fragment==null? new LibraryFragment():fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("currentBook", this.currentBook);
        super.onSaveInstanceState(outState);
    }

    /**
     * Permet d'afficher le detail du livre si l'utilisateur en a déjà selectionné un avant de passer en paysage
     * @param saveInstanceState
     */
    public void goToDetailIfBook(Bundle saveInstanceState){
        if(saveInstanceState!=null)
        this.currentBook=saveInstanceState.getParcelable("currentBook");
        if(this.currentBook!=null)goToDetail(this.currentBook);
    }
}
