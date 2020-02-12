package cat.paucasesnoves.videojuegoslocos.SharedPreferences;

public class Usuario {
    private String User;
    private String Password;
    private boolean estado = false;

    public Usuario() {
    }

    public Usuario(String user, String password) {
        User = user;
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
