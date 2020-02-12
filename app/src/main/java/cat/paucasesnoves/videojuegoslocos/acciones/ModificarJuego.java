package cat.paucasesnoves.videojuegoslocos.acciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import cat.paucasesnoves.videojuegoslocos.Fragment.DetalleActivity;
import cat.paucasesnoves.videojuegoslocos.Fragment.DetalleFragment;
import cat.paucasesnoves.videojuegoslocos.Fragment.ListaFragment;
import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.Juego;

public class ModificarJuego extends AppCompatActivity implements ListaFragment.JuegosListener{

    int idPlataforma;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            idPlataforma = extras.getInt("Id");
        }
        //cargamos los datos que vienen de la clase ListaFragment
        ListaFragment frgLista = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.FrgLista);
        frgLista.setIdPlataforma(idPlataforma);
        frgLista.setJuegosListener(this);

    }

    // Esto es para crear el menu apartar de un layout menu
    @Override public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_mainactivity,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Buscador");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(getApplicationContext(),"Hola " + s,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), DetalleActivity.class);
                i.putExtra("nombreJuego",s);
                i.putExtra("idPlataforma",idPlataforma);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //Toast.makeText(getApplicationContext(),"Hola " + s,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;

    }

    public void NuevaPlataforma(){
        Intent i = new Intent(this, InsertarPlataforma.class);
        startActivity(i);
    }
    public void NuevoGenero(){
        Intent i = new Intent(this, InsertarGenero.class);
        startActivity(i);
    }
    public void insertarJuego(){
        Intent i = new Intent(this, PruebaIncluirJuego.class);
        i.putExtra("idPlataforma",idPlataforma);
        startActivity(i);
    }
    public void eliminarJuego(){
        Intent i = new Intent(this, EliminarJuego.class);
        startActivity(i);
    }

    //Gestionar las opciones del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.insertarPlataforma:
                NuevaPlataforma();
                return true;
            case R.id.insertarGenero:
                NuevoGenero();
                return true;
            case R.id.insertarJuego:
                insertarJuego();
                return true;
            case R.id.eliminarJuego:
                eliminarJuego();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //se llama cuando pulsamos el elemento de la lista
    @Override
    public void onJuegoSeleccionado(String juego) {
        //Para saber en que modo esta el teléfono
        boolean horizontal = getResources().getBoolean(R.bool.modeHoritzontal);
        //en horizontal cargamos la actividad DetalleFragment
        if(horizontal){
            ((DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(juego,4);
        }else {
            //Si es vertical cargamos la actividad DetalleActivity y pasamos el nombre del juego
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra("nombreJuego",juego);
            i.putExtra("idPlataforma",idPlataforma);
            startActivity(i);
        }
    }
}
