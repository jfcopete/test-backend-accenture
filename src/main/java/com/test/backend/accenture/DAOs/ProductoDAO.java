package com.test.backend.accenture.DAOs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.accenture.dtos.Producto;

@Component
public class ProductoDAO {
	
	/**
	 * Contador para el ID del arraylist
	 */
	private static Integer productCounter = 4;
	/**
	 * Estructura de datos de los productos
	 */
	private static List<Producto> productos = new ArrayList<>();
	/**
	 * Inicialización de los productos
	 */
	static {
		productos.add(new Producto(0, "Domicilio", "Servicio de entrega", 5000.00));
		productos.add(new Producto(1, "Procesador Ryzen 3600", "Procesador Ryzen", 760000.00));
		productos.add(new Producto(2, "Diadema Gamer", "Diadema Gamer", 60000.00));
		productos.add(new Producto(3, "Mouse Gamer Logitech", "Mouse Gamer Logitech", 76000.00));
		productos.add(new Producto(4, "MousePad Gamer", "MousePad Gamer", 30000.00));
		
	}
	
	/**
	 * @return los productos en formato Json, obtenidos del DAO y modelados por el DTO
	 */
	public List<Producto> findAll()
	{
		return productos;
	}
	
	/**
	 * Retorna el producto por id
	 * @param id
	 * @return El producto
	 */
	public Producto findById (int id) {
		for (Producto producto : productos) {
			if (producto.getIdProducto()==id) {
				return producto;
			}
		}
		return null;
	}
	
	/**
	 * Añade un producto a la estructura de datos
	 * @param producto
	 */
	public Producto addProducto(Producto producto)
	{
		if (producto.getIdProducto().equals(null)) {
			producto.setIdProducto(++productCounter);
		}
		productos.add(producto);
		return producto;
	}
	
	/**
	 * Elimina un producto de la estructura de datos
	 * @param id
	 */
	public Producto deleteProductoById(int id)
	{
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
			Producto producto = iterator.next();
			if (producto.getIdProducto()==id) {
				iterator.remove();
				return producto;
			}
		}
		return null;
	}
	

}
