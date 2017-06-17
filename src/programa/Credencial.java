
package programa;

public class Credencial {
    private String usuario,hora,fecha;
    private int perfil;

    public Credencial(String usuario, String hora, String fecha, int perfil) {
        this.usuario = usuario;
        this.hora = hora;
        this.fecha = fecha;
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getHora() {
        return hora;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPerfil() {
        return perfil;
    }
    
    
}
