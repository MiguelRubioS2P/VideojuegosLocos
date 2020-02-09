package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class IncluirJueGen extends AppCompatActivity {
private static int jug;
    DBInterface bd;

    EditText juego,genero;//Number
    TextView datoJuego,datoGenero;//Contenedores ocultos con la info de los juegos o genero
    Toast toast1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_jue_gen);
        Bundle extras = getIntent().getExtras();
        juego = findViewById(R.id.idJuego);
        //Si recibimos campos ponemos el juego k hemos insertado anteriormente . . .
        if(extras != null) {
            jug = extras.getInt("IdJuego");//Contenedor del id de juego k estamos editando
            String id = String.valueOf(jug);
            juego.setText(id);
        }
        incluirGeneroJuego();

    }
    private void incluirGeneroJuego() {

        Button btnBuscarJuego = (Button) findViewById(R.id.btnCompJuego);
        Button btnBuscarGenero = (Button) findViewById(R.id.btnCompGen);
        Button btnNuevoGenJue = (Button) findViewById(R.id.btnConfGen);


        //Se ha dado click sobre el boton de buscar imagen
        btnBuscarJuego.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                juego = findViewById(R.id.idJuego);

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
                datoJuego = findViewById(R.id.textoNombreJuego);
                datoJuego.setText(resultado.getString(1));
                datoJuego.setVisibility(View.VISIBLE);
            }

        });
        btnBuscarGenero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                genero = findViewById(R.id.idGenero);

                bd = new DBInterface(getApplicationContext());
                int id = Integer.parseInt(genero.getText().toString());
                bd.abrir();
                //Realizar el insert
                Cursor resultado = bd.obtenerGenero(id);
                //Si nos devuelve los datos
                if (resultado.getCount() != -1) {
                    toast1 = Toast.makeText(getApplicationContext(),"Genero encontrado : " + resultado.getString(1), Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"No encontrado : " , Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();
                datoGenero = findViewById(R.id.textoNombreGenero);
                datoGenero.setText(resultado.getString(1));
                datoGenero.setVisibility(View.VISIBLE);

            }

        });
        btnNuevoGenJue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                genero = findViewById(R.id.idGenero);
                juego = findViewById(R.id.idJuego);

                bd = new DBInterface(getApplicationContext());
                int gen = Integer.parseInt(genero.getText().toString());
                int jue = Integer.parseInt(juego.getText().toString());
                bd.abrir();
                //Realizar el insert
                Long resultado = bd.insertarJuegosGeneros(jue , gen);
                //Si nos devuelve los datos
                if (resultado != -1) {
                    toast1 = Toast.makeText(getApplicationContext(),"Se ha añadido el Genero al Juego ", Toast.LENGTH_SHORT);
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
