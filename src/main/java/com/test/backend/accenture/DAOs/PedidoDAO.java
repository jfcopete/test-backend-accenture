package com.test.backend.accenture.DAOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.backend.accenture.dtos.Factura;
import com.test.backend.accenture.dtos.Pedido;
import com.test.backend.accenture.dtos.Producto;

@Component
public class PedidoDAO {
	/**
	 * Contador para amntener un id en el ArrayList
	 */
	private static Integer pedidoCounter=4;

	/**
	 * Constantes de tiempo en ms para eliminary/o actualizar un pedido
	 */
	public final Integer TWELVEHOURS=43200000;
	public final Integer FIVEHOURS=18000000;

	/**
	 * Autoinstancia de FacturaDAO
	 */
	@Autowired
	private FacturaDAO facturaService;

	/**
	 * Estructura de datos para pedidos
	 */
	private static List<Pedido> pedidos = new ArrayList<>();
	
	/**
	 * Inicialización de primeros datos
	 */
	static {
		pedidos.add(new Pedido(1, new ProductoDAO().findAll(), 1, new Date(), "ACCEPTED"));
		pedidos.add(new Pedido(2, new ProductoDAO().findAll(), 1, new Date(), "ACCEPTED"));
		pedidos.add(new Pedido(3, new ProductoDAO().findAll(), 2, new Date(), "ACCEPTED"));
		pedidos.add(new Pedido(4, new ProductoDAO().findAll(), 3, new Date(), "ACCEPTED"));
		
		//Elimina los costos de domicilio de los primeros datos en el arrayList
		deleteDeliveryFromPedidosCreatedManually();
	}

