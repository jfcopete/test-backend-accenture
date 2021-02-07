package com.test.backend.accenture.dtos;

public class Cliente {
	private Integer idCliente;
	private String name;
	private String address;
	private String idNumber;
	
	public Cliente(Integer idCliente, String name, String address, String idNumber) {
		super();
		this.idCliente = idCliente;
		this.name = name;
		this.address = address;
		this.idNumber = idNumber;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", name=" + name + ", address=" + address + ", idNumber=" + idNumber
				+ "]";
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	
}
