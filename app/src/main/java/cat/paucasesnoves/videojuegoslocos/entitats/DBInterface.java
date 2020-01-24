package cat.paucasesnoves.videojuegoslocos.entitats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBInterface {
    public static final String CLAU_ID = "_id";
    public static final String CLAU_NOM = "_nom";
    public static final String CLAU_EMAIL = "_email";
    public static final String TAG = "DBInterface";
    public static final String BD_NOM = "BDAlumnes";
    public static final String BD_TAULA = "contactes";
    public static final int VERSIO = 1;
    public static final String BD_CREATE = "";
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBInterface(Context con){
        this.context = con;
        ajuda = new AjudaDB(context);
    }

}
