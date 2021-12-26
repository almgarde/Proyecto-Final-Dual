package com.nttdata.PF.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.PF.Persistence.Client;
import com.nttdata.PF.Persistence.ClientRepositoryI;
import com.nttdata.PF.Persistence.Product;
import com.nttdata.PF.Persistence.ProductRepositoryI;
import com.nttdata.PF.Persistence.Stationery;
import com.nttdata.PF.Persistence.StationeryRepositoryI;

/**
 * Servicios de gestión del CLIENTE
 * 
 * @author agadelao
 *
 */
@Service
public class ClientServiceImpl implements ClientServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

	/** Métodos de gestión del cliente */
	@Autowired
	ClientRepositoryI clientRep;

	/** Métodos de gestión de los productos */
	@Autowired
	ProductRepositoryI productRep;

	/** Métodos de gestión de las papelerías */
	@Autowired
	StationeryRepositoryI stationeryRep;

	/**
	 * Añadir un cliente a/de la BBDD
	 * 
	 * @return ClientDto
	 */

	@Override
	public Client addClient(final ClientDto clientDto) {

		LOGGER.info("Inicio del servicio addClient");

		Client clientSaved = null;

		if (clientDto != null) {

			// Verificación de integridad
			if (clientDto.getDniClient().length() == 9) {

				// Volcado de DTO a entidad
				Client c = new Client();
				c.setDniClient(clientDto.getDniClient());
				c.setNameClient(clientDto.getNameClient());
				c.setSurnameClient(clientDto.getSurnameClient());

				// Guardado
				clientSaved = clientRep.save(c);
				LOGGER.debug(clientSaved.toString());
				LOGGER.info("Cliente almacenado correctamente");

			} else {
				LOGGER.error("El DNI debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El clientDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio addClient");

		return clientSaved;

	}

	/**
	 * Actualizar un cliente de la BBDD
	 * 
	 * @return ClientDto
	 */
	@Override
	public Client updateClient(final ClientDto clientDto) {

		LOGGER.info("Inicio del servicio updateClient");

		Client clientUpdated = null;

		if (clientDto != null) {

			// Verificación de integridad
			if (clientDto.getDniClient().length() == 9) {

				// Consulta a la BBDD
				Client clientBbdd = clientRep.findByDniClient(clientDto.getDniClient());
				LOGGER.debug(clientBbdd.toString());

				// Actualizado del cliente
				clientBbdd.setNameClient(clientDto.getNameClient());
				clientBbdd.setSurnameClient(clientDto.getSurnameClient());

				// Guardado
				clientUpdated = clientRep.save(clientBbdd);
				LOGGER.debug(clientUpdated.toString());
				LOGGER.info("Cliente actualizado correctamente");

			} else {
				LOGGER.error("El dni debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El clientDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio updateClient");

		return clientUpdated;

	}

	/**
	 * Eliminar un cliente de la BBDD
	 * 
	 * @return ClientDto
	 */
	@Override
	public void deleteClient(final String dniClient) {

		LOGGER.info("Inicio del servicio deleteClient");

		if (dniClient != null) {

			// Volcado de DTO a Entidad
			Client c = clientRep.findByDniClient(dniClient);
			LOGGER.debug(c.toString());

			if (c != null) {
				// Borrado
				clientRep.delete(c);
			} else {
				LOGGER.error("No se ha encontrado el cliente");
			}

		} else {
			LOGGER.error("El dniClient no puede ser nulo");
		}

		LOGGER.info("Fin del servicio deleteClient");
	}

	/**
	 * Buscar un cliente por su DNI
	 * 
	 * @return Client
	 */
	@Override
	public Client searchClientByDni(final String dniClient) {

		LOGGER.info("Inicio del servicio searchClientByDni");

		// Resultado
		Client c = null;

		if (dniClient != null) {

			// Verificación de integridad
			if (dniClient.length() == 9) {

				// Consulta a la base de datos
				c = clientRep.findByDniClient(dniClient);
				// LOGGER.debug(c.toString());

				// Volcado de Entidad a DTO
				if (c != null) {
					ClientDto clientDto = new ClientDto();
					clientDto.setDniClient(c.getDniClient());
					clientDto.setNameClient(c.getNameClient());
					clientDto.setSurnameClient(c.getSurnameClient());
				} else {
					LOGGER.error("Este cliente no se encuentra en la BBDD");
				}

			} else {
				LOGGER.error("El dni debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El dniClient no puede ser nulo");
		}

		LOGGER.info("Fin del servicio searchClientByDni");

		return c;
	}

	/**
	 * Muestra todos los clientes almacenados en la BBDD
	 * 
	 * @return List<ClientDto>
	 */
	@Override
	public List<ClientDto> showAllClients() {

		LOGGER.info("Inicio del servicio showAllClients");

		// Resultado
		List<ClientDto> clientsDto = new ArrayList<ClientDto>();

		// Consulta a la base de datos
		List<Client> clients = clientRep.findAll();

		if (clients != null && clients.size() > 0) {

			// Volcado de Entidad a Dto
			for (Client c : clients) {
				ClientDto clientDto = new ClientDto();
				clientDto.setDniClient(c.getDniClient());
				clientDto.setNameClient(c.getNameClient());
				clientDto.setSurnameClient(c.getSurnameClient());
				clientsDto.add(clientDto);
			}

		} else {
			LOGGER.error("No hay clientes en la BBDD");
		}

		LOGGER.info("Fin del servicio showAllClients");
		return clientsDto;
	}

	/**
	 * Muestra todos los productos comprados por un cliente
	 * 
	 * @return List<ProductDto>
	 */
	@Override
	public List<ProductDto> showAllProductsBought(final String dniClient) {

		LOGGER.info("Inicio del servicio showAllProductsBought");

		// Resultado
		List<ProductDto> productsDto = new ArrayList<ProductDto>();

		// Consulta a la BBDD del cliente
		Client c = searchClientByDni(dniClient);
		LOGGER.debug(c.toString());

		if (c != null) {

			// Consulta a la base de datos
			List<Product> products = productRep.productsClient(c.getIdClient());

			if (products != null && products.size() > 0L) {

				// Volcado de la Entidad a DTO
				for (Product p : products) {
					ProductDto productDto = new ProductDto();
					productDto.setNameProduct(p.getNameProduct());
					String priceString = String.valueOf(p.getPriceProduct());
					productDto.setPriceProduct(priceString);
					productsDto.add(productDto);
				}

			} else {
				LOGGER.error("El cliente no ha comprado ningún producto");
			}

		} else {
			LOGGER.error("Este cliente no se encuentra en la BBDD");
		}

		LOGGER.info("Fin del servicio showAllProductsBought");

		return productsDto;
	}

	/**
	 * Muestra todas las papelerías en las que ha comprado un cliente
	 * 
	 * @return List<StationeryDto>
	 */
	@Override
	public List<StationeryDto> showStationariesFromClient(final String dniClient) {

		LOGGER.info("Inicio del servicio showStationariesFromClient");

		// Resultado
		List<StationeryDto> stationeriesDto = new ArrayList<StationeryDto>();

		// Consulta a la BBDD del cliente
		Client c = searchClientByDni(dniClient);
		LOGGER.debug(c.toString());

		if (c != null) {

			// Consulta a la base de datos
			List<Stationery> stationeries = stationeryRep.searchStationeryByIdClient(c.getIdClient());

			if (stationeries != null && stationeries.size() > 0L) {

				// Volcado de la Entidad a DTO
				for (Stationery s : stationeries) {
					StationeryDto stationeryDto = new StationeryDto();
					stationeryDto.setCifStationery(s.getCifStationery());
					stationeryDto.setNameStationery(s.getNameStationery());
					stationeriesDto.add(stationeryDto);
				}

			} else {
				LOGGER.error("El cliente no ha visitado ninguna papelería");
			}

		} else {
			LOGGER.error("Este cliente no se encuentra en la BBDD");
		}

		LOGGER.info("Fin del servicio showStationariesFromClient");

		return stationeriesDto;
	}

}
