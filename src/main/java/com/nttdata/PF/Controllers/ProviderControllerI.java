package com.nttdata.PF.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.PF.Services.ClientDto;
import com.nttdata.PF.Services.ProviderDto;

/**
 * Interfaz asociada al controlador de los proveedores
 * 
 * @author agadelao
 *
 */
public interface ProviderControllerI {

	/**
	 * Dirije a la pantalla principal de los proveedores
	 * 
	 * @return String
	 */
	public String providerPage();

	/**
	 * Añade o actualiza un proveedor a/de la BBDD
	 * 
	 * @param providerDto
	 * @return String
	 */
	public String addOrUpdateProvider(final ProviderDto providerDto);

	/**
	 * Elimina un proveedor de la BBDD
	 * 
	 * @param model, cifProvider
	 * @return String
	 */
	public String deleteProvider(Model model, @PathVariable String cifProvider);

	/**
	 * Muestra todos los proveedores
	 * 
	 * @param model
	 * @return String
	 */
	public String showAllProviders(Model model);

	/**
	 * Muestra un proveedor consultado por su CIF
	 * 
	 * @param model, cifProvider
	 * @return
	 */
	public String searchProviderByCif(Model model, final String cifProvider);

}
