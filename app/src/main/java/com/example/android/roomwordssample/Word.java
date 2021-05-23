package com.example.android.roomwordssample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")// @Entity indica que la clase es una entidad,
// y se especifica el nombre de la tabla
/**
 * clase Word que sera la entidad 'Entity' en nuestro proyecto
 */
public class Word {

    @PrimaryKey //cada word será su misma PK
    @NonNull //la PK no puede ser nula
    @ColumnInfo(name = "word") // especifica el nombre de la columna en la tabla word_table
    //variables
    private String mWord;

    /**
     * Constructor de la clase Word
     *
     * @param word recibe una cadena llamada word
     *             El @NonNull es para que nunca obtenga un valor nulo.
     */
    public Word(@NonNull String word) {
        this.mWord = word;
    }

    /**
     * Getter de la variable word
     *
     * @return regresa la word
     */
    public String getWord() {
        return this.mWord;
    }
}