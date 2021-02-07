package com.test.backend.accenture.dtos;

public class Producto {
	private Integer idProducto;
	private String name;
	private String description;
	private Double price;
	
	public Producto(Integer idProducto, String name, String description, Double price) {
		super();
		this.idProducto = idProducto;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", name=" + name + ", description=" + description + ", price="
				+ price + "]";
	}
	
	
}
