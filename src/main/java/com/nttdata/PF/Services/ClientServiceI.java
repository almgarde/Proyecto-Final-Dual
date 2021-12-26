package com.nttdata.PF.Services;

import java.util.List;

import com.nttdata.PF.Persistence.Client;

/**
 * Interfaz de los Servicios de gestión de los clientes
 * 
 * @author agadelao
 *
 */
public interface ClientServiceI {

	/** Añadir un cliente a la BBDD */
	public Client addClient(final ClientDto clientDto);

	/** Actualizar un cliente de la BBDD */
	public Client updateClient(final ClientDto clientDto);

	/** Eliminar un cliente de la BBDD */
	public void deleteClient(final String dniClient);

	/** Buscar un cliente por su DNI */
	public Client searchClientByDni(final String dniClient);

	/** Muestra todos los clientes almacenados en la BBDD */
	public List<ClientDto> showAllClients();

	/** Muestra todos los productos comprados por un cliente */
	public List<ProductDto> showAllProductsBought(final String dniClient);

	/** Muestra todas las papelerías donde ha comprado un cliente */
	public List<StationeryDto> showStationariesFromClient(final String dniClient);

}
