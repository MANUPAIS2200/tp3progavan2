package com.example.tp3progavan2.clases;

public class Parking {




    public Parking(String matricula, String tiempo, String usuario, String borrado)
    {
        this.Matricula = matricula;
        this.Tiempo = tiempo;
        this.Usuario = usuario;
        this.Borrado = borrado;
    }

    private String Matricula;
    private String Tiempo;
    private String Usuario;
    private String Borrado;

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
        this.Borrado = borrado;
    }

    @Override
    public String toString() {
        return "Parking{" +
                ", Matricula='" + Matricula +
                ", Tiempo='" + Tiempo + '\'' +
                ", usuario='" + Usuario + '\'' +
                ", borrado='" + Borrado + '\'' +
                '}';
    }
}
