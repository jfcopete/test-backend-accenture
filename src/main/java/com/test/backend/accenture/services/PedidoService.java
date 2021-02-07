package com.test.backend.accenture.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.backend.accenture.DAOs.PedidoDAO;
import com.test.backend.accenture.dtos.Pedido;

/**
 * Servicio REST 
 * @author Juan fernando Copete Mutis
 *
 */
@RestController
public class PedidoService {
	
	/**
	 * Permite la instanciación automatica del DAO
	 */
	@Autowired
	private PedidoDAO pedidoService;
	
	/**
	 * Retorna los pedios registrados
	 * @return Pedido
	 */
	@GetMapping("/pedidos")
	public List<Pedido> getAllPedidos(){
		return pedidoService.getAllPedidos();
	}
	
	/**
	 * Retorna el pedido con id indicaddo por parametro
	 * @param id
	 * @return Pedido
	 */
	@GetMapping("/pedidos/{id}")
	public Pedido getPedidoById(@PathVariable int id)
	{
		return pedidoService.getPedidoById(id);
	}
	
	/**
	 * Retorna los pedidos de un cliente
	 * @param idCliente
	 * @return Lista de pedidos 
	 */
	@GetMapping("clientes/{idCliente}/pedidos")
	public List<Pedido> getPedidoByCliente(@PathVariable int idCliente){
		return pedidoService.getAllPedidosByCliente(idCliente);
	}
	
	/**
	 * Guarda el registro de un pedido en la estructura de datos
	 * @param idCliente int
	 * @param pedido Pedido
	 * @return 201 HTTP Response si fué creado exitosamente
	 */
	@PostMapping("/clientes/{idCliente}/pedidos")
	public ResponseEntity<Object> createPedido(@PathVariable  int idCliente,@RequestBody Pedido pedido)
	{
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idClient}").buildAndExpand(idCliente).toUri();
		pedidoService.addPedido(pedido);
		return ResponseEntity.created(location).build();
	}
	
	
	/**
	 * Actualiza un pedido 
	 * @param idCliente
	 * @param idPedido
	 * @param pedido
	 * @return Pedido actualizado / 200 Empty si no fue posible
	 */
	@PutMapping("/clientes/{idCliente}/pedidos/{idPedido}")
	public Pedido updatePedido(@PathVariable int idCliente, @PathVariable int idPedido,@RequestBody Pedido pedido)
	{
		return pedidoService.updatePedido(idCliente, pedido, idPedido);
	}
	
	/**
	 * Añade un producto a un pedido existente
	 * @param idCliente
	 * @param idPedido
	 * @param idProducto
	 * @return Pedido Actualizado
	 */
	@PostMapping("/clientes/{idCliente}/pedidos/{idPedido}/addProduct/{idProducto}")
	public Pedido addProductoToPedido(@PathVariable int idCliente, @PathVariable int idPedido, @PathVariable int idProducto){
		return pedidoService.addProductToPedido(idCliente, idPedido, idProducto);
	}
	
	/**
	 * Elimina el pedido de un cliente
	 * @param idCliente
	 * @param idPedido
	 */
	@DeleteMapping("/clientes/{idCliente}/pedidos/{idPedido}")
	public void deletePedidoByCliente(@PathVariable int idCliente, @PathVariable int idPedido)
	{
		Pedido pedido = pedidoService.deletePedidoById(idCliente, idPedido);
		if (pedido==null) {
			
		}
	}
	
	
}
