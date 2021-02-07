package com.test.backend.accenture.dtos;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	private Integer idPedido;
	private List<Producto> productos;
	private Double price;
	private Integer idCliente;
	private Date date;
	private String state;
	
	
	public Pedido(Integer idPedido, List<Producto> productos, Integer idCliente, Date date,
			String state) {
		super();
		this.idPedido = idPedido;
		this.productos = productos;
		this.idCliente = idCliente;
		this.date = date;
		this.state = state;
		this.price= calculatePrice(productos);
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", productos=" + productos + ", price=" + price + ", idCliente="
				+ idCliente + ", date=" + date + ", state=" + state + "]";
	}
	
	public Integer getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	
	public Double calculatePrice(List<Producto> productos)
	{
		double precioProductos=0;
		for (Producto producto : productos) {
			precioProductos+=producto.getPrice();
		}
		price=precioProductos;
		return price;
	}
	

}
