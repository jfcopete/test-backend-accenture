package com.test.backend.accenture.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.backend.accenture.DAOs.ClienteDAO;
import com.test.backend.accenture.dtos.Cliente;

/**
 * Servicio REST 
 * @author Juan fernando Copete Mutis
 *
 */
@RestController
public class ClienteService {
	
	/**
	 * Permite la instanciación automatica del DAO
	 */
	@Autowired
	private ClienteDAO service;
	
	/**
	 * Retorna los clientes registrados
	 * @return
	 */
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes()
	{
		return service.findAll();
	}
	
	/**
	 * Retorna el cliente con id indicaddo por parametro
	 * @param id
	 * @return Cliente
	 */
	@GetMapping("clientes/{id}")
	public Cliente getClienteById(@PathVariable int id)
	{
		return service.findById(id);
	}
	
	/**
	 * Registra un cliente en la estructura de datos
	 * @param cliente
	 * @return 201 HTTP si fué creado exitosamente
	 */
	@PostMapping("/clientes")
	public ResponseEntity<Object> addCliente(@RequestBody Cliente cliente)
	{
		service.addCliente(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Elimina un cliente dado el id por PathParam
	 * @param id
	 */
	@DeleteMapping("/clientes/{id}")
	public void deleteByClienteId(@PathVariable int id)
	{
		Cliente cliente = service.deleteCliente(id);
		if (cliente.equals(null)) {
			//TODO
		}
	}
}
