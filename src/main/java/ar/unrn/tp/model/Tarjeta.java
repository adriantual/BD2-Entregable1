package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity
@NoArgsConstructor
public class Tarjeta {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String marca;
    private int nro;



    public Tarjeta(String marca) {
        this.marca = marca;

    }

    public Tarjeta(String marca, int nro) {
        this.marca = marca;
        this.nro = nro;
    }

    public boolean validarPago() {
        return true;
    }

    public String marca() {
        return marca;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(marca, tarjeta.marca);
    }

    public int hashCode() {
        return Objects.hash(marca);
    }
}
