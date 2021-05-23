package com.example.android.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;


/**
 * is a class whose role is to provide data to the UI and survive configuration changes
 */

public class WordViewModel extends AndroidViewModel {
    //variables
    private WordRepository mRepository;//variable para guardar la referencia del repositorio
    private LiveData<List<Word>> mAllWords;//variable para almacenar la lista de palabras

    /**
     * gets a reference to the WordRepository and gets the list of all words from the WordRepository
     *
     * @param application
     */
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }
    /**
     * method that gets all the words. This completely hides the implementation from the UI.
     *
     * @return gets all the words
     */
    LiveData<List<Word>> getAllWords() { return mAllWords; }
    /**
     * method that calls the Repository's insert() method.
     * In this way, the implementation of insert() is completely hidden from the UI.
     *
     * @param word recibe una Word
     */
    public void insert(Word word) { mRepository.insert(word); }
}