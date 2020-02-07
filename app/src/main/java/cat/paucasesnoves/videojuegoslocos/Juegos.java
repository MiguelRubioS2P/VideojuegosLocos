package cat.paucasesnoves.videojuegoslocos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class Juegos extends AppCompatActivity {
    private static int plat;
    DBInterface bd;
    //Declaro el text view
    TextView plataformaActual;

    //Cuando se crea a vista
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
                // obtenerPlataformaActual();
        //Obtener la plataforma actual con Switch y obteniendo el ID por el Budle /Intent.
        saberPlataforma();


        obtenerJuegosPlataforma();

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
        Cursor c = bd.obtenerJuegosPlataformas(plat);
        //Obtenemos el primer valor
        c.moveToFirst();

        //Contenedor del listado de juegos
        ArrayList<HashMap<String,String>> llista = new ArrayList<HashMap<String, String>>();

        //HashMap que contendrá la info de los juegos
        HashMap<String,String> map = new HashMap<>();

        //ArrayList para obtener solo los id de los juegos
        ArrayList<Integer> idJuego = new ArrayList<>();

        //Recorrer los resultados
        while(!c.isAfterLast()){
            //Añadir nuemero al ArrayList
            idJuego.add(c.getInt(1));
            c.moveToNext();
        }

        for (int p : idJuego) {


        }




        /*
         ArrayList<HashMap<String,String>> llista = new ArrayList<HashMap<String, String>>();

        while(!c.isAfterLast()){
            HashMap<String,String> map = new HashMap<>();

            map.put("juegoid",c.getString(0));
            llista.add(map);
            c.moveToNext();
        }
        * */
        bd.cerrar();
        /*
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_item_juego, new String[]{"id","nom","email"},new int[]{R.id.id_item_lista,R.id.nombre_item_lista,R.id.email_item_lista});
        listView.setAdapter(adapter);*/
        /*
        menu = new SimpleAdapter(R.layout.buton_menu);
        listView.addFooterView(menu);*/
    }




}
