package edu.co.ustavillavo.noxia.dto;

import edu.co.ustavillavo.noxia.model.Usuario;

public class RegisterRequest {
    private String username;
    private String password;
    private Usuario.Rol rol;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario.Rol getRol() {
        return rol;
    }

    public void setRol(Usuario.Rol rol) {
        this.rol = rol;
    }
}