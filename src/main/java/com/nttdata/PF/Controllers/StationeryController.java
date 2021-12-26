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

import com.nttdata.PF.Persistence.Stationery;
import com.nttdata.PF.Services.StationeryDto;
import com.nttdata.PF.Services.StationeryServiceI;

/**
 * Controlador de las papelerías
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/home/stationery")
public class StationeryController implements StationeryControllerI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(StationeryController.class);

	/** Servicio de gestión de las papelerías */
	@Autowired
	StationeryServiceI stationeryService;

	/**
	 * Dirije a la pantalla principal de las papelerías
	 * 
	 * @return String
	 */
	@Override
	@GetMapping
	public String stationeryPage() {
		return "/stationery";
	}

	/**
	 * Añade o actualiza un a papelería a/de la BBDD
	 * 
	 * @param stationeryDto
	 * @return String
	 */
	@Override
	@PostMapping("/addStationery")
	public String addOrUpdateStationery(final StationeryDto stationeryDto) {

		LOGGER.info("Inicio del método del controlador addOrUpdateStationery");

		// Vista resultante.
		String viewResult = "Errors";

		// Verificación de integridad.
		if (stationeryDto != null && stationeryDto.getCifStationery() != null
				&& stationeryDto.getCifStationery().length() == 9 && stationeryDto.getNameStationery() != null) {

			if (stationeryService.findByCifStationery(stationeryDto.getCifStationery()) == null) {
				// Invocación a servicio de registro.
				Stationery stationerySaved = stationeryService.addStationery(stationeryDto);
				LOGGER.error(stationerySaved.toString());
			} else {
				// Invocación a servicio de registro.
				Stationery stationeryupdated = stationeryService.updateStationery(stationeryDto);
				LOGGER.error(stationeryupdated.toString());
			}

			viewResult = "redirect:././";
		}

		LOGGER.info("Fin del método del controlador addOrUpdateStationery");

		return viewResult;
	}

	/**
	 * Elimina una papelería de la BBDD
	 * 
	 * @param model, cifStationery
	 * @return String
	 */
	@Override
	@GetMapping("/deleteStationery/{nameStationery}")
	public String deleteStationery(Model model, @PathVariable String nameStationery) {

		LOGGER.info("Inicio del método del controlador deleteProvider");

		// Invocación a servicio de borrado
		stationeryService.deleteStationery(nameStationery);

		LOGGER.info("Fin del método del controlador deleteProvider");

		return "redirect:/home/stationery/";

	}

	/**
	 * Muestra todos las papelerías
	 * 
	 * @param model
	 * @return String
	 */
	@Override
	@GetMapping("/showAllStationeries")
	public String showAllStationeries(Model model) {

		LOGGER.info("Inicio del método del controlador showAllStationeries");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de consulta de juegos.
		final List<StationeryDto> stationeriesResult = stationeryService.showAllStationeries();

		if (stationeriesResult != null) {
			model.addAttribute("stationeries", stationeriesResult);
			viewResult = "/stationeryList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showAllStationeries");

		return viewResult;
	}

	/**
	 * Muestra una papelería consultada por su nombre
	 * 
	 * @param model, nameStationery
	 * @return
	 */
	@Override
	@PostMapping("/searchStationery")
	public String searchStationeryByName(Model model, final String nameStationery) {

		LOGGER.info("Inicio del método del controlador searchStationeryByName");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de registro.
		Stationery s = stationeryService.findByNameStationery(nameStationery);

		// Verificación de integridad.
		if (s != null) {
			model.addAttribute("stationeryData", s);
			viewResult = "/cardStationery";
		} else {
			viewResult = "notFound";
		}

		LOGGER.info("Fin del método del controlador searchStationeryByName");

		return viewResult;
	}

}
