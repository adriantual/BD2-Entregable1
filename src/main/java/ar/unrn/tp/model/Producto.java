package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="idCategoria")
    private Categoria categoria;
    private double precio;
    private double porcentajeDeDescuento;

    public Producto(int codigo, String descripcion, Categoria categoria, double precio, Marca marca) {

        validarCodigo(codigo);
        validarDescripcion(descripcion);
        validarCategoria(categoria);
        validarPrecio(precio);

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.marca= marca;
    }


    public double precio() {
        return precio;
    }

    public Marca marca() {
        return marca;
    }

    public void asignarDescuento(double porcentajeDeDescuento) {
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }
    public double CalcularTotalConDescuento()
    {
        return precio-(precio*porcentajeDeDescuento);}










    // validaciones
    private void validarCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código del producto debe ser un número positivo.");
        }
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vacía.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría del producto no puede ser nula.");
        }
    }

    private void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
