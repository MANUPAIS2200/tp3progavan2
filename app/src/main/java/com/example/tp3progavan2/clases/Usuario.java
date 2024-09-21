package com.example.tp3progavan2.clases;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    public Usuario (String nombre, String email, String pass){
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
    }

    private String nombre;
    private String email;
    private String pass;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    //Por si nos sirve para comparar usuarios y verificar que los datos ingresados no esten repetidos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
