package com.test.backend.accenture.DAOs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.accenture.dtos.Factura;
import com.test.backend.accenture.dtos.Pedido;

@Component
public class FacturaDAO {
	private static int facturaCounter=4;

	private static List<Factura> facturas = new ArrayList<>();

	/**
	 * Instancia los primeros datos en la estructura de datos
	 */
	static {
		facturas.add(new Factura(1, new PedidoDAO().getPedidoById(1)));
		facturas.add(new Factura(2, new PedidoDAO().getPedidoById(2)));
		facturas.add(new Factura(3, new PedidoDAO().getPedidoById(3)));
		facturas.add(new Factura(4, new PedidoDAO().getPedidoById(4)));
	}

	/**
	 * Retorna las facturas registradas en la estructura de datos en formato JSON
	 * @return
	 */
	public List<Factura> getAllFacturas()
	{
		return facturas;
	}

	/**
	 * Retorna la Factura dado un ID por parametro
	 * @param idFactura
	 * @return Factura
	 */
	public Factura getFacturaById(int id)
	{
		for (Factura factura : facturas) {
			if (factura.getIdFactura()==id) {
				return factura;
			}
		}
		return null;
	}
	
	/**
	 * Añade una Factura a la estructura de datos
	 * @param factura
	 * @return Factura añadida
	 */
	public Factura addFactura(Factura factura)
	{
		if (factura.getIdFactura()==null) {
			factura.setIdFactura(++facturaCounter);
		}
		facturas.add(factura);
		return factura;
	}

	/**
	 * Elimina una factura y Agrega una nueva en su lugar con los datos correspondientes a una
	 * actualización de pedido
	 * @param idPedido
	 * @param pedido
	 */
	public void updateFactura (int idPedido ,Pedido pedido)
	{
		for (int i = 0; i < facturas.size(); i++) {
			if (facturas.get(i).getPedido().getIdPedido()==idPedido) {
				Factura nueva = addFactura(new Factura(null, pedido));
				facturas.remove(i);
				facturas.set(i, nueva);
			}
		}
	}

	/**
	 * elimina una Factura dado un iDFactura
	 * @param id
	 * @return
	 */
	public Factura deleteFacturaByIdPedido(int idPedido)
	{
		Iterator<Factura> iterator = facturas.iterator();
		while (iterator.hasNext()) {
			Factura factura = (Factura) iterator.next();
			if (factura.getPedido().getIdPedido()==idPedido) {
				iterator.remove();
				return factura;
			}
		}
		return null;
	}

}
