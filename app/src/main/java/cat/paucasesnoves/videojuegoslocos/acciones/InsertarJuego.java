package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class InsertarJuego extends AppCompatActivity {
    ImageView imagenJuego;
    DBInterface bd;
    EditText nombreJuego,descripJuego,precioJuego;//Edit Text contenedor del nombre de plataforma . . .
    Toast toast1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_juego);
        imagenJuego = (ImageView) findViewById(R.id.imgJuego);
        nuevoJuego();
    }

    //String nombre, String descripcion, int dinero, byte[] imagen, String favorito


    private void nuevoJuego() {

        Button btnImgJuego = (Button) findViewById(R.id.btnBuscarJuego);
        Button btnAñadir = (Button) findViewById(R.id.btnConfirmarJuego);

        //Se ha dado click sobre el boton de buscar imagen
        btnImgJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobar si hay permiso . . .
                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) v.getContext(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                }
                recullDeGaleria();
            }
        });
        //Se ha dado click sobre el boton de confirmar añadir plataforma. . .
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el texto escrito
                nombreJuego = findViewById(R.id.nombreJuego);
                String nombre = nombreJuego.getText().toString();
                descripJuego = findViewById(R.id.descripcionJuego);
                String descripcion = descripJuego.getText().toString();
                precioJuego = findViewById(R.id.precioJuego);
                //Obtenemos el texto y lo pasamos a int
                int precio = Integer.parseInt(precioJuego.getText().toString());
                //Dejamos por defecto en no Favorito
                String fav = "false";
                //Compobar que texto obtengo
                /*toast1 = Toast.makeText(getApplicationContext(),"Click " + nombrePlat.getText(), Toast.LENGTH_SHORT);
                toast1.show();*/
                //preparamos las salida en byte
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //Realizamos la compresión
                ((BitmapDrawable)imagenJuego.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                //Lo guardamos en un array de byte[]
                byte[] foto = baos.toByteArray();
                // Comprobar si se inserta correctamente
                bd = new DBInterface(getApplicationContext());

                bd.abrir();
                //Realizar el insert
                Cursor resultado = bd.insertarJuego(nombre, descripcion, precio , foto,fav);
                //Si nos devuelve los datos
                if (resultado.getCount() != 0) {
                    toast1 = Toast.makeText(getApplicationContext(),"Se ha insertado correctamente : " + nombreJuego.getText().toString(), Toast.LENGTH_SHORT);
                }else{
                    toast1 = Toast.makeText(getApplicationContext(),"Error : " + nombreJuego.getText().toString(), Toast.LENGTH_SHORT);
                }
                bd.cerrar();
                //Mostramos el resultado
                toast1.show();
               // finishActivity(Integer.parseInt(resultado.getColumnName(1).toString()));
                //Creamos el intent

                Intent i = new Intent(v.getContext(), MenuJuego.class);
                //Volvemos al Menu de Juego
                int idJuego = Integer.parseInt(resultado.getString(0));
                i.putExtra("Id" , idJuego);
                startActivity(i);

            }
        });
    }


    private void recullDeGaleria() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, 100);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {//data.getData return the content URI for the selected Image
                Uri imageUri = data.getData();
                imagenJuego.setImageURI(imageUri);
                imagenJuego.setVisibility(View.VISIBLE);
                imagenJuego.setMinimumHeight(200);
                imagenJuego.setMinimumWidth(200);
                imagenJuego.setAdjustViewBounds(true);
            }
        }
    }
}
