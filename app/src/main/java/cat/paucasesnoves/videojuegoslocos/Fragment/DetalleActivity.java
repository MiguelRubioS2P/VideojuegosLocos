package cat.paucasesnoves.videojuegoslocos.Fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class DetalleActivity extends AppCompatActivity {

    String juego;
    private DBInterface db;
    EditText nombreJuego,descripcionJuego,precioJuego;
    Button btnImagenJuego,btnModificarJuego;
    ImageView imagenJuego;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        //relacionar la clase con los elementos del layout
        nombreJuego = findViewById(R.id.etDetalleNombreJuego);
        descripcionJuego = findViewById(R.id.etDetalleDescripcionJuego);
        precioJuego = findViewById(R.id.etDetallePrecioJuego);
        btnImagenJuego = findViewById(R.id.btnDetalleImagenJuego);
        btnModificarJuego = findViewById(R.id.btnDetalleModificarJuego);
        imagenJuego = findViewById(R.id.ivDetalleImagenJuego);

        //obtenemos el nombre del juego seleccionado
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            juego = extras.getString("nombreJuego");
        }

        db = new DBInterface(getApplication());
        db.abrir();

        //viene ya preparado para usarlo
        Cursor juegoCursor = db.obtenerJuego(juego);
        nombreJuego.setText(juegoCursor.getString(1));
        descripcionJuego.setText(juegoCursor.getString(2));
        precioJuego.setText(juegoCursor.getInt(3)+"");
        Bitmap bmp = BitmapFactory.decodeByteArray(juegoCursor.getBlob(4),0,juegoCursor.getBlob(4).length);
        imagenJuego.setImageBitmap(Bitmap.createScaledBitmap(bmp,200,200,false));
        imagenJuego.setVisibility(View.VISIBLE);

        db.cerrar();

        //Toast.makeText(getApplicationContext(),"El nombre del juego es: " + juego,Toast.LENGTH_SHORT).show();


    }
}
