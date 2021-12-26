package com.nttdata.PF.Services;

import java.util.List;

import com.nttdata.PF.Persistence.Provider;
import com.nttdata.PF.Persistence.Stationery;

/**
 * Interfaz de los Servicios de gestión de las papelerías
 * 
 * @author agadelao
 *
 */
public interface StationeryServiceI {

	/** Añadir una papelería a la BBDD */
	public Stationery addStationery(final StationeryDto stationeryDto);

	/** Actualizar una papelería de la BBDD */
	public Stationery updateStationery(final StationeryDto stationeryDto);

	/** Eliminar una papelería de la BBDD */
	public void deleteStationery(final String nameStationery);

	/** Muestra todos las papelerías almacenadas en la BBDD */
	public List<StationeryDto> showAllStationeries();

	/** Buscar una papelería por su nombre */
	public Stationery findByNameStationery(final String nameStationery);

	/** Buscar una papelería por su CIF */
	public Stationery findByCifStationery(final String cifStationery);

}
