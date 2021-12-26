package com.nttdata.PF.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.PF.Services.StationeryDto;

/**
 * Interfaz asociada al controlador de las papelerías
 * 
 * @author agadelao
 *
 */
public interface StationeryControllerI {

	/**
	 * Dirije a la pantalla principal de las papelerías
	 * 
	 * @return String
	 */
	public String stationeryPage();

	/**
	 * Añade o actualiza un a papelería a/de la BBDD
	 * 
	 * @param stationeryDto
	 * @return String
	 */
	public String addOrUpdateStationery(final StationeryDto stationeryDto);

	/**
	 * Elimina una papelería de la BBDD
	 * 
	 * @param model, cifStationery
	 * @return String
	 */
	public String deleteStationery(Model model, @PathVariable String cifStationery);

	/**
	 * Muestra todos las papelerías
	 * 
	 * @param model
	 * @return String
	 */
	public String showAllStationeries(Model model);

	/**
	 * Muestra una papelería consultada por su nombre
	 * 
	 * @param model, nameStationery
	 * @return
	 */
	public String searchStationeryByName(Model model, final String nameStationery);

}
