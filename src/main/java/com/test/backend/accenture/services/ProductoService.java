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

import com.test.backend.accenture.DAOs.ProductoDAO;
import com.test.backend.accenture.dtos.Producto;

/**
 * Servicio REST 
 * @author Juan fernando Copete Mutis
 *
 */
@RestController
public class ProductoService {

	/**
	 * Permite la instanciación automatica del DAO
	 */
	@Autowired
	private ProductoDAO productoService;


	/**
	 * localhost:8080/{path}
	 * @return los productos en formato Json, obtenidos del DAO y modelados por el DTO
	 */
	@GetMapping("/productos")
	public List<Producto> getAllProductos()
	{
		return productoService.findAll();
	}

	/**
	 * Retorna el producto por id
	 * @param id
	 * @return
	 */
	@GetMapping("productos/{id}")
	public Producto getProductoById(@PathVariable int id)
	{
		return productoService.findById(id);
	}

	/**
	 * Añade un producto a la estructura de datos
	 * @param producto
	 * @return 201 HTTP response si fue creado exitosamente
	 */
	@PostMapping("/productos")
	public ResponseEntity<Object> addProducto(@RequestBody Producto producto)
	{
		productoService.addProducto(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(producto.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Elimina un producto de la estructura de datos
	 * @param id
	 */
	@DeleteMapping("/productos/{id}")
	public void deleteByProductoId(@PathVariable int id)
	{
		Producto producto = productoService.deleteProductoById(id);
		if (producto.equals(null)) {
			//TODO
		}
	}
}
