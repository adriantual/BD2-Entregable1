package ar.unrn.tp.web;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.model.Cliente;
import ar.unrn.tp.model.DTO.ClienteDTO;
import ar.unrn.tp.model.DTO.TarjetaDTO;
import ar.unrn.tp.servicios.JPAClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteServiceController {

    @Autowired
    private ClienteService clienteService;

    // Crear un nuevo cliente
    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@RequestBody ClienteDTO request) {
        try {
            this.clienteService.crearCliente(request.getNombre(), request.getApellido(), request.getDni(), request.getEmail());
            return ResponseEntity.ok("Cliente creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear cliente: " + e.getMessage() );
        }
    }

    // Modificar un cliente existente
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarCliente(@PathVariable Long id, @RequestBody ClienteDTO request) {
        try {
            clienteService.modificarCliente(id, request.getNombre(), request.getApellido(), request.getDni(), request.getEmail());
            return ResponseEntity.ok("Cliente modificado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al modificar cliente: " + e.getMessage());
        }
    }

    // Agregar una tarjeta a un cliente
    @PostMapping("/{idCliente}/tarjetas")
    public ResponseEntity<String> agregarTarjeta(@PathVariable Long idCliente, @RequestBody TarjetaDTO request) {
        try {
            clienteService.agregarTarjeta(idCliente, request.getNro(), request.getMarca());
            return ResponseEntity.ok("Tarjeta agregada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar tarjeta: " + e.getMessage());
        }
    }

    // Listar tarjetas de un cliente
    @GetMapping("/{idCliente}/tarjetas")
    public ResponseEntity<List> listarTarjetas(@PathVariable Long idCliente) {
        try {
            List tarjetas = clienteService.listarTarjetas(idCliente);
            return ResponseEntity.ok(tarjetas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Buscar un cliente por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}