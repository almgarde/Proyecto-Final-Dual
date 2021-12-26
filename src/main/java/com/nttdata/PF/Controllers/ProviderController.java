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

import com.nttdata.PF.Persistence.Provider;
import com.nttdata.PF.Services.ProviderDto;
import com.nttdata.PF.Services.ProviderServiceI;

/**
 * Controlador de los proveedores
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/home/provider")
public class ProviderController implements ProviderControllerI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

	/** Servicio de gestión de los proveedores */
	@Autowired
	ProviderServiceI providerService;

	/**
	 * Dirije a la pantalla principal de los proveedores
	 * 
	 * @return String
	 */
	@Override
	@GetMapping
	public String providerPage() {
		return "/provider";
	}

	/**
	 * Añade o actualiza un proveedor a/de la BBDD
	 * 
	 * @param providerDto
	 * @return String
	 */
	@Override
	@PostMapping("/addProvider")
	public String addOrUpdateProvider(final ProviderDto providerDto) {

		LOGGER.info("Inicio del método del controlador addOrUpdateProvider");

		// Vista resultante.
		String viewResult = "Errors";

		// Verificación de integridad.
		if (providerDto != null && providerDto.getCifProvider() != null && providerDto.getCifProvider().length() == 9
				&& providerDto.getNameProvider() != null) {

			if (providerService.findByCifProvider(providerDto.getCifProvider()) == null) {
				// Invocación a servicio de registro.
				Provider providerSaved = providerService.addProvider(providerDto);
				LOGGER.error(providerSaved.toString());
			} else {
				// Invocación a servicio de registro.
				Provider providerupdated = providerService.updateProvider(providerDto);
				LOGGER.error(providerupdated.toString());
			}

			viewResult = "redirect:././";
		}

		LOGGER.info("Fin del método del controlador addOrUpdateProvider");

		return viewResult;
	}

	/**
	 * Elimina un proveedor de la BBDD
	 * 
	 * @param model, cifProvider
	 * @return String
	 */
	@Override
	@GetMapping("/deleteProvider/{cifProvider}")
	public String deleteProvider(Model model, @PathVariable String cifProvider) {

		LOGGER.info("Inicio del método del controlador deleteProvider");

		// Invocación a servicio de borrado
		providerService.deleteProvider(cifProvider);

		LOGGER.info("Fin del método del controlador deleteProvider");

		return "redirect:/home/provider/";

	}

	/**
	 * Muestra todos los proveedores
	 * 
	 * @param model
	 * @return String
	 */
	@Override
	@GetMapping("/showAllProviders")
	public String showAllProviders(Model model) {

		LOGGER.info("Inicio del método del controlador showAllProviders");

		// Vista resultante.
		String viewResult = "Errors";

		// Invocación a servicio de consulta de juegos.
		final List<ProviderDto> providersResult = providerService.showAllProviders();

		if (providersResult != null) {
			model.addAttribute("providers", providersResult);
			viewResult = "/providerList";
		} else {
			LOGGER.error("Se ha obtenido una lista nula");
		}

		LOGGER.info("Fin del método del controlador showAllProviders");

		return viewResult;
	}

	/**
	 * Muestra un proveedor consultado por su CIF
	 * 
	 * @param model, cifProvider
	 * @return
	 */
	@Override
	@PostMapping("/searchProvider")
	public String searchProviderByCif(Model model, final String cifProvider) {

		LOGGER.info("Inicio del método del controlador searchProviderByCif");

		// Vista resultante.
		String viewResult = "Errors";

		if (cifProvider.length() == 9) {

			// Invocación a servicio de registro.
			Provider p = providerService.findByCifProvider(cifProvider);

			// Verificación de integridad.
			if (p != null) {
				model.addAttribute("providerData", p);
				viewResult = "/cardProvider";
			} else {
				viewResult = "notFound";
			}

		} else {
			System.out.println("El CIF debe tener 9 dígitos");
		}

		LOGGER.info("Fin del método del controlador searchProviderByCif");

		return viewResult;
	}

}
