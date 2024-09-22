package com.example.tp3progavan2.clases;

public class Parking {




    public Parking(int idParking, String matricula, String tiempo, String usuario, String borrado)
    {
        this.IdParking = idParking;
        this.Matricula = matricula;
        this.Tiempo = tiempo;
        this.Usuario = usuario;
        this.Borrado = borrado;
    }

    private int IdParking;
    private String Matricula;
    private String Tiempo;
    private String Usuario;
    private String Borrado;

    public int getIdParking() {
        return IdParking;
    }

    public void setIdParking(int idParking) {
        IdParking = idParking;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        this.Tiempo = tiempo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getBorrado() {
        return Borrado;
    }

    public void setBorrado(String borrado) {
        this.Usuario = borrado;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "IdParking=" + IdParking +
                ", Matricula='" + Matricula + '\'' +
                ", Tiempo='" + Tiempo + '\'' +
                ", usuario='" + Usuario + '\'' +
                ", borrado='" + Borrado + '\'' +
                '}';
    }
}
