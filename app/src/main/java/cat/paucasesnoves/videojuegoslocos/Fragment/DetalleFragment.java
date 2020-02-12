package cat.paucasesnoves.videojuegoslocos.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.acciones.ModificarJuego;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class DetalleFragment extends Fragment {
    private static final int CALLBACK_STORAGE = 666;
    DBInterface db;
    EditText nombreJuego,descripcionJuego,precioJuego;
    Button btnImagenJuego,btnModificarJuego;
    ImageView imagenJuego;
    Cursor juegoCursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragmento_detalle,container,false);

    }
    public void mostrarDetalle(final String juego,final int idPlataforma){
        //Toast.makeText(getActivity(),"Hola soy la plataforma: " + idPlataforma,Toast.LENGTH_SHORT).show();

        nombreJuego = getView().findViewById(R.id.etModificarNombreJuego);
        descripcionJuego = getView().findViewById(R.id.etModificarDescripcionJuego);
        precioJuego = getView().findViewById(R.id.etModificarPrecioJuego);
        btnImagenJuego = getView().findViewById(R.id.btnModificarImagenJuego);
        btnModificarJuego = getView().findViewById(R.id.btnModificarModificarJuego);
        imagenJuego = getView().findViewById(R.id.ivModificarImagenJuego);

        db = new DBInterface(getView().getContext());
        db.abrir();

        //viene ya preparado para usarlo
        juegoCursor = db.obtenerJuego(juego);
        nombreJuego.setText(juegoCursor.getString(1));
        descripcionJuego.setText(juegoCursor.getString(2));
        precioJuego.setText(juegoCursor.getInt(3)+"");
        Bitmap bmp = BitmapFactory.decodeByteArray(juegoCursor.getBlob(4),0,juegoCursor.getBlob(4).length);
        imagenJuego.setImageBitmap(Bitmap.createScaledBitmap(bmp,200,200,false));
        imagenJuego.setVisibility(View.VISIBLE);

        btnImagenJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) view.getContext(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                }
                recullDeGaleria();*/
                pedirPermisosStorage();
            }
        });

        btnModificarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreJuego.getText().toString();
                String descripcion = descripcionJuego.getText().toString();
                int precio = Integer.parseInt(precioJuego.getText().toString());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ((BitmapDrawable)imagenJuego.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] foto = baos.toByteArray();

                db.abrir();
                juegoCursor = db.obtenerJuego(juego);
                boolean isWorks = db.actualizaJuego(juegoCursor.getInt(0),nombre,descripcion,precio,foto);

                if(isWorks){
                    Toast.makeText(getView().getContext(),"Funciona",Toast.LENGTH_SHORT).show();
                    //cargamos el intent de la lista de jogos
                    Intent i = new Intent(view.getContext(), ModificarJuego.class);
                    i.putExtra("Id",idPlataforma);
                    startActivity(i);
                }else{
                    Toast.makeText(getView().getContext(),"No Funciona",Toast.LENGTH_SHORT).show();
                }

                db.cerrar();
            }
        });

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

    public void pedirPermisosStorage(){
        if(demanaPermisos()){
            int permissionCheck = ContextCompat.checkSelfPermission(getView().getContext(),Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},CALLBACK_STORAGE);
            }else {
                recullDeGaleria();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CALLBACK_STORAGE: {
                // Si es cancela la petició l'aray de tornada es buit.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permís concedit
                    Toast.makeText(getView().getContext(),"Permisos concedidos de calendario", Toast.LENGTH_SHORT).show();

                } else {
                    // permís denegat
                    // Desactivar la funcionalitat relacionada amb el permís
                    Toast.makeText(getView().getContext(),"Permisos no concedidos de calendario", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    private boolean demanaPermisos(){
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }
}
