package cat.paucasesnoves.videojuegoslocos.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.lang.reflect.Type;

/*
*
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
* */
import cat.paucasesnoves.videojuegoslocos.MainActivity;
import cat.paucasesnoves.videojuegoslocos.R;


public class Login extends AppCompatActivity {
    EditText usuario, contraseña;
    //Shared Preferences
    private SharedPreferences prefs;
    private ArrayList<Usuario> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login();
    }

    private void Login() {

        Button btnGen = (Button) findViewById(R.id.btnStartSesion);

        //Se ha dado click sobre el boton de añadir Genero
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = findViewById(R.id.nameUser);
                contraseña = findViewById(R.id.passUser);


               //nuevoUser();
                comprobarUser();
            }
        });
    }

    //Para caso de Registrar futura actu . . .
    private void introducirDatos(){
        Usuario pepe = new Usuario("Jose","Pepe");
        Usuario luis = new Usuario("Luis","Rigo");
        Usuario rubio = new Usuario("Miguel","Rubio");
        users.add(pepe);
        users.add(luis);
        users.add(rubio);

        prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
        //Utilizamos el editor de Shared para hacer cambios
        SharedPreferences.Editor editor = prefs.edit();
        /*
        Gson gson = new Gson();
        //Utilizamos el gson para poder pasar el listado de items a String
        String json = gson.toJson(items);
        //Esto lo guardamos en el archivo
        editor.putString("users", json);
        //y aplicamos los cambios
        editor.apply();
        */
        Toast toast1;
        toast1 = Toast.makeText(getApplicationContext(),"Hecho " + usuario.getText() +" "+ contraseña.getText(), Toast.LENGTH_SHORT);
        toast1.show();
    }
    private void nuevoUser(){
        prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user","Pepe");
        editor.putString("password","Rubio");
        editor.putBoolean("estado",false);
        editor.commit();

        Toast toast1;
        toast1 = Toast.makeText(getApplicationContext(),"Insertado " + usuario.getText() +" "+ contraseña.getText(), Toast.LENGTH_SHORT);
        toast1.show();
    }

    private void comprobarUser(){
        prefs = getApplicationContext().getSharedPreferences("Usuarios", MODE_PRIVATE);
//Utilizamos el Gson
        String Usuario = prefs.getString("user",null);
        String contra = prefs.getString("password",null);

        Toast toast1;
        String Resultado = "";
        //Usuario contra
        if(Usuario.equals(usuario.getText().toString()) && contra.equals(contraseña.getText().toString())) {
            Resultado = "Iniciada Sesion";
            //Primera opción utilizar un boolean para comprobar si se ha iniciado sesión.
            Usuario pepe = new Usuario();
            pepe.setEstado(true);
            toast1 = Toast.makeText(getApplicationContext(),Resultado, Toast.LENGTH_SHORT);
            toast1.show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("estado",true);
            editor.apply();

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("log",true);
            startActivity(i);        //User
        }else {

            if (Usuario.equals(usuario.getText().toString())) {
                Resultado += "El usuario existe\n";
                //Contra
            } else {
                Resultado += "El usuario insertado no existe\n";
            }
            if (!(contra.equals(contraseña.getText().toString()))) {
                Resultado += "La contraseña es erronea.\n";

            }
        }
        toast1 = Toast.makeText(getApplicationContext(),Resultado, Toast.LENGTH_SHORT);
        toast1.show();
    }


}
