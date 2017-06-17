package programa;

public class Licencias {
    private String rut;
    private String enfermedad;
    private int dias;
    private String isapre;
    private String fecha;
    private String estado;

    public Licencias(String rut, String enfermedad, int dias, String isapre, String fecha, String estado) {
        this.rut = rut;
        this.enfermedad = enfermedad;
        this.dias = dias;
        this.isapre = isapre;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getIsapre() {
        return isapre;
    }

    public void setIsapre(String isapre) {
        this.isapre = isapre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
