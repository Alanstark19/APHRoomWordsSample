package com.example.android.roomwordssample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


/**
 * Clase que servirá como base de datos
 */

@Database(entities = {Word.class}, version = 1,  exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();// Para obtener el objeto DAO

    private static WordRoomDatabase INSTANCE;
    /**
     * método que evitará crear varias intancias del mismo objeto, si ya hay una la reciclará
     *
     * @param context
     * @return
     */
    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Crea la base de datos
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * método para sobre escribir la base de datos
     * se limpiará la base de datos cada que la abrimos o creamos
     *
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();//si se necesita mantener los datos se comentará
        }
    };

    /**
     * Método para añadir registros a la base de datos
     * para ser mostrados al inicial la app
     *
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String [] words = {"Leon", "Hiena", "Cobra"};

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();//eliminamos los registros de la base de datos
                //for para insertar las palabras del arreglo en la base de datos
            for( int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);//insertando cada palabra
            }
            return null;
        }
    }
}
