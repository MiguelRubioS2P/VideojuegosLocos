package cat.paucasesnoves.videojuegoslocos.entitats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
            CLAVE_IMAGEN1 + " blob, " + CLAVE_FAVORITO1 + " text);";
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
            CLAVE_NOMBRE3 + " text, " + CLAVE_IMAGEN3 + " blob);";
    // ---------------------------------------------------------------------
    //cuarta tabla ---------------------------------------------------------
    public static final String BD_TAULA4 = "JuegosPlataformas";
    public static final String CLAVE_ID4 = "_id";
    public static final String CLAVE_JUEGOID4 = "_juegoid";
    public static final String CLAVE_PLATAFORMAID4 = "_plataformaid";
    // ---------------------------------------------------------------------
    //query creación cuarta tabla ------------------------------------------
    public static final String BD_CREATETABLA4 = "create table " + BD_TAULA4 + "( " +
            CLAVE_ID4 + " integer not null primary key autoincrement, " +
            CLAVE_JUEGOID4 + " integer, " +
            CLAVE_PLATAFORMAID4 + " integer, " +
            "foreign key (" + CLAVE_JUEGOID4 + ") references " + BD_TAULA1 + "(" + CLAVE_ID1 + "), " +
            "foreign key (" + CLAVE_PLATAFORMAID4 + ") references " + BD_TAULA3 + "(" + CLAVE_ID3 + "));";
    // ---------------------------------------------------------------------
    // quinta tabla --------------------------------------------------------
    public static final String BD_TAULA5 = "JuegosGeneros";
    public static final String CLAVE_ID5 = "_id";
    public static final String CLAVE_JUEGOID5 = "_juegoid";
    public static final String CLAVE_GENERO5 = "_generoid";
    // ---------------------------------------------------------------------

    //query creación quinta tabla ------------------------------------------
    public static final String BD_CREATETABLA5 = "create table " + BD_TAULA5 + "( " +
            CLAVE_ID5 + " integer not null primary key autoincrement, " +
            CLAVE_JUEGOID5 + " integer, " +
            CLAVE_GENERO5 + " integer, " +
            "foreign key (" + CLAVE_JUEGOID5 + ") references " + BD_TAULA1 + "(" + CLAVE_ID1 + "), " +
            "foreign key (" + CLAVE_GENERO5 + ") references " + BD_TAULA2 + "(" + CLAVE_ID2 + "));";
    // ---------------------------------------------------------------------


    public static final int VERSIO = 1;
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBInterface(Context con) throws SQLException{
        this.context = con;
        ajuda = new AjudaDB(context);
    }

    //Abrir la base de datos
    public DBInterface abrir() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }

    //Cerrar la base de datos
    public void cerrar() throws SQLException{
        ajuda.close();
    }

    //Insertar un juego tabla1

    /**
     * Insertar un juego
     *
     * @param nombre Nombre para el juego
     * @param descripcion Descripcion para el juego
     * @param dinero Dinero para el juego
     * @param imagen Imagen para el juego
     * @param favorito Favorito para el juego
     * @return Cursor
     * @throws SQLException
     */
    public long insertarJuego(String nombre, String descripcion, int dinero, byte[] imagen, String favorito) throws SQLException{
        ContentValues initualValues = new ContentValues();
        initualValues.put(CLAVE_NOMBRE1,nombre);
        initualValues.put(CLAVE_DESCRIPCION1,descripcion);
        initualValues.put(CLAVE_PRECIO1,dinero);
        initualValues.put(CLAVE_IMAGEN1,imagen);
        initualValues.put(CLAVE_FAVORITO1,favorito);
        return bd.insert(BD_TAULA1,null,initualValues);
    }

    //Borrar un juego
    //También hay que borrar las relaciones de las tablas juegosgeneros y juegosplataformas
    //Recuperar en la tabla de generos y plataformas donde esta el juego a eliminar


    //Modificar un juego
    //Controlar los elementos individualmente

    //Devolver un juego tabla1

    /**
     * Obtener un juego
     *
     * @param IDFila id del juego que deseamos buscar
     * @return Cursor
     * @throws SQLException
     */
    public Cursor obtenerJuego(long IDFila) throws SQLException{
        Cursor mCursor = bd.query(true,BD_TAULA1, new String[]{CLAVE_ID1,CLAVE_NOMBRE1,CLAVE_DESCRIPCION1,CLAVE_PRECIO1,CLAVE_IMAGEN1,CLAVE_FAVORITO1},CLAVE_ID1 + " = " + IDFila,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Devolver todos los juegos favoritos

    /**
     * Devolvemos todos los juegos favoritos
     *
     * @return Cursor con todos los juegos favoritos
     * @throws SQLException
     */
    public Cursor obtenerTodosLosjuegosFavoritos() throws SQLException{
        return bd.query(BD_TAULA1,new String[]{CLAVE_ID1,CLAVE_NOMBRE1,CLAVE_DESCRIPCION1,CLAVE_PRECIO1,CLAVE_IMAGEN1,CLAVE_FAVORITO1},CLAVE_FAVORITO1 + " = 'True'",null,null,null,null,null);
    }


    //Insertar una plataforma tabla3
    //PC,Switch,Xbox,Playstation

    /**
     * Insertamos una plataforma
     *
     * @param nombre Nombre, valor para la plataforma
     * @param imagen Imagen, valor para la plataforma
     * @return Long
     * @throws SQLException
     */
    public long insertarPlataforma(String nombre, byte[] imagen) throws SQLException{
        ContentValues initualValues = new ContentValues();
        initualValues.put(CLAVE_NOMBRE3,nombre);
        initualValues.put(CLAVE_IMAGEN3,imagen);
        return bd.insert(BD_TAULA3,null,initualValues);
    }

    //Devolver una plataforma tabla3

    /**
     * Devolver una plataforma
     *
     * @param IDFila id de la plataforma que queremos recuperar
     * @return Cursor con un unico valor
     * @throws SQLException
     */
    public Cursor obtenerPlataforma(long IDFila) throws SQLException{
        Cursor mCursor = bd.query(true,BD_TAULA3,new String[]{CLAVE_ID3,CLAVE_NOMBRE3},CLAVE_ID3 + " = " + IDFila,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    //Devolver todos las plataformas

    /**
     * Devolver todas las plataformas
     *
     * @return Cursor con todas las plataformas
     * @throws SQLException
     */
    public Cursor obtenerTodosLasPlataformas() throws SQLException{
        return bd.query(BD_TAULA3,new String[]{CLAVE_ID3,CLAVE_NOMBRE3},null,null,null,null,null,null);
    }

    //Insertar un genero tabla2

    /**
     * Insertamos un genero
     *
     * @param nombre Valor nombre para el genero
     * @return long
     */
    public long insertarGenero(String nombre){
        ContentValues initualValues = new ContentValues();
        initualValues.put(CLAVE_NOMBRE2,nombre);
        return bd.insert(BD_TAULA2,null,initualValues);
    }

    //Devolver un genero tabla2

    /**
     * Devolvemos un genero
     *
     * @param IDFila id del genero
     * @return Cursor con un resultado
     * @throws SQLException
     */
    public Cursor obtenerGenero(long IDFila) throws SQLException{
        Cursor mCursor = bd.query(true,BD_TAULA2,new String[]{CLAVE_ID2,CLAVE_NOMBRE2},CLAVE_ID2 + " = " + IDFila,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Devolver todos los generos

    /**
     * Todas las tuplas de los generos
     *
     * @return Cursor
     */
    public Cursor obtenerTodosLosGeneros(){
        return bd.query(BD_TAULA2,new String[]{CLAVE_ID2,CLAVE_NOMBRE2},null,null,null,null,null,null);
    }

    //Insertar JuegosPlataformas tabla4

    /**
     * Insertar una tupla
     *
     * @param juego id del juego
     * @param plataforma id de la plataforma
     * @return Long
     * @throws SQLException
     */
    public long insertarJuegosPlataformas(int juego,int plataforma) throws SQLException{
        ContentValues initualValues = new ContentValues();
        initualValues.put(CLAVE_JUEGOID4,juego);
        initualValues.put(CLAVE_PLATAFORMAID4,plataforma);
        return bd.insert(BD_TAULA4,null,initualValues);
    }

    //Devolver un JuegosPlataformas tabla4

    /**
     * Devolvemos todas las tuplas de juegos con una plataforma
     *
     * @param IDPlataforma id de la plataforma que queremos
     * @return Cursor
     * @throws SQLException
     */
    public Cursor obtenerJuegosPlataformas(int IDPlataforma) throws SQLException{
        Cursor mCursor = bd.query(true,BD_TAULA4,new String[]{CLAVE_ID4,CLAVE_JUEGOID4,CLAVE_PLATAFORMAID4},CLAVE_PLATAFORMAID4 + " = " + IDPlataforma,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Devolver todos los JuegosPlataformas

    /**
     * Recuperamos todas las plataformas a las cual esta un juego
     *
     * @param idJuego id del juego
     * @return Todas las plataformas de un juego
     */
    public Cursor obtenerTodosLosJuegosPlataformas(int idJuego) throws SQLException{
        return bd.query(BD_TAULA4,new String[]{CLAVE_ID4,CLAVE_JUEGOID4,CLAVE_PLATAFORMAID4},CLAVE_JUEGOID4 + " = " + idJuego,null,null,null,null,null);
    }

    //Borrar JuegosPlataformas

    /**
     * Elimina una tupla
     *
     * @param IDFila El id de la tupla que queremos eliminar
     * @return boolean
     */
    public boolean borrarJuegosPlataformas(long IDFila) throws SQLException{
        return bd.delete(BD_TAULA4,CLAVE_ID4 + " = " + IDFila,null) > 0;
    }

    //Insertar JuegosGeneros tabla5

    /**
     *
     * @param juego id del juego
     * @param genero id del genero
     * @return Long
     */
    public long insertarJuegosGeneros(int juego,int genero) throws SQLException{
        ContentValues initualValues = new ContentValues();
        initualValues.put(CLAVE_JUEGOID5,juego);
        initualValues.put(CLAVE_GENERO5,genero);
        return bd.insert(BD_TAULA5,null,initualValues);
    }

    //Devolver un JuegosGeneros tabla5

    /**
     * Devolver las tuplas de cierto genero
     *
     * @param IDGenero El genero que queremos filtrar
     * @return Cursor con todas las tuplas en la que este el genero especifico
     */
    public Cursor obtenerJuegosGeneros(int IDGenero) throws SQLException{
        Cursor mCursor = bd.query(true,BD_TAULA5,new String[]{CLAVE_ID5,CLAVE_JUEGOID5,CLAVE_GENERO5},CLAVE_GENERO5 + " = " + IDGenero,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Devolver todos los JuegosGeneros

    /**
     * Recuperamos todos los generos a los cual pertenece un juego.
     *
     * @param idJuego id del juego
     * @return Un cursor con todos los generos que tiene un juego
     */
    public Cursor obtenerTodosLosJuegosGeneros(int idJuego) throws SQLException{
        return bd.query(BD_TAULA5,new String[]{CLAVE_ID5,CLAVE_JUEGOID5,CLAVE_GENERO5},CLAVE_JUEGOID5 + " = " + idJuego,null,null,null,null,null);
    }

    //Borrar JuegosGeneros

    /**
     * Borrar una fila
     *
     * @param IDFila id de la tupla correspondiente
     * @return boolean
     */
    public boolean borrarJuegosGeneros(long IDFila) throws SQLException {
        return bd.delete(BD_TAULA5,CLAVE_ID5 + " = " + IDFila,null) > 0;
    }


}