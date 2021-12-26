package com.nttdata.PF.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.PF.Services.ClientDto;

/**
 * Interfaz asociada al controlador de los clientes
 * 
 * @author agadelao
 *
 */
public interface ClientControllerI {

	/**
	 * Dirije a la pantalla principal de los clientes
	 * 
	 * @return String
	 */
	public String clientPage();

	/**
	 * Añade o actualiza un cliente a/de la BBDD
	 * 
	 * @param clientDto
	 * @return String
	 */
	public String addOrUpdateClient(final ClientDto clientDto);

	/**
	 * Elimina un cliente de la BBDD
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	public String deleteClient(Model model, @PathVariable String dniClient);

	/**
	 * Muestra un cliente consultado por su DNI
	 * 
	 * @param model, dniClient
	 * @return
	 */
	public String searchClientByDni(Model model, final String dniClient);

	/**
	 * Muestra todos los clientes
	 * 
	 * @param model
	 * @return String
	 */
	public String showAllClients(Model model);

	/**
	 * Muestra todos los productos comprados por un cliente
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	public String showAllProductsBought(Model model, @PathVariable String dniClient);

	/**
	 * Muestra todas las papelerías donde ha comprado el cliente
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	public String showStationariesFromClient(Model model, @PathVariable String dniClient);

}
