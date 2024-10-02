package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaYHora;
    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
    private List<Producto> productos;
    private double total;
    @ManyToOne
    private Tarjeta tarjeta;
    @ManyToOne
    private Cliente cliente;

    public Venta(List<Producto> productos, Cliente unCliente, double total, Tarjeta tarjeta) {
        this.productos = productos;
        this.cliente=unCliente;
        this.total=total;
        this.fechaYHora=LocalDate.now();
        this.tarjeta=tarjeta;


    }

    public double getTotal() {
        return total;
    }

    public List<Producto> productosComprados() {
        return productos;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
