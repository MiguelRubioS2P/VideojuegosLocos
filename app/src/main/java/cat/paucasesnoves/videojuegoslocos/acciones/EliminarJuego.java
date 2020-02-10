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

public class EliminarJuego extends AppCompatActivity {
    DBInterface bd;
    TextView datoJuego;
    EditText juego;
    Toast toast1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_juego);
        incluirPlatJuego();
    }

    private void incluirPlatJuego() {

        Button btnBuscarJuego = (Button) findViewById(R.id.btnCompDelJuego);
        Button btnBorrarJuego = (Button) findViewById(R.id.btnConfDelJuego);


        //Se ha dado click sobre el boton de buscar imagen
        btnBuscarJuego.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                juego = findViewById(R.id.delIdJuego);

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
                datoJuego = findViewById(R.id.textoDelNombreJuego);
                datoJuego.setText(resultado.getString(1));
                datoJuego.setVisibility(View.VISIBLE);
            }

        });
        btnBorrarJuego.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                juego = findViewById(R.id.delIdJuego);
                bd = new DBInterface(getApplicationContext());
                int id = Integer.parseInt(juego.getText().toString());
                bd.abrir();
                //Realizar el insert
                Boolean resultado = bd.eliminarJuego(id);
                //Si nos devuelve los datos
                if (resultado) {
                    toast1 = Toast.makeText(getApplicationContext(),"Juego Borrado " , Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"No encontrado  " , Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();

            }

        });

    }
    }

