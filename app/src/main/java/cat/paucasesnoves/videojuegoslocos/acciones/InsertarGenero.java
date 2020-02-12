package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class InsertarGenero extends AppCompatActivity {
    DBInterface bd;
    EditText nombreGen;//Edit Text contenedor del nombre de plataforma . . .
    Toast toast1 = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_genero);
        nuevaGenero();
    }


    private void nuevaGenero() {

        Button btnGen = (Button) findViewById(R.id.btnInsertGen);

        //Se ha dado click sobre el boton de añadir Genero
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el texto escrito
                nombreGen = findViewById(R.id.nombreGenero);
                //Iniciar la BBDD . . .
                bd = new DBInterface(getApplicationContext());

                bd.abrir();
                if (bd.insertarGenero(nombreGen.getText().toString()) != -1) {
                    toast1 = Toast.makeText(getApplicationContext(),"Se ha insertado correctamente : " + nombreGen.getText().toString(), Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"Error : " + nombreGen.getText().toString(), Toast.LENGTH_SHORT);
                }
                toast1.show();
                bd.cerrar();

            }
        });
    }



}
