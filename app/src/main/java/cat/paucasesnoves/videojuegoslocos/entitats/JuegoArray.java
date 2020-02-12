package cat.paucasesnoves.videojuegoslocos.entitats;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cat.paucasesnoves.videojuegoslocos.R;

public class JuegoArray extends ArrayAdapter<Juego> {

    private Context context;
    private List<Juego> juegos;
    private int resooo;

    public JuegoArray(Context context, ArrayList<Juego> objects, int resource){
        super(context,resource,objects);
        this.resooo = resource;
        this.context = context;
        this.juegos = objects;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public int getResooo() {
        return resooo;
    }

    public void setResooo(int resooo) {
        this.resooo = resooo;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Juego juego = juegos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resooo,null);

        TextView nom = (TextView) view.findViewById(R.id.nom);
        nom.setText(juego.getNombre());



        return view;
    }
}
