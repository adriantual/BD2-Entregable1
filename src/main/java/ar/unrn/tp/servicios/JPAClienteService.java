package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.model.Cliente;
import ar.unrn.tp.model.Tarjeta;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class JPAClienteService implements ClienteService {
    @Autowired
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    //con transactional tenes que sacar los try y catch ya que se encarga solo de hacer la transaccion
    public void crearCliente(String nombre, String apellido, String dni, String email) {

        try {

            Cliente nuevoCliente = new Cliente(nombre, apellido, Integer.parseInt(dni), email);
            em.persist(nuevoCliente);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {

        try {

            Cliente cliente = em.getReference(Cliente.class, idCliente);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(Integer.parseInt(dni));
            cliente.setEmail(email);
            em.persist(cliente);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional
    public void agregarTarjeta(Long idCliente, int nro, String marca) {


        try {

            //int numeroro= nro;
            //Tarjeta nuevaTarjeta = new Tarjeta(marca);
            Cliente cliente = em.find(Cliente.class, idCliente);
            Tarjeta nuevaTarjeta = new Tarjeta(marca, nro);


            cliente.agregarTarjeta(nuevaTarjeta);
            em.persist(cliente);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    //no va transactional?
    public List listarTarjetas(Long idCliente) {
        Cliente cliente = em.getReference(Cliente.class, idCliente);
        //Cliente cliente = em.find(Cliente.class, idCliente);
        return cliente.getTarjetas();
    }

    @Override

    public Cliente buscarCliente(Long idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    @Override

    public Tarjeta buscarTarjeta(Long idTarjeta) {
        return em.getReference(Tarjeta.class, idTarjeta);
    }

}
