package com.example.android.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


/**
 * class WordRepository is a class that abstracts access to multiple data sources
 */

public class WordRepository {
    //variables
    private WordDao mWordDao;//DAO
    private LiveData<List<Word>> mAllWords;//lista de words

    /**
     * Constructor de la clase WordRepository
     */
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    /**
     * Método getAllWords
     *
     * @return retorna las words de la lista como un objeto LiveData
     */
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /**
     * Método insert
     *
     * @param word recibe una Word para ser insertada
     */
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
