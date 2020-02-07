package cat.paucasesnoves.videojuegoslocos.acciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Picture;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;

public class InsertarPlataforma extends AppCompatActivity {
  DBInterface bd;
    Bitmap imatge_bitmap;
    ImageView imagenPlataforma;
    byte[] bitmapmap;//Contenedor de la info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_plataforma);

        nuevaPlataforma();
    }


    private void nuevaPlataforma() {
        imagenPlataforma = (ImageView) findViewById(R.id.imgPlataforma);

        Button btnPlat = (Button) findViewById(R.id.btnInsertPlat);
        btnPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) v.getContext(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    recullDeGaleria();

                }
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
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                //Get the column index of MediaStore.Images.Media.DATA
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //Gets the String value in the column
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Transormarem una imatge a bitmap i seguidament a bytes per a guardar-ho a l'objecte i a la BBDD
                //imatge_bitmap = BitmapFactory.decodeFile(imgDecodableString);
               // byte[] pepe = imgDecodableString.getBytes();
               // Resources direccion = new Resources(getDrawable());

               // direccion = imgDecodableString;
               // imatge_bitmap = BitmapFactory.decodeResource(imgDecodableString,getResources());
              //  imatge_bitmap = BitmapFactory.decodeByteArray(pepe,1,1);
               // Bitmap.;*/
               /*
               byte[] pepe = imgDecodableString.getBytes();
                */

                ByteArrayOutputStream blob = new ByteArrayOutputStream();
                imatge_bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /* Ignored for PNGs */, blob);
                //La información a guardar es el array de bitmap
                bitmapmap = blob.toByteArray();
                imagenPlataforma.setImageBitmap(imatge_bitmap);
                //imagenPlataforma.setVisibility(View.VISIBLE);
            }
        }


    }
}
