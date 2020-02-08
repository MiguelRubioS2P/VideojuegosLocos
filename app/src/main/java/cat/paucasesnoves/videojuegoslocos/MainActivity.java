package cat.paucasesnoves.videojuegoslocos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.acciones.InsertarGenero;
import cat.paucasesnoves.videojuegoslocos.acciones.InsertarPlataforma;
import cat.paucasesnoves.videojuegoslocos.acciones.MenuJuego;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;
import cat.paucasesnoves.videojuegoslocos.entitats.GestorFavoritos;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private int CODI_PETICIO = 1;//Segunda opcion diferir en el codigo de peticion
    DBInterface db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  comprovarLayout();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Comprobar si hay favoritos
        //comprobarLayout();
        MenuInicial();


    }
    public void comprobarLayout(){
        //Realizar la bd
        db = new DBInterface(getApplicationContext());
        //Abrir
        db.abrir();
        //Obtener todos los favoritos
        Cursor pepe = db.obtenerTodosLosjuegosFavoritos();
        //Cerrar la conexión
        db.cerrar();

        //Pasar a int la cantidad de resultados . . .
        int resultados = pepe.getCount();
        //Si tenemos algún favorito
        if(pepe.getCount() != 0) {
            cargarFavoritos();
        }else{
            //Si no hay favoritos cargar el main
            //Y los botones
            setContentView(R.layout.activity_main);
            MenuInicial();        }

    }

    // Esto es para crear el menu apartar de un layout menu
    @Override public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_mainactivity,menu);

        return true;

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
        Intent i = new Intent(this,Juegos.class);
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
            case R.id.modificarJuego:
                modificarJuego();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void plataforma(int plat){
        System.out.println("numero" + plat);
    }
    public void cargarFavoritos() {
        //Pasamos a la clase k gestiona los favoritos . . .
        Intent i = new Intent(this, GestorFavoritos.class);
        startActivity(i);
    }

        public void MenuInicial(){
        //Declaro los distindos botones
        Button btnPS4= (Button) findViewById(R.id.Ps4);
        Button btnXbox= (Button) findViewById(R.id.Xbox);
        Button btnNintendo= (Button) findViewById(R.id.Nintendo);
        Button btnPc= (Button) findViewById(R.id.PC);


        //Declaramos un listener por boton . . .
        //Varia el id para saber a que plataforma hace referencia
        btnPS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Obtener info
             /*   Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),"Se dio click sobre la PS4", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);

                    toast1.show();
*/
                //Pasamos a la clase k gestiona los favoritos . . .

                Intent i = new Intent(v.getContext(), Juegos.class);
                i.putExtra("Id", 1);
                startActivityForResult(i,100);


                /*

                //Creamos intent  para redirigir a la siguiente clase
                Intent intent = new Intent(v.getContext(), DBInterface.class);
                intent.putExtra("Id", 1);
                startActivityForResult(intent,CODI_PETICIO);
                finish();*/
            }
        });
        btnXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),"Se dio click sobre Xbox", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
                */
                Intent i = new Intent(v.getContext(), Juegos.class);
                i.putExtra("Id", 2);
                startActivity(i);

               /* setResult(RESULT_OK, intent);
                finish();*/
            }
        });
        btnNintendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),"Se dio click sobre Nintendo", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
                */
                Intent i = new Intent(v.getContext(), Juegos.class);
                i.putExtra("Id", 4);
                startActivity(i);
            }
        });
        btnPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),"Se dio click sobre PC", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);
                */
                Intent i = new Intent(v.getContext(), Juegos.class);
                i.putExtra("Id", 3);
                startActivity(i);
            }
        });

    }
}
