package com.test.backend.accenture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.backend.accenture.DAOs.FacturaDAO;
import com.test.backend.accenture.dtos.Factura;

/**
 * Servicio REST 
 * @author Juan fernando Copete Mutis
 *
 */
@RestController
public class FacturaService {
	
	/**
	 * Permite la instanciación automatica del DAO
	 */
	@Autowired
	private FacturaDAO facturaService;
	
	/**
	 * Retorna las facturas registradas en la estructura de datos en formato JSON
	 * @return
	 */
	@GetMapping("/facturas")
	public List<Factura> getAllFacturas()
	{
		return facturaService.getAllFacturas();
	}
	
	/**
	 * Retorna la Factura dado un ID por parametro
	 * @param idFactura
	 * @return Factura
	 */
	@GetMapping("/facturas/{idFactura}")
	public Factura getFacturaById(@PathVariable int idFactura)
	{
		return facturaService.getFacturaById(idFactura);
	}
	
}
