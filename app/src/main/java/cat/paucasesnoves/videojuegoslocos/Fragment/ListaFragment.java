package cat.paucasesnoves.videojuegoslocos.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import cat.paucasesnoves.videojuegoslocos.R;
import cat.paucasesnoves.videojuegoslocos.entitats.DBInterface;
import cat.paucasesnoves.videojuegoslocos.entitats.Juego;
import cat.paucasesnoves.videojuegoslocos.entitats.JuegoArray;

public class ListaFragment extends Fragment {
    private DBInterface db;
    private ArrayList<String> lista;
    private ArrayList<Integer> listaIdJuegos;
    private ListView lvLista;
    private JuegosListener listener;
    private int idPlataforma;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_fragmento_lista,container,false);
    }

    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);

        lvLista = getView().findViewById(R.id.lvListaFragmento);
        lista = new ArrayList<>();
        listaIdJuegos = new ArrayList<>();
        Cursor juego = null;
        db = new DBInterface(getContext());
        db.abrir();
        //nintendo 4
        //prueba
        Cursor juegosPlataforma = db.obtenerJuegosPlataformas(idPlataforma);
        //estoy guardando todas las id de los juegos que estan para la plataforma que nos pasan por id
        while(!juegosPlataforma.isAfterLast()){
            listaIdJuegos.add(juegosPlataforma.getInt(1));
            juegosPlataforma.moveToNext();
        }

        for (int i = 0; i < listaIdJuegos.size(); i++){
            juego = db.obtenerJuegoId(listaIdJuegos.get(i));
            lista.add(juego.getString(1));
        }

        db.cerrar();

//        lista.add(new Juego(1,"prueba1","prueba1",1,null,"false"));
//        lista.add(new Juego(2,"prueba2","prueba2",1,null,"false"));
        /*lista.add("Prueba1");
        lista.add("Prueba2");
        lista.add("Prueba3");*/

        lvLista.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_selectable_list_item,lista));
        //JuegoArray juegosAdapter = new JuegoArray(getActivity(),lista,R.layout.pruebalistfragmentestilobarroco);
        //lvLista.setAdapter(juegosAdapter);
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int i, long l) {
                if(listener != null){
                    listener.onJuegoSeleccionado((String) list.getAdapter().getItem(i));
                    //listener.onJuegoSeleccionado((Juego) list.getAdapter().getItem(i));
                }
            }
        });

    }

    public interface JuegosListener{
        void onJuegoSeleccionado(String juego);
    }
    public void setJuegosListener(JuegosListener listener){
        this.listener=listener;
    }
    //para poder obtener la id de la plataforma
    public void setIdPlataforma(int idPlataforma){
        this.idPlataforma = idPlataforma;
    }
}
