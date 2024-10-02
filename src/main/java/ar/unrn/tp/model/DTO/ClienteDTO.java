package ar.unrn.tp.model.DTO;

import lombok.Data;
import lombok.Getter;
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;

    public ClienteDTO(String nombre, String apellido, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }
}
