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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class Juegos extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static int plat;
    DBInterface bd;
    //Cuando se crea a vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Iniciar el layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);
        //Debug.(getIntent());
        //
        plat = obtenerPlataformaActual();
       /* Toast toast1;
        toast1 = Toast.makeText(getApplicationContext(),"Id seleccionado."+plat, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
        toast1.show();*/
        //obtenerJuegosPlataforma(plat);

    }


    //Obtener todos los juegos de una plataforma en especifico...
    public void obtenerJuegosPlataforma(int plat){
        bd = new DBInterface(getApplicationContext());
        bd.abrir();
        Cursor c = bd.obtenerJuegosPlataformas(plat);
        c.moveToFirst();
        ArrayList<HashMap<String,String>> llista = new ArrayList<HashMap<String, String>>();
        while(!c.isAfterLast()){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("id",c.getString(0));
            map.put("nom",c.getString(1));
            map.put("email",c.getString(2));
            llista.add(map);
            c.moveToNext();
        }
        bd.cerrar();
        /*
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_item_juego, new String[]{"id","nom","email"},new int[]{R.id.id_item_lista,R.id.nombre_item_lista,R.id.email_item_lista});
        listView.setAdapter(adapter);*/
        /*
        menu = new SimpleAdapter(R.layout.buton_menu);
        listView.addFooterView(menu);*/
    }

    //Obtener la plataforma actual
    private int obtenerPlataformaActual(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
             plat = extras.getInt("Id");
        }else{
            Toast toast1;
            toast1 = Toast.makeText(getApplicationContext(),"No se ha pasado correctamente.", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
            toast1.show();

            plat = 0;
        }
        return plat;
    }

}
