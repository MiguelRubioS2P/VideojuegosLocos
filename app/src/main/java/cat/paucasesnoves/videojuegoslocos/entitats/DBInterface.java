package cat.paucasesnoves.videojuegoslocos.entitats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBInterface {
    //base de datos -------------------------------------------------------
    public static final String TAG = "DBInterface";
    public static final String BD_NOM = "BDJuegos";
    // --------------------------------------------------------------------
    //primera tabla -------------------------------------------------------
    public static final String BD_TAULA1 = "Juegos";
    public static final String CLAVE_ID1 = "_id";
    public static final String CLAVE_NOMBRE1 = "_nombre";
    public static final String CLAVE_DESCRIPCION1 = "_descripcion";
    public static final String CLAVE_PRECIO1 = "_precio";
    public static final String CLAVE_IMAGEN1 = "_imagen";
    public static final String CLAVE_FAVORITO1 = "_favorito";
    // --------------------------------------------------------------------
    // query creación primera tabla
    public static final String BD_CREATETABLA1 = "";
    // ---------------------------------------------------------------------
    //segunda tabla --------------------------------------------------------
    public static final String BD_TAULA2 = "Generos";
    public static final String CLAVE_ID2 = "_id";
    public static final String CLAVE_NOMBRE2 = "_nombre";
    // ---------------------------------------------------------------------
    //query creación segunda tabla
    public static final String BD_CREATETABLA2 = "";
    // ---------------------------------------------------------------------
    //tercera tabla --------------------------------------------------------
    public static final String BD_TAULA3 = "Plataformas";
    public static final String CLAVE_ID3 = "_id";
    public static final String CLAVE_NOMBRE3 = "_nombre";
    public static final String CLAVE_IMAGEN3 = "_imagen";
    // ---------------------------------------------------------------------
    //query creación tercera tabla -----------------------------------------
    public static final String BD_CREATETABLA3 = "";
    // ---------------------------------------------------------------------
    //cuarta tabla ---------------------------------------------------------

    // ---------------------------------------------------------------------
    //query creación cuarta tabla ------------------------------------------

    // ---------------------------------------------------------------------
    // quinta tabla --------------------------------------------------------

    // ---------------------------------------------------------------------
    //query creación quinta tabla ------------------------------------------

    // ---------------------------------------------------------------------

    public static final int VERSIO = 1;
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBInterface(Context con){
        this.context = con;
        ajuda = new AjudaDB(context);
    }

    //Abrir la base de datos


}
