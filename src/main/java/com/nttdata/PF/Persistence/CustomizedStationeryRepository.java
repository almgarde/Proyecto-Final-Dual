package com.nttdata.PF.Persistence;

import java.util.List;

/**
 * Interfaz de persistencia con Criteria de la entidad STATIONERY
 * 
 * @author agadelao
 *
 */
public interface CustomizedStationeryRepository {

	/** Metodo que muestra las papelerías donde ha comprado el cliente */
	public List<Stationery> searchStationeryByIdClient(final Long idClient);

}
