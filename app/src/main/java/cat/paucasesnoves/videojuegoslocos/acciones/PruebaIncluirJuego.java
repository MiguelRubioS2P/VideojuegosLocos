package cat.paucasesnoves.videojuegoslocos.acciones;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import cat.paucasesnoves.videojuegoslocos.MainActivity;
import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class PruebaIncluirJuego extends AppCompatActivity {

    DBInterface db;
    CheckBox checkBox;
    ArrayList<String> generosList;
    ArrayList<String> plataformasList;
    ArrayList<Integer> generosListNumeros;
    ArrayList<Integer> plataformasListNumeros;

    EditText nombreJuego,descripcionJuego,precioJuego;
    ImageView imagenJuego;
    Button btnImagen, btnInsertar,btnDialogoGeneros,btnDialogoPlataformas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_insertar_juego);

        //db clase
        db = new DBInterface(getApplicationContext());
        //abrimos para la conexión
        db.abrir();

        nombreJuego = findViewById(R.id.etPruebaNombreJuego);
        descripcionJuego = findViewById(R.id.etPruebaDescripcionJuego);
        precioJuego = findViewById(R.id.etPruebaPrecioJuego);

        btnImagen = findViewById(R.id.btnPruebaImagenJuego);
        btnInsertar = findViewById(R.id.btnPruebaInsertarJuego);
        btnDialogoGeneros = findViewById(R.id.btnPruebaDialogoGeneros);
        btnDialogoPlataformas = findViewById(R.id.btnPruebaDialogoPlataformas);

        imagenJuego = findViewById(R.id.ivPruebaImagenJuego);

        generosList = new ArrayList<>();
        plataformasList = new ArrayList<>();
        generosListNumeros = new ArrayList<>();
        plataformasListNumeros = new ArrayList<>();

        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) view.getContext(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                }
                recullDeGaleria();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtenemos los datos de los editText
                String nombre = nombreJuego.getText().toString();
                String descripcion = descripcionJuego.getText().toString();
                int precio = Integer.parseInt(precioJuego.getText().toString());
                String favorito = "False";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //Realizamos la compresión
                ((BitmapDrawable)imagenJuego.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                //Lo guardamos en un array de byte[]
                byte[] foto = baos.toByteArray();
                db.abrir();
                //lo tocho el insert
                Cursor resultado = db.insertarJuego(nombre, descripcion, precio , foto,favorito);
                //Si nos devuelve los datos
                if (resultado.getCount() != 0) {
                    //Toast.makeText(getApplicationContext(),"Se ha insertado correctamente : " + nombreJuego.getText().toString(), Toast.LENGTH_SHORT);
                    //obtenemos la id del juego
                    int idJuego = resultado.getInt(0);
                    //generoslistnumeros y plataformaslistnumeros con las id de los generos y plataformas
                    if (generosListNumeros.size() != 0){
                        for (int i = 0 ;i < generosListNumeros.size();i++){
                            //Toast.makeText(getApplicationContext(),"El valor es: " + generosListNumeros.get(i),Toast.LENGTH_SHORT).show();
                            db.insertarJuegosGeneros(idJuego,generosListNumeros.get(i));
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"No hay generos seleccionados",Toast.LENGTH_SHORT).show();
                    }

                    if (plataformasListNumeros.size() != 0){
                        for (int i = 0 ;i < plataformasListNumeros.size();i++){
                            //Toast.makeText(getApplicationContext(),"El valor es: " + plataformasListNumeros.get(i),Toast.LENGTH_SHORT).show();
                            db.insertarJuegosPlataformas(idJuego,plataformasListNumeros.get(i));
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"No hay plataformas seleccionados",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error : " + nombreJuego.getText().toString(), Toast.LENGTH_SHORT);
                }
                db.cerrar();

            }
        });

        btnDialogoGeneros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoGeneros();
            }
        });

        btnDialogoPlataformas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoPlataformas();
            }
        });

        //cerramos la conexión
        db.cerrar();



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

    private void dialogoPlataformas(){
        ArrayList<String> plataformasListDialogo = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(PruebaIncluirJuego.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_prueba_dialog_plataformas,null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        LinearLayout layout = view.findViewById(R.id.llDialogoPlataformas);
        db.abrir();
        Cursor plataformas = db.obtenerTodosLasPlataformas();
        //preparamos el cursor
        plataformas.moveToFirst();
        //rellenar arraylist
        while(!plataformas.isAfterLast()){
            plataformasListDialogo.add(plataformas.getString(1));
            plataformas.moveToNext();
        }

        for(int i = 0; i<plataformasListDialogo.size();i++){
            checkBox = new CheckBox(this);
            checkBox.setId(i+1);
            checkBox.setText(plataformasListDialogo.get(i));
            checkBox.setOnClickListener(hacerAlgoPlataformas(checkBox));
            layout.addView(checkBox);
        }

        db.cerrar();

        Button btnCancelar = view.findViewById(R.id.btnPruebaDialogoCancelarPlataformas);
        Button btnConfirmar = view.findViewById(R.id.btnPruebaDialogoConfirmarPlataformas);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Elegidos",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private View.OnClickListener hacerAlgoPlataformas(final CheckBox checkBox) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    //Toast.makeText(getApplicationContext(),"Soy el checkbox: " + checkBox.getText().toString(),Toast.LENGTH_SHORT).show();
                    plataformasListNumeros.add(checkBox.getId());
                }else{
                    //Toast.makeText(getApplicationContext(),"Soy el checkbox: " + checkBox.getId(),Toast.LENGTH_SHORT).show();
                    plataformasListNumeros.remove(checkBox.getId());
                }
            }
        };
    }

    private void dialogoGeneros(){
        ArrayList<String> generosListDialogo = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(PruebaIncluirJuego.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_prueba_dialog_generos,null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        LinearLayout layout = view.findViewById(R.id.llDialogoGeneros);
        db.abrir();
        Cursor generos = db.obtenerTodosLosGeneros();
        //preparamos el cursor
        generos.moveToFirst();
        //rellenar arraylist
        while(!generos.isAfterLast()){
            generosListDialogo.add(generos.getString(1));
            generos.moveToNext();
        }

        for(int i = 0; i<generosListDialogo.size();i++){
            checkBox = new CheckBox(this);
            checkBox.setId(i+1);
            checkBox.setText(generosListDialogo.get(i));
            checkBox.setOnClickListener(hacerAlgoGeneros(checkBox));
            layout.addView(checkBox);
        }

        db.cerrar();

        Button btnCancelar = view.findViewById(R.id.btnPruebaDialogoCancelarGeneros);
        Button btnConfirmar = view.findViewById(R.id.btnPruebaDialogoConfirmarGeneros);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Elegidos",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private View.OnClickListener hacerAlgoGeneros(final CheckBox checkBox) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    //Toast.makeText(getApplicationContext(),"Soy el checkbox: " + checkBox.getText().toString(),Toast.LENGTH_SHORT).show();
                    generosListNumeros.add(checkBox.getId());
                }else{
                    //Toast.makeText(getApplicationContext(),"Soy el checkbox: " + checkBox.getId(),Toast.LENGTH_SHORT).show();
                    generosListNumeros.remove(checkBox.getId());
                }
            }
        };
    }
}
