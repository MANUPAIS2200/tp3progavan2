package com.example.tp3progavan2.clases;

public class Parking {


    public Parking(int idParking, String matricula, String tiempo)
    {
        this.IdParking = idParking;
        this.matricula = matricula;
        this.Tiempo = tiempo;
    }

    private int IdParking;
    private String matricula;
    private String Tiempo;

    public int getIdParking() {
        return IdParking;
    }

    public void setIdParking(int idParking) {
        IdParking = idParking;
    }

    public String getmatricula() {
        return matricula;
    }

    public void setmatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        this.Tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "IdParking=" + IdParking +
                ", matricula='" + matricula + '\'' +
                ", Tiempo='" + Tiempo + '\'' +
                '}';
    }
}
