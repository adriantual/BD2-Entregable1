package ar.unrn.tp.web;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.model.Categoria;
import ar.unrn.tp.model.DTO.ProductoDTO;
import ar.unrn.tp.model.Marca;
import ar.unrn.tp.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoServiceController {


    @Autowired
    private ProductoService productoService;

    // Crear un nuevo producto
    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDTO request) {
        try {
            //esta bien hacer esto o estoy rompiendo las capaz?

            productoService.crearProducto(request.getCodigo(), request.getDescripcion(), request.getPrecio(), request.getCategoriaId(), request.getMarcaId());
            return ResponseEntity.ok("Producto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear producto: " + e.getMessage());
        }
    }

    // Modificar un producto existente
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarProducto(@PathVariable Long id, @RequestBody ProductoDTO request) {
        try {

            productoService.modificarProducto(id, request.getCodigo(), request.getDescripcion(), request.getPrecio(), request.getCategoriaId(), request.getMarcaId());
            return ResponseEntity.ok("Producto modificado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al modificar producto: " + e.getMessage());
        }
    }

    // Listar todos los productos
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        try {
            List<Producto> productos = productoService.listarProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Buscar productos por una lista de IDs
    @PostMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(@RequestBody List<Long> ids) {
        try {
            List<Producto> productos = productoService.buscarProductos(ids);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}