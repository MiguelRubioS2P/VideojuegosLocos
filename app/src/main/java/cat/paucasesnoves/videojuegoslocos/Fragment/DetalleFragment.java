package cat.paucasesnoves.videojuegoslocos.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import cat.paucasesnoves.videojuegoslocos.R;

public class DetalleFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragmento_detalle,container,false);
    }
    public void mostrarDetalle(String juego){
        Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
    }
}
