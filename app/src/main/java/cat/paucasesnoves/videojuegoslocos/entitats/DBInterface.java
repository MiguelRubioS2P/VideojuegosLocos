package cat.paucasesnoves.videojuegoslocos.entitats;

import android.content.Context;
import android.database.SQLException;
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
    public static final String BD_CREATETABLA1 = "create table " + BD_TAULA1 + "( " +
            CLAVE_ID1 + " integer not null primary key autoincrement, " + CLAVE_NOMBRE1 +
            " text, " + CLAVE_DESCRIPCION1 + " text, " + CLAVE_PRECIO1 + " real, " +
            CLAVE_IMAGEN1 + " text, " + CLAVE_FAVORITO1 + " text);";
    // ---------------------------------------------------------------------
    //segunda tabla --------------------------------------------------------
    public static final String BD_TAULA2 = "Generos";
    public static final String CLAVE_ID2 = "_id";
    public static final String CLAVE_NOMBRE2 = "_nombre";
    // ---------------------------------------------------------------------
    //query creación segunda tabla
    public static final String BD_CREATETABLA2 = "create table " + BD_TAULA2 +
            "( " + CLAVE_ID2 + " integer not null primary key autoincrement, " +
            CLAVE_NOMBRE2 + " text);";
    // ---------------------------------------------------------------------
    //tercera tabla --------------------------------------------------------
    public static final String BD_TAULA3 = "Plataformas";
    public static final String CLAVE_ID3 = "_id";
    public static final String CLAVE_NOMBRE3 = "_nombre";
    public static final String CLAVE_IMAGEN3 = "_imagen";
    // ---------------------------------------------------------------------
    //query creación tercera tabla -----------------------------------------
    public static final String BD_CREATETABLA3 = "create table " + BD_TAULA3 +
            "( " + CLAVE_ID3 + " integer not null primary key autoincrement, " +
            CLAVE_NOMBRE3 + " text, " + CLAVE_IMAGEN3 + " text);";
    // ---------------------------------------------------------------------
    //cuarta tabla ---------------------------------------------------------
    public static final String BD_TAULA4 = "JuegosPlataformas";
    public static final String CLAVE_ID4 = "_id";
    public static final String CLAVE_JUEGOID4 = "_juegoid";
    public static final String CLAVE_PLATAFORMAID4 = "_plataformaid";
    // ---------------------------------------------------------------------
    //query creación cuarta tabla ------------------------------------------
    public static final String BD_CREATETABLA4 = "create table " + BD_TAULA4 +
            "( " + CLAVE_ID4 + " integer not null primary key autoincrement, " +
            CLAVE_JUEGOID4 + " integer foreign key references " + BD_TAULA1 + "("+CLAVE_ID1+"), " +
            CLAVE_PLATAFORMAID4 + " integer foreign key references " + BD_TAULA3 + "("+CLAVE_ID3+"));";
    // ---------------------------------------------------------------------
    // quinta tabla --------------------------------------------------------
    public static final String BD_TAULA5 = "JuegosGeneros";
    public static final String CLAVE_ID5 = "_id";
    public static final String CLAVE_JUEGOID5 = "_juegoid";
    public static final String CLAVE_GENERO5 = "_generoid";
    // ---------------------------------------------------------------------
    //query creación quinta tabla ------------------------------------------
    public static final String BD_CREATETABLA5 = "create table " + BD_TAULA5 + "( " +
            CLAVE_ID5 + " integer not null primary key autoincrement, " + CLAVE_JUEGOID5 +
            " integer foreign key references " + BD_TAULA1+"("+CLAVE_ID1+"), " +
            CLAVE_GENERO5 + " integer foreign key references " + BD_TAULA2 + "("+CLAVE_ID2+"));";
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
    public DBInterface abrir() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }

    //Insertar un juego

    //Insertar una plataforma
    //PC,Switch,Xbox,Playstation

    //Insertar un genero


    //Insertar JuegosPlataformas

    //Insertar JuegosGeneros



}
