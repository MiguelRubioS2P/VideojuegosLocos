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

        ListaFragment frgLista = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.FrgLista);
        frgLista.setJuegosListener(this);

    }

    @Override
    public void onJuegoSeleccionado(String juego) {
        boolean horizontal = getResources().getBoolean(R.bool.modeHoritzontal);

        if(horizontal){
            ((DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(juego);
        }else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra("nombreJuego",juego);
            startActivity(i);
        }
    }
}
