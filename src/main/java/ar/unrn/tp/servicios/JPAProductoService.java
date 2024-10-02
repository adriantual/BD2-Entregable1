package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JPAProductoService implements ProductoService {
    @PersistenceContext
    @Autowired
    private EntityManager em;



    @Override
    @Transactional
    public void crearProducto (String codigo, String descripcion, double precio, Long categoria, Long marca){


        try {
            Categoria nuevaCategoria= em.find(Categoria.class, categoria);
            Marca nuevaMarca= em.find(Marca.class, marca);
            Producto producto = new Producto(Integer.parseInt(codigo), descripcion, nuevaCategoria, precio,nuevaMarca);
            em.persist(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public void modificarProducto(Long idProducto, String codigo, String descripcion,  double precio,  Long categoria, Long marca) {
        try {

            Categoria nuevaCategoria= em.find(Categoria.class, categoria);
            Marca nuevaMarca= em.find(Marca.class, marca);
            Producto producto = em.getReference(Producto.class, idProducto);
            producto.setCodigo(Integer.parseInt(codigo));
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCategoria(nuevaCategoria);
            producto.setMarca(nuevaMarca);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List listarProductos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

    }

    @Override
    public List<Producto> buscarProductos(List<Long> idsProductos) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.id IN :id", Producto.class)
                .setParameter("id", idsProductos)
                .getResultList();
    }
}
