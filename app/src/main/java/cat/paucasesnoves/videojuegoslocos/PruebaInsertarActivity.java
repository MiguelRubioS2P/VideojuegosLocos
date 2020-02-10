package cat.paucasesnoves.videojuegoslocos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class PruebaInsertarActivity extends AppCompatActivity {

    DBInterface bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pruebainsertar);

        bd = new DBInterface(getApplicationContext());
        bd.abrir();
        /*//Nuevas pruebas
        byte[] prueba = new byte[10];
        Cursor juego = bd.insertarJuego("Prueba1","Prueba1",30,prueba,"False");
        if(juego.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No se ha añadido",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"El juego es: " + juego.getString(1).toString(),Toast.LENGTH_SHORT).show();
        }

        //id 1 genero
        bd.insertarGenero("Prueba1Genero");
        Cursor genero = bd.obtenerGenero(1);
        if(genero != null){
            Toast.makeText(getApplicationContext(),"El genero es: " + genero.getString(1),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se ha podido obtener el genero deseado",Toast.LENGTH_SHORT).show();
        }
        //id 1 plataforma
        bd.insertarPlataforma("Prueba1Plataforma",prueba);
        Cursor plataforma = bd.obtenerPlataforma(1);
        if(plataforma != null){
            Toast.makeText(getApplicationContext(),"La plataforma es: " + plataforma.getString(1),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se ha podido obtener la plataforma",Toast.LENGTH_SHORT).show();
        }

        bd.insertarJuegosGeneros(juego.getInt(0),genero.getInt(0));
        bd.insertarJuegosPlataformas(juego.getInt(0),plataforma.getInt(0));

        Cursor juegosGeneros = bd.obtenerJuegosGeneros(genero.getInt(0));
        juegosGeneros.moveToFirst();
        while(!juegosGeneros.isAfterLast()){
            juego = bd.obtenerJuegoId(juegosGeneros.getInt(0));
            Toast.makeText(getApplicationContext(),"El juego es: " + juego.getString(1),Toast.LENGTH_SHORT).show();
            juegosGeneros.moveToNext();
        }
        Cursor juegosPlataformas = bd.obtenerJuegosPlataformas(plataforma.getInt(0));
        juegosPlataformas.moveToFirst();
        while(!juegosPlataformas.isAfterLast()){
            juego = bd.obtenerJuegoId(juegosPlataformas.getInt(0));
            Toast.makeText(getApplicationContext(),"El juego es: " + juego.getString(1),Toast.LENGTH_SHORT).show();
            juegosPlataformas.moveToNext();
        }*/

        /*//Prueba borrar juego
        Cursor juego = bd.obtenerJuegoId(1);
        boolean eliminado = bd.eliminarJuego(juego.getInt(0));
        if(eliminado){
            Toast.makeText(getApplicationContext(),"Se ha eliminado perfectamente",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se ha eliminado",Toast.LENGTH_SHORT).show();
        }*/


        //Prueba de insertar Plataforma
        /*if(bd.insertarPlataforma("PC",null) != -1){
            Toast.makeText(getApplicationContext(),"Añadido correctamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se ha añadido",Toast.LENGTH_LONG).show();
        }*/
        //Prueba del metodo obtener favoritos
        /*bd.insertarJuego("Prueba1","PRueba1",20,"PRuebaimagen1","True");

        Cursor prueba = bd.obtenerTodosLosjuegosFavoritos();
        int pruebaCantidad = prueba.getCount();
        Toast.makeText(getApplicationContext(),pruebaCantidad + "",Toast.LENGTH_SHORT).show();*/

        //Prueba del metodo insertar relaciones

        /*bd.insertarJuego("Prueba2","Prueba2",10,"Prueba2","False");
        //toast para saber si se esta en la bd
        bd.insertarPlataforma("PruebaPlataforma1","ImagenPruebaPlataforma");
        //toast para saber si se esta en la bd
        bd.insertarGenero("PruebaGenero1");
        //toast para saber si se esta en la bd
        bd.insertarJuegosGeneros(1,1);
        //toast para saber si se esta en la bd
        bd.insertarJuegosPlataformas(1,1);
        //toast para saber si se esta en la bd*/

        //Obtener JuegosGeneros Funciona
        /*Cursor prueba = bd.obtenerJuegosGeneros(1);
        String prueba1 = prueba.getString(0);

        Toast.makeText(getApplicationContext(),prueba1,Toast.LENGTH_SHORT).show();*/




        bd.cerrar();
        finish();

    }

}
