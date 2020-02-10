package cat.paucasesnoves.videojuegoslocos.acciones;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cat.paucasesnoves.videojuegoslocos.Fragment.DetalleActivity;
import cat.paucasesnoves.videojuegoslocos.Fragment.DetalleFragment;
import cat.paucasesnoves.videojuegoslocos.Fragment.ListaFragment;
import cat.paucasesnoves.videojuegoslocos.R;

public class ModificarJuego extends AppCompatActivity implements ListaFragment.JuegosListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //cargamos los datos que vienen de la clase ListaFragment
        ListaFragment frgLista = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.FrgLista);
        frgLista.setJuegosListener(this);

    }

    //se llama cuando pulsamos el elemento de la lista
    @Override
    public void onJuegoSeleccionado(String juego) {
        //Para saber en que modo esta el teléfono
        boolean horizontal = getResources().getBoolean(R.bool.modeHoritzontal);
        //en horizontal cargamos la actividad DetalleFragment
        if(horizontal){
            ((DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(juego);
        }else {
            //Si es vertical cargamos la actividad DetalleActivity y pasamos el nombre del juego
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra("nombreJuego",juego);
            startActivity(i);
        }
    }
}
