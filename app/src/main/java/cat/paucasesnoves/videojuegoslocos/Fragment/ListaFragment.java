package cat.paucasesnoves.videojuegoslocos.Fragment;

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

public class ListaFragment extends Fragment {
    private ArrayList<String> lista;
    private ListView lvLista;
    private JuegosListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_fragmento_lista,container,false);
    }

    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);

        lvLista = getView().findViewById(R.id.lvListaFragmento);
        lista = new ArrayList<>();
        lista.add("Prueba1");
        lista.add("Prueba2");
        lista.add("Prueba3");

        lvLista.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lista));

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int i, long l) {
                if(listener != null){
                    listener.onJuegoSeleccionado((String) list.getAdapter().getItem(i));
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
}
