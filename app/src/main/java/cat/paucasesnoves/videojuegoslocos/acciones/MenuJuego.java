package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.R;

public class MenuJuego extends AppCompatActivity {
    private static int jug = 0;

    //Submenu para gestionar la introducción de datos referente a los juegos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juego);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            jug = extras.getInt("Id");//Contenedor del id de juego k estamos editando
        }
        MenuJuego();

    }


    public void MenuJuego() {
        //Declaro los distindos botones
        //Boton para introducir el nuevo Juego
        Button btnNewGame = (Button) findViewById(R.id.btnInsertJuego);
        //Boton para añadir un Genero a un Juego
        Button btnNewGen = (Button) findViewById(R.id.btnInsertJuGen);
        //Boton para añadir Plataforma a un Juego.
        Button btnNewPlat = (Button) findViewById(R.id.btnInsertJuPlat);

        //Llamar para añadir el nuevo Juego
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), InsertarJuego.class);
                Intent i = new Intent(v.getContext(), PruebaIncluirJuego.class);
                startActivity(i);
            }
        });
        //Pasar al class para gestionar las peticiones de obtener datos de un juego y asociarlo a un Genero.
        btnNewGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1;
                Intent i = new Intent(v.getContext(), IncluirJueGen.class);
                if(jug != 0) {
                    toast1 = Toast.makeText(getApplicationContext(), "El id del juego es " + jug, Toast.LENGTH_SHORT);
                    toast1.show();
                    i.putExtra("IdJuego" , jug);//Le pasamos el id del juego k estamos editando sino hemos incluido un juego pues estara en 0 ...


                }/*else{
                    toast1 = Toast.makeText(getApplicationContext(), "No hay juego" + jug, Toast.LENGTH_SHORT);
                }*/

                //Volvemos al Menu de Juego
                startActivity(i);
                /*
                Intent i = new Intent(v.getContext(), InsertarJuego.class);
                startActivity(i);*/
            }
        });
        //Clase para obtener datos del juego y asociar a una plataforma . . .
        btnNewPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1;
                Intent i = new Intent(v.getContext(), IncluirJuePlat.class);
                if(jug != 0) {
                    toast1 = Toast.makeText(getApplicationContext(), "El id del juego es " + jug, Toast.LENGTH_SHORT);
                    toast1.show();
                    i.putExtra("IdGame" , jug);//Le pasamos el id del juego k estamos editando sino hemos incluido un juego pues estara en 0 ...
                }
                startActivity(i);

            }
        });


    }



    }
