package cat.paucasesnoves.videojuegoslocos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import cat.paucasesnoves.videojuegoslocos.Fragment.DetalleActivity;
import cat.paucasesnoves.videojuegoslocos.acciones.EliminarJuego;
import cat.paucasesnoves.videojuegoslocos.acciones.InsertarGenero;
import cat.paucasesnoves.videojuegoslocos.acciones.InsertarPlataforma;
import cat.paucasesnoves.videojuegoslocos.acciones.MenuJuego;
import cat.paucasesnoves.videojuegoslocos.acciones.ModificarJuego;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;
import cat.paucasesnoves.videojuegoslocos.entitats.Juego;

public class Juegos extends AppCompatActivity {
    private static int plat;
    DBInterface bd;
    //Declaro el text view
    TextView plataformaActual;
    //Declaro la lista de los juegos
    ListView juegos,listadoJuegos;
    //Cuando se crea a vista
    SimpleAdapter adapter;
    SearchView searchView;
    private Object SearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Iniciar el layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);
        //Debug.(getIntent());
        //
        plataformaActual = (TextView) findViewById(R.id.LogoTexto);

        Bundle extras = getIntent().getExtras();
        plat = extras.getInt("Id");

        //Obtener la plataforma actual con la BBDD
                obtenerPlataformaActual();
        //Obtener la plataforma actual con Switch y obteniendo el ID por el Budle /Intent.
        //saberPlataforma();


        obtenerJuegosPlataforma();

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Parte de los iconos de la parte de arriba ....



    private void buscar(String juego){
        bd.abrir();
        Cursor SearchJuego = bd.obtenerJuego(juego);
        SearchJuego.moveToFirst();
        if(SearchJuego.getCount() != 0){
            Toast.makeText(getApplicationContext(),"Juego encontrado " + SearchJuego.getString(3),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No encontrado " + SearchJuego.getString(3),Toast.LENGTH_SHORT).show();
        }

    }

    public void NuevaPlataforma(){
        Intent i = new Intent(this, InsertarPlataforma.class);
        startActivity(i);
    }
    public void NuevoGenero(){
        Intent i = new Intent(this, InsertarGenero.class);
        startActivity(i);
    }
    public void insertarJuego(){
        Intent i = new Intent(this, MenuJuego.class);
        startActivity(i);
    }
    public void modificarJuego(){
        Intent i = new Intent(this, ModificarJuego.class);
        startActivity(i);
    }
    public void eliminarJuego(){
        Intent i = new Intent(this, EliminarJuego.class);
        startActivity(i);
    }


    //Gestionar las opciones del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.insertarPlataforma:
                NuevaPlataforma();
                return true;
            case R.id.insertarGenero:
                NuevoGenero();
                return true;
            case R.id.insertarJuego:
                insertarJuego();
                return true;
//            case R.id.modificarJuego:
//                modificarJuego();
//                return true;
            case R.id.eliminarJuego:
                eliminarJuego();
                return true;
            case R.id.action_search:
              //  buscar();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



private void saberPlataforma(){
    switch (plat) {
        case 4:
            plataformaActual.setText("Nintendo");
            break;
        case 1:
            plataformaActual.setText("Playstation");
            break;
        case 2:
            plataformaActual.setText("Xbox");
            break;
        case 3:
            plataformaActual.setText("Pc");
            break;
    }
}
    //Obtener la plataforma actual con la BBDD
    private void  obtenerPlataformaActual(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            //Se ha recibido el id de plataforma
            plat = extras.getInt("Id");

            bd = new DBInterface(getApplicationContext());
            bd.abrir();
            Cursor c = bd.obtenerPlataforma(plat);//Obtener una plataforma en especifico.
            //Ya en el metodo hace             mCursor.moveToFirst();
            //c.moveToNext();
            plataformaActual.setText(c.getString(1));
            bd.cerrar();

        }else{
            Toast toast1;
            toast1 = Toast.makeText(getApplicationContext(),"No se ha pasado correctamente.", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
            toast1.show();

            plat = 0;
        }
    }
    //Obtener todos los juegos de una plataforma en especifico...
    public void obtenerJuegosPlataforma(){
        //CLAVE_JUEGOID4
        bd = new DBInterface(getApplicationContext());
        bd.abrir();
        //Solicitamos todos los Juegos referente a una plataforma
        Cursor c = bd.obtenerJuegosPlataformas(plat); //Ya el metodo da la primera opcion
        //Obtenemos el primer valor
       // c.moveToFirst();

        //Contenedor del listado de juegos
        ArrayList<HashMap<String,String>> llista = new ArrayList<HashMap<String, String>>();

        //ArrayList para obtener solo los id de los juegos
        ArrayList<Integer> idJuego = new ArrayList<>();

        //Recorrer los resultados
        while(!c.isAfterLast()){
            //Añadir nuemero al ArrayList
            idJuego.add(c.getInt(1));
            c.moveToNext();
        }

        //Recorremos el array del listado anterior

        for (int p : idJuego) {
            //Obtenemos los resultados que nos de
            Cursor a = bd.obtenerJuegoId(p);
            //Pasamos al primer resultado
            //HashMap que contendrá la info de los juegos
            HashMap<String,String> map = new HashMap<>();
            //Ponemos la información según la necesitamos
            map.put("id",a.getString(0));
            map.put("nombre",a.getString(1));
            map.put("descripcion",a.getString(2));
            map.put("precio",String.valueOf(a.getInt(3)));
            //Pasamos el byte[] a String
            String imagen = new String(a.getBlob(4));
            map.put("imagen",imagen);
            map.put("favorito",a.getString(5));
            // Convertir el String al byte[]
            //byte[] by_new = imagen.getBytes();
            //Añadimos al HashMap general
            llista.add(map);
            a.moveToNext();

        }

        /*
        int i = 0;
        while(i != idJuego.size()){
            Cursor a = bd.obtenerJuegoId(plat);
            HashMap<String,String> map = new HashMap<>();
            map.put("nombre",a.getString(1));
            a.moveToNext();
            llista.add(map);
            i++;
        }
*/
        bd.cerrar();
        /*
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_item_juego, new String[]{"id","nombre","precio"},new int[]{R.id.idJuegoLista,R.id.nombreJuego,R.id.precioJuego});
        */
        adapter =new SimpleAdapter(getApplicationContext(),llista , R.layout.activity_item_juego,new String[]{"favorito","imagen"},new int[]{R.id.nombreJuego,R.id.imgJuego});
        listadoJuegos = findViewById(R.id.listadoJuegos);
        listadoJuegos.setAdapter(adapter);


        /*
        ArrayList<HashMap<String,String>> llista = new ArrayList<HashMap<String, String>>();
        while(!c.isAfterLast()){
            HashMap<String,String> map = new HashMap<>();
            map.put("juegoid",c.getString(0));
            llista.add(map);
            c.moveToNext();
        }
        * */
        /*
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_item_juego, new String[]{"id","nom","email"},new int[]{R.id.id_item_lista,R.id.nombre_item_lista,R.id.email_item_lista});
        listView.setAdapter(adapter);*/
        /*
        menu = new SimpleAdapter(R.layout.buton_menu);
        listView.addFooterView(menu);*/
    }




}