	/**
	 * Método auxiliar que permite eliminar los costos de domicilio 
	 * de los primeros pedidos creados
	 */
	public static void deleteDeliveryFromPedidosCreatedManually()
	{
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getPrice()>100000) {
				Iterator<Producto> iterator = pedidos.get(i).getProductos().iterator();
				pedidos.get(i).setPrice(pedidos.get(i).getPrice()-5000);
				while (iterator.hasNext()) {
					Producto producto2 = iterator.next();
					if (producto2.getIdProducto()==0) {
						iterator.remove();
					}
				}
			}
		}
	}
	
	/**
	 * Retorna los pedios registrados
	 * @return Pedido
	 */
	public List<Pedido> getAllPedidos(){
		return pedidos;
	}
	
	/**
	 * Retorna los pedidos de un cliente
	 * @param idCliente
	 * @return Lista de pedidos 
	 */
	public List<Pedido> getAllPedidosByCliente (int idCliente)
	{
		List<Pedido> pedidosDelCliente = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			if (pedido.getIdCliente()==idCliente) {
				pedidosDelCliente.add(pedido);
			}
		}
		return pedidosDelCliente;
	}

	/**
	 * * Guarda el registro de un pedido en la estructura de datos
	 * @param idCliente int
	 * @param pedido Pedido
	 * @return Pedido creado
	 */
	public Pedido addPedidoToAClient (int idClient, Pedido pedido)
	{
		if (pedido.getIdPedido().equals(null)) {
			pedido.setIdPedido(++pedidoCounter);
		}
		pedido.setIdCliente(idClient);
		deleteDelivery(pedido);
		pedidos.add(pedido);
		return pedido;
	}
	
	/**
	 * Obtiene un pedido en especifico por ID
	 * @param id
	 * @return Pedido solicitado / null si no lo encuentra
	 */
	public Pedido getPedidoById(int id)
	{
		for (Pedido pedido : pedidos) {
			if (pedido.getIdPedido()==id) {
				return pedido;
			}
		}
		return null;
	}
	
	/**
	 * Añade un nuevo pedido a la estructura de datos
	 * @param pedido
	 * @return Pedido creado
	 */
	public Pedido addPedido(Pedido pedido)
	{
		if (pedido.getIdPedido()==null) {
			pedido.setIdPedido(++pedidoCounter);
		}
		if (pedido.getDate()==null) {
			pedido.setDate(new Date());
		}
		double verifyPrice = pedido.calculatePrice(pedido.getProductos());
		if (verifyPrice<100000) {
			pedido.getProductos().add(new Producto(0, "Domicilio", "Servicio de entrega", 5000.00));
			pedido.setPrice(verifyPrice+5000);
		}else {
			pedido.getProductos().add(new Producto(0, "Domicilio", "Servicio de entrega", 0.00));
			pedido.setPrice(verifyPrice);
		}
		pedidos.add(pedido);
		facturaService.addFactura(new Factura(null, pedido));
		return pedido;
	}
	
	/**
	 * Actualiza un pedido generando una nueva verificación de precios
	 * para evitar que un hacker pueda modificar el precio y comprar por menor precio un pedido
	 * Además "valida" que la persona quien vaya a acrtualizar sea la dueña del recurso.
	 * @param idCliente
	 * @param pedido
	 * @param idPedido
	 * @return El pedido actualizado
	 */
	public Pedido updatePedido (int idCliente, Pedido pedido , int idPedido)
	{
		//Variable para controlar el precio de un pedido y evitar que un atacante lo pueda modificar
		Pedido newPedido = new Pedido(idPedido, pedido.getProductos(), idCliente, new Date(), pedido.getState());
		//-----------------------------------------------------------------------------------------
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getIdPedido()==idPedido&&pedidos.get(i).getIdCliente()==idCliente) {
				if (isUpdatedTime(pedido.getDate())==true) {
					if (newPedido.getPrice()>=pedidos.get(i).getPrice()) {
						deleteDelivery(newPedido);
						pedidos.set(i, newPedido);
						facturaService.updateFactura(idPedido, newPedido);
						return pedidos.get(i);
					}
				}

			}
		}
		return null;
	}

	/**
	 * Método auxiliar que permite determinar si el tiempo de actualizar es menor 
	 * a 5 horas o si por el contrario no puede modificarlo
	 * @param pedidoDate
	 * @return True si puede modificar, de lo contrario False
	 */
	public boolean isUpdatedTime(Date pedidoDate)
	{
		Date actualDate = new Date();
		if (actualDate.getTime()-pedidoDate.getTime()<FIVEHOURS) {
			return true;
		}
		return false;
	}

	/**
	 * Elimina Un pedido
	 * @param idCliente
	 * @param idPedido
	 * @return Pedido eliminado
	 */
	public Pedido deletePedidoById(int idCliente, int idPedido)
	{
		Iterator<Pedido> iteratos = pedidos.iterator();
		while (iteratos.hasNext()) {
			Pedido pedido = iteratos.next();
			if (pedido.getIdPedido()==idPedido && pedido.getIdCliente()==idCliente) {
				if (isCancelledTime(pedido.getDate())==true) {
					iteratos.remove();
					facturaService.deleteFacturaByIdPedido(idPedido);
					return pedido;
				}
				else {
					pedido.setState("CANCELLED");
					double newPrice= pedido.getPrice();
					newPrice = (newPrice / 100)*10;
					pedido.setPrice(newPrice);
					facturaService.updateFactura(idPedido, pedido);
				}
			}
		}
		return null;
	}
	
	/**
	 * Método auxiliar que permite determinar si el tiempo de eliminar es menor 
	 * a 12 horas o si por el contrario no puede eliminarlo
	 * @param pedidoDate
	 * @return True si puede eliminar, de lo contrario False
	 */
	public boolean isCancelledTime(Date pedidoDate)
	{
		Date actualDate = new Date();
		if (actualDate.getTime()-pedidoDate.getTime()<TWELVEHOURS) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método auxiliar que permite determinar si un pedido debe o no contener el precio del domicilio
	 * @param pedido
	 * @return Pedido con Domicilio gratis de lo contrario el pedido queda intacto
	 */
	public Pedido deleteDelivery (Pedido pedido)
	{
		if (pedido.getPrice()>100000) {
			Iterator<Producto> iterator = pedido.getProductos().iterator();
			while (iterator.hasNext()) {
				Producto producto2 = iterator.next();
				if (producto2.getIdProducto()==0) {
					iterator.remove();
					return pedido;
				}
			}
		}
		return pedido;
	}

	/**
	 * Añade un producto a un pedido
	 * @param idCliente
	 * @param idPedido
	 * @param idProduct
	 * @return Pedido actualizado
	 */
	public Pedido addProductToPedido(int idCliente, int idPedido, int idProduct)
	{
		ProductoDAO productos = new ProductoDAO();
		Producto producto = productos.findById(idProduct);
		Pedido pedido = deleteDelivery(getPedidoById(idPedido));
		pedido.getProductos().add(producto);
		return updatePedido(idCliente, pedido, idPedido);
	}

}
