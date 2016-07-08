package com.example.leo.corpus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CasetasDbAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CALLE = "calle";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_FAVORITO = "favorito";



    private static final String TAG = "CasetasDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "CasetasDB";
    private static final String SQLITE_TABLE = "Caseta";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME + "," +
                    KEY_CALLE+ "," +
                    KEY_DESCRIPCION+ "," +
                    KEY_FAVORITO +");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public long crearCaseta(SQLiteDatabase db,String name, String calle, String descripcion,String favorito) {

            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_NAME, name);
            initialValues.put(KEY_CALLE, calle);
            initialValues.put(KEY_DESCRIPCION, descripcion);
            initialValues.put(KEY_FAVORITO, favorito);


            return db.insert(SQLITE_TABLE, null, initialValues);
        }
        public void insertarAlgunasCasetas(SQLiteDatabase db) {

            crearCaseta(db, "AIRES DE FIESTA", "C/ LA CAÑA, 31-33", "Esta es la caseta AIRES DE FIESTA. Fue fundada en 1974","si");
            crearCaseta(db, "ALJAMA", "C/ EL VITO, 32-34","Esta es la caseta ALJAMA. Fue fundada en 1974","no");
            crearCaseta(db, "AYUNTAMIENTO", "C/ LA ZAMBRA","Esta es la caseta del AYUNTAMIENTO. Fue fundada en 1974","no");
            crearCaseta(db, "CAJA RURAL", "C/ LA ZAMBRA","Esta es la caseta CAJA RURAL. Fue fundada en 1974","no");
            crearCaseta(db, "CASA DE JAÉN", "C/ LA ZAMBRA","Esta es la caseta CASA DE JAÉN. Fue fundada en 1974","no");
            crearCaseta(db, "CASA DE MOTRIL", "C/ LA ZAMBRA","Esta es la caseta CASA DE MOTRIL. Fue fundada en 1974","no");
            crearCaseta(db, "COLEGIO ARBITROS","C/ LA ZAMBRA","Esta es la caseta del colegio de arbitros. Fue fundada en 1974","no");
            crearCaseta(db, "PEÑA LOS 17", "C/ LA CAÑA, 31-33", "Esta es la caseta PEÑA LOS 7. Fue fundada en 1974","si");
            crearCaseta(db, "LA REHUERTA", "C/ LA CAÑA, 31-33", "Esta es la caseta LA REHUERTA. Fue fundada en 2013","no");
            crearCaseta(db, "LA CASTAÑUELA", "C/ LA CAÑA, 31-33", "Esta es la caseta LA CASTAÑUELA. Fue fundada en 1994","no");
            crearCaseta(db, "LA CACHUCHA", "C/ LA CAÑA, 31-33", "Esta es la caseta LA CACHUCHA. Fue fundada en 1983","no");
            crearCaseta(db, "LA ALBOREA", "C/ LA CAÑA, 31-33", "Esta es la caseta LA ALBOREA. Fue fundada en 1983","no");

        }



        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
            insertarAlgunasCasetas(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public CasetasDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public CasetasDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }



    public boolean eliminarCasetas() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }


    public long anadirFavorito(String id) {

        ContentValues cv = new ContentValues();
        cv.put("favorito", "si");

        return mDb.update("Caseta", cv, "_id=" + id, null);
    }
    public long quitarFavorito(String id) {

        ContentValues cv = new ContentValues();
        cv.put("favorito", "no");

        return mDb.update("Caseta", cv, "_id=" + id, null);
    }

    public Cursor recorrerCasetasPorNombre(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_CALLE},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_CALLE},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor recorrerCasetas() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_CALLE},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor recorrerFavoritos() {
        String[] campos = new String[] {KEY_ROWID, KEY_NAME, KEY_CALLE};
        String[] args = new String[] {"si"};
        Cursor mCursor = mDb.query(SQLITE_TABLE, campos , "favorito=?", args, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



    public Cursor getTodo(String id) {

        String[] campos = new String[]{KEY_NAME, KEY_CALLE, KEY_DESCRIPCION,KEY_FAVORITO};
        String[] args = new String[]{id};

        Cursor mCursor = null;
        mCursor = mDb.query(SQLITE_TABLE, campos,
                "_id=?", args, null, null, null);

        return mCursor;


    }

}
