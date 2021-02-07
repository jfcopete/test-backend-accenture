package com.test.backend.accenture.DAOs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.accenture.dtos.Cliente;

@Component
public class ClienteDAO {

	private static int userCounter=4;

	private static List<Cliente> clientes = new ArrayList<>();
	static {
		clientes.add(new Cliente(1, "Juan Fernando", "Calle 80 #96-43", "1233852741"));
		clientes.add(new Cliente(2, "Luis Nicolas", "Calle 25 #13-7", "123456789"));
		clientes.add(new Cliente(3, "Andres Kevin", "carrera 11# 14-08", "12345"));
		clientes.add(new Cliente(4, "Diego Valita", "Calle 95A #22-85", "12345678"));
	}

	/**
	 * Retorna los clientes registrados
	 * @return Lista de clientes
	 */
	public List<Cliente> findAll()
	{
		return clientes;
	}

	/**
	 * * Registra un cliente en la estructura de datos
	 * @param cliente
	 * @return Cleinte Registrado
	 */
	public Cliente addCliente(Cliente cliente)
	{
		if (cliente.getIdCliente().equals(null)) {
			cliente.setIdCliente(++userCounter);
		}
		clientes.add(cliente);
		return cliente;
	}

	/**
	 * Retorna el cliente con id indicaddo por parametro
	 * @param id
	 * @return Cliente
	 */
	public Cliente findById(int id)
	{
		for (Cliente cliente : clientes) {
			if (cliente.getIdCliente()==id) {
				return cliente;
			}
		}
		return null;
	}
	
	/**
	 * Elimina un cliente dado el id por PathParam
	 * @param id
	 */
	public Cliente deleteCliente(int id)
	{
		Iterator<Cliente> iterator = clientes.iterator();
		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();
			if (cliente.getIdCliente()==id) {
				iterator.remove();
				return cliente;
			}
		}
		return null;
	}

}
