package cat.paucasesnoves.videojuegoslocos.entitats;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_CREATETABLA1;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_CREATETABLA2;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_CREATETABLA3;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_CREATETABLA4;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_CREATETABLA5;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_NOM;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_TAULA1;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_TAULA2;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_TAULA3;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_TAULA4;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.BD_TAULA5;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.TAG;
import static cat.paucasesnoves.videojuegoslocos.entitats.DBInterface.VERSIO;

public class AjudaDB extends SQLiteOpenHelper {

    //pasamos el nombre de la base de datos
    AjudaDB(Context con){
        super(con, BD_NOM,null,VERSIO);
    }


    //creamos las tablas de la base de datos
    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL(BD_CREATETABLA1);
            db.execSQL(BD_CREATETABLA2);
            db.execSQL(BD_CREATETABLA3);
            db.execSQL(BD_CREATETABLA4);
            db.execSQL(BD_CREATETABLA5);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //actualiza la versión de la base de datos
    //elimina las tablas.
    @Override
    public void onUpgrade(SQLiteDatabase db, int VersioAntiga, int VersioNova){
        Log.w(TAG, "Actualitzant Base de dades de la versió" + VersioAntiga + " a " + VersioNova + ". Destruirà totes les dades");
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA1);
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA2);
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA3);
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA4);
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA5);
        onCreate(db);
    }
}
