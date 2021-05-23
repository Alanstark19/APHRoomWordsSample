package com.example.android.roomwordssample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Clase WordDao que sera nuestra clase para comunicar nuestra app con la base de datos
 */
@Dao
public interface WordDao {
    /**
     * //método getAllWords
     *
     * @return retorna una lista de Words
     */
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
    /**
     * //método para insertar una Word
     *
     * @param word recibe una palabra
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);
    /**
     * método para eliminar todas las Words
     */
    @Query("DELETE FROM word_table")
    void deleteAll();
}
