package com.nttdata.PF.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.PF.Persistence.Client;
import com.nttdata.PF.Services.ClientDto;
import com.nttdata.PF.Services.ClientServiceI;
import com.nttdata.PF.Services.ProductDto;
import com.nttdata.PF.Services.StationeryDto;
import com.nttdata.PF.Services.StationeryServiceImpl;

/**
 * Controlador de los clientes
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/home/client")
public class ClientController implements ClientControllerI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/** Servicio de gestión de los clientes */
	@Autowired
	ClientServiceI clientService;

	/**
	 * Dirije a la pantalla principal de los clientes
	 * 
	 * @return String
	 */
	@Override
	@GetMapping
	public String clientPage() {
		return "/client";
	}

	/**
	 * Añade o actualiza un cliente a/de la BBDD
	 * 
	 * @param clientDto
	 * @return String
	 */
	@Override
	@PostMapping("/addClient")
	public String addOrUpdateClient(final ClientDto clientDto) {

		LOGGER.info("Inicio del método del controlador addOrUpdateClient");

		// Vista resultante.
		String viewResult = "Errors";

		// Verificación de integridad.
		if (clientDto != null && clientDto.getDniClient() != null && clientDto.getDniClient().length() == 9
				&& clientDto.getNameClient() != null && clientDto.getSurnameClient() != null) {

			if (clientService.searchClientByDni(clientDto.getDniClient()) == null) {
				// Invocación a servicio de registro.
				Client clientSaved = clientService.addClient(clientDto);
				LOGGER.debug(clientSaved.toString());
			} else {
				// Invocación a servicio de registro.
				Client clientUpdated = clientService.updateClient(clientDto);
				LOGGER.debug(clientUpdated.toString());
			}

			viewResult = "redirect:././";
		}

		LOGGER.info("Fin del método del controlador addOrUpdateClient");

		return viewResult;
	}

	/**
	 * Elimina un cliente de la BBDD
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	@Override
	@GetMapping("/deleteClient/{dniClient}")
	public String deleteClient(Model model, @PathVariable String dniClient) {

		LOGGER.info("Inicio del método del controlador deleteClient");

		// Invocación a servicio de borrado
		clientService.deleteClient(dniClient);

		LOGGER.info("Fin del método del controlador deleteClient");

		return "redirect:/home/client/";
	}

	/**
	 * Muestra un cliente consultado por su DNI
	 * 
	 * @param model, dniClient
	 * @return
	 */
	@Override
	@PostMapping("/searchClient")
	public String searchClientByDni(Model model, final String dniClient) {

		LOGGER.info("Inicio del método del controlador searchClientByDni");

		// Vista resultante.
		String viewResult = "Errors";

		if (dniClient.length() == 9) {

			// Invocación a servicio de registro.
			Client c = clientService.searchClientByDni(dniClient);

			// Verificación de integridad.
			if (c != null) {
				model.addAttribute("clientData", c);
				viewResult = "/cardClient";
			} else {
				viewResult = "notFound";
			}
		}

		LOGGER.info("Fin del método del controlador searchClientByDni");

		return viewResult;
	}

	/**
	 * Muestra todos los clientes
	 * 
	 * @param model
	 * @return String
	 */
	@Override
	@GetMapping("/showAllClients")
	public String showAllClients(Model model) {

		LOGGER.info("Inicio del método del controlador showAllClients");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de consulta de juegos.
		final List<ClientDto> clientsResult = clientService.showAllClients();

		if (clientsResult != null) {
			model.addAttribute("clients", clientsResult);
			viewResult = "/clientList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showAllClients");

		return viewResult;
	}

	/**
	 * Muestra todos los productos comprados por un cliente
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	@Override
	@GetMapping("/productsClient/{dniClient}")
	public String showAllProductsBought(Model model, @PathVariable String dniClient) {

		LOGGER.info("Inicio del método del controlador showAllProductsBought");

		// Vista resultante.
		String viewResult = "Errors";

		// Consulta de la base de datos

		final List<ProductDto> productsResult = clientService.showAllProductsBought(dniClient);
		final int numProducts = productsResult.size() + 1;

		if (productsResult != null) {
			model.addAttribute("dniClient", dniClient);
			model.addAttribute("numProducts", numProducts);
			model.addAttribute("productsClient", productsResult);
			viewResult = "/productsClientList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showAllProductsBought");

		return viewResult;
	}

	/**
	 * Muestra todas las papelerías donde ha comprado el cliente
	 * 
	 * @param model, dniClient
	 * @return String
	 */
	@Override
	@GetMapping("/stationeriesClient/{dniClient}")
	public String showStationariesFromClient(Model model, @PathVariable String dniClient) {

		LOGGER.info("Inicio del método del controlador showStationariesFromClient");

		// Vista resultante.
		String viewResult = "Errors";

		// Consulta de la base de datos

		final List<StationeryDto> stationeriesResult = clientService.showStationariesFromClient(dniClient);
		final int numStationeries = stationeriesResult.size() + 1;

		if (stationeriesResult != null) {
			model.addAttribute("dniClient", dniClient);
			model.addAttribute("numStationeries", numStationeries);
			model.addAttribute("stationeriesClient", stationeriesResult);
			viewResult = "/stationeriesClientList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showStationariesFromClient");

		return viewResult;
	}

}
