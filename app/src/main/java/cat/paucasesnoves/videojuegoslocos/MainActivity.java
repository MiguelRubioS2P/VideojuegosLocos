package cat.paucasesnoves.videojuegoslocos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private int CODI_PETICIO = 1;//Segunda opcion diferir en el codigo de peticion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),"Se dio click sobre la PS4", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.LEFT,250,50);

                toast1.show();

                try {
                    toast1.show();

                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                Intent intent = new Intent();
                intent.putExtra("Id", 2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnNintendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Id", 3);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnPc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("Id", 4);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
        });




    }

    // Esto es para crear el menu apartar de un layout menu
    @Override public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_mainactivity,menu);

        return true;

    }
    public void plataforma(int plat){
        System.out.println("numero" + plat);
    }
}
