package ar.unrn.tp.api;

import ar.unrn.tp.model.*;


import java.util.List;

public interface ProductoService {

    //validar que sea una categoría existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, double precio, Long categoria, Long marca);
    //validar que sea un producto existente
    void modificarProducto(Long idProducto, String codigo, String descripcion, double precio,  Long categoria, Long marca);

        //Devuelve todos los productos
    List listarProductos();

    List<Producto> buscarProductos(List<Long> idsProductos);
}
