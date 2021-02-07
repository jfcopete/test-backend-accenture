package com.test.backend.accenture.dtos;

public class Factura {
	private Integer idFactura;
	private Pedido pedido;
	private String description;
	private Double iva;
	
	public Factura(Integer idFactura, Pedido pedido) {
		super();
		this.idFactura = idFactura;
		this.pedido = pedido;
		this.description = setDescription();
		this.iva = setIva();
	}
	
	public Integer getIdFactura() {
		return idFactura;
	}
	
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String setDescription() {
		return this.description = pedido.toString();
	}
	
	public Double getIva() {
		return iva;
	}
	
	public Double setIva() {
		return this.iva = (pedido.getPrice()/100*19);
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", pedido=" + pedido + ", description=" + description + ", iva="
				+ iva + "]";
	}
	
	

}
