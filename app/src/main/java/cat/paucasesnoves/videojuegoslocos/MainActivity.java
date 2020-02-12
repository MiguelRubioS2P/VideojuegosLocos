package cat.paucasesnoves.videojuegoslocos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.SharedPreferences.Login;
import cat.paucasesnoves.videojuegoslocos.SharedPreferences.Usuario;
import cat.paucasesnoves.videojuegoslocos.acciones.EliminarJuego;
import cat.paucasesnoves.videojuegoslocos.acciones.InsertarGenero;
import cat.paucasesnoves.videojuegoslocos.acciones.InsertarPlataforma;
import cat.paucasesnoves.videojuegoslocos.acciones.MenuJuego;
import cat.paucasesnoves.videojuegoslocos.acciones.ModificarJuego;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;
import cat.paucasesnoves.videojuegoslocos.entitats.GestorFavoritos;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    private int CODI_PETICIO = 1;//Segunda opcion diferir en el codigo de peticion
    DBInterface db;
    private Menu globalMenuItem; //Para poder gestion
    private boolean login = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  comprovarLayout();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Comprobar si hay favoaritos
        //comprobarLayout();
        MenuInicial();

        //estado();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Parte de los iconos de la parte de arriba ....
    public void iniciarSesion(){
        //Cuando se da click hacer visible el cerrar sesion
  /*      MenuItem nav_dashboard = globalMenuItem.findItem(id);
        nav_dashboard.setVisible(false);
         nav_dashboard = globalMenuItem.findItem(R.id.cerrarSesion);
        nav_dashboard.setVisible(true);
*/


/*
        globalMenuItem.findItem(R.id.cerrarSesion).setVisible(true);

        globalMenuItem.findItem(R.id.iniciarUser).setVisible(false);*/

        login =true;
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        //Cmbiar a clase para iniciar user SharedPreferences
/*
        Intent i = new Intent(this, EliminarJuego.class);
        startActivity(i);
        */
    }

    // Esto es para crear el menu apartar de un layout menu
    @Override public boolean onCreateOptionsMenu(Menu menu){
        Usuario pepe = new Usuario();
        Boolean estado = pepe.isEstado();
        Boolean sesion = comprobarSesion();
        Bundle extras = getIntent().getExtras();
        Boolean login = false;
        if(extras != null) {
             login = extras.getBoolean("log");

        }
           // if(estado == false|| sesion == true || login == false) {
         if( sesion == false) {

              getMenuInflater().inflate(R.menu.menu_usuario, menu);
        }else{
            getMenuInflater().inflate(R.menu.user_login, menu);
            //Obtengo el nombre del Usuario actual . . .
             prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
             String usuario = prefs.getString("user",null);
            menu.findItem(R.id.usuarioLogin).setTitle(usuario);

        }

        return true;
    }
    /*
    public void estado(){
        Boolean sesion = comprobarSesion();
        if( sesion == false) {
            getMenuInflater().inflate(R.menu.menu_usuario, globalMenuItem);
        }else{
            getMenuInflater().inflate(R.menu.user_login, globalMenuItem);
        }
    }*/
    public boolean comprobarSesion(){
        prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
        Boolean estado = prefs.getBoolean("estado",false);
        /*
        Toast toast1 = Toast.makeText(getApplicationContext(),"Esto "+estado, Toast.LENGTH_SHORT);
        toast1.show();*/
        return estado;
    }
    //Gestionar las opciones del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            //Iniciar sesion Con un Usuario
            case R.id.iniciarUser:
                finish();
                iniciarSesion();
                return true;
                //Editar la información del Usuario
            case R.id.editarUser:
               // NuevoGenero();
                return true;
                //Cerrar la sesión del Usuario
            case R.id.cerrarSesion:
               Logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Cerrar sesion
    public void Logout(){
        prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("estado",false);
        editor.apply();
        finish();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

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
