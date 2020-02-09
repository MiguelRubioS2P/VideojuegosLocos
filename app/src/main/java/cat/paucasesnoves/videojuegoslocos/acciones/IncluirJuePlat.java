package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class IncluirJuePlat extends AppCompatActivity {
    DBInterface bd;

    EditText juego,genero;//Number
    TextView datoJuego,datoGenero;//Contenedores ocultos con la info de los juegos o genero
    Toast toast1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_jue_plat);
        Bundle extras = getIntent().getExtras();
        juego = findViewById(R.id.idGame);
        //Si recibimos campos ponemos el juego k hemos insertado anteriormente . . .
        if(extras != null) {
           int jug = extras.getInt("IdGame");//Contenedor del id de juego k estamos editando
            String id = String.valueOf(jug);
            juego.setText(id);
        }
        incluirPlatJuego();
    }


    private void incluirPlatJuego() {

        Button btnBuscarJuego = (Button) findViewById(R.id.btnCompJuego2);
        Button btnBuscarPlataforma = (Button) findViewById(R.id.btnCompPlat);
        Button btnNuevoPlatJue = (Button) findViewById(R.id.btnConfPlat);


        //Se ha dado click sobre el boton de buscar imagen
        btnBuscarJuego.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                juego = findViewById(R.id.idGame);

                bd = new DBInterface(getApplicationContext());
                int id = Integer.parseInt(juego.getText().toString());
                bd.abrir();
                //Realizar el insert
                Cursor resultado = bd.obtenerJuegoId(id);
                //Si nos devuelve los datos
                if (resultado.getCount() != 0) {
                    toast1 = Toast.makeText(getApplicationContext(),"Juego encontrado : " + resultado.getString(1), Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"No encontrado : " , Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();
                datoJuego = findViewById(R.id.textoNameJuego);
                datoJuego.setText(resultado.getString(1));
                datoJuego.setVisibility(View.VISIBLE);
            }

        });
        btnBuscarPlataforma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                genero = findViewById(R.id.idPlata);

                bd = new DBInterface(getApplicationContext());
                int id = Integer.parseInt(genero.getText().toString());
                bd.abrir();
                //Realizar el insert
                Cursor resultado = bd.obtenerPlataforma(id);
                //Si nos devuelve los datos
                if (resultado.getCount() != -1) {
                    toast1 = Toast.makeText(getApplicationContext(),"Plataforma : " + resultado.getString(1), Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"No encontrado : " , Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();
                datoGenero = findViewById(R.id.textoNamePlataforma);
                datoGenero.setText(resultado.getString(1));
                datoGenero.setVisibility(View.VISIBLE);

            }

        });
        btnNuevoPlatJue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                genero = findViewById(R.id.idPlata);
                juego = findViewById(R.id.idGame);

                bd = new DBInterface(getApplicationContext());
                int plat = Integer.parseInt(genero.getText().toString());
                int jue = Integer.parseInt(juego.getText().toString());
                bd.abrir();
                //Realizar el insert
                Long resultado = bd.insertarJuegosPlataformas(jue , plat);
                //Si nos devuelve los datos
                if (resultado != -1) {
                    toast1 = Toast.makeText(getApplicationContext(),"Se ha añadido la Plataforma al Juego ", Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"No encontrado : " , Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();


            }

        });
    }
}
