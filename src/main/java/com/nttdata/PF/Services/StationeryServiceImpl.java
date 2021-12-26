package com.nttdata.PF.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.PF.Persistence.Client;
import com.nttdata.PF.Persistence.Provider;
import com.nttdata.PF.Persistence.Stationery;
import com.nttdata.PF.Persistence.StationeryRepositoryI;

/**
 * Servicios de gestión de las PAPELERÍAS
 * 
 * @author agadelao
 *
 */
@Service
public class StationeryServiceImpl implements StationeryServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(StationeryServiceImpl.class);

	/** Métodos de gestión de las papelerías */
	@Autowired
	StationeryRepositoryI stationeryRep;

	/**
	 * Añadir una papelería a la BBDD
	 * 
	 * @return StationeryDto
	 */
	@Override
	public Stationery addStationery(final StationeryDto stationeryDto) {

		LOGGER.info("Inicio del servicio addStationery");

		Stationery stationerySaved = null;

		if (stationeryDto != null) {

			// Condición de integridad
			if (stationeryDto.getCifStationery().length() == 9) {

				// Volcado de DTO a entidad
				Stationery s = new Stationery();
				s.setCifStationery(stationeryDto.getCifStationery());
				s.setNameStationery(stationeryDto.getNameStationery());

				// Guardado
				stationerySaved = stationeryRep.save(s);
				LOGGER.debug(stationerySaved.toString());
				LOGGER.info("Papelería almacenada correctamente");

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El stationeryDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio addStationery");

		return stationerySaved;
	}

	/**
	 * Añadir o actualizar una papelería a/de la BBDD
	 * 
	 * @return StationeryDto
	 */
	@Override
	public Stationery updateStationery(final StationeryDto stationeryDto) {

		LOGGER.info("Inicio del servicio updateStationery");

		Stationery stationeryUpdated = null;

		if (stationeryDto != null) {

			// Condición de integridad
			if (stationeryDto.getCifStationery().length() == 9) {

				// Consulta a la BBDD
				Stationery stationeryBbdd = stationeryRep.findByCifStationery(stationeryDto.getCifStationery());
				LOGGER.debug(stationeryBbdd.toString());

				// Actualizado de la papelería
				stationeryBbdd.setNameStationery(stationeryDto.getNameStationery());

				// Guardado
				stationeryUpdated = stationeryRep.save(stationeryBbdd);
				LOGGER.debug(stationeryUpdated.toString());
				LOGGER.info("Papelería actualizada correctamente");

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El stationeryDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio updateStationery");

		return stationeryUpdated;
	}

	/**
	 * Eliminar una papelería de la BBDD
	 * 
	 * @return StationeryDto
	 */
	@Override
	public void deleteStationery(final String nameStationery) {

		LOGGER.info("Inicio del servicio deleteStationery");

		if (nameStationery != null) {

			// Volcado de DTO a Entidad
			Stationery s = stationeryRep.findByNameStationery(nameStationery);
			LOGGER.debug(s.toString());

			if (s != null) {
				// Borrado
				stationeryRep.delete(s);
			} else {
				LOGGER.error("No se ha encontrado la papelería");
			}

		} else {
			LOGGER.error("El nameStationery no puede ser nulo");
		}

		LOGGER.info("Fin del servicio deleteStationery");
	}

	/**
	 * Muestra todos las papelerías almacenadas en la BBDD
	 * 
	 * @return List<StationeryDto>
	 */
	@Override
	public List<StationeryDto> showAllStationeries() {

		LOGGER.info("Inicio del servicio showAllStationeries");

		// Resultado
		List<StationeryDto> stationeriesDto = new ArrayList<StationeryDto>();

		// Consulta a la base de datos
		List<Stationery> stationeries = stationeryRep.findAll();

		// Condición de integridad
		if (stationeries != null && stationeries.size() > 0) {

			// Volcado de Entidad a Dto
			for (Stationery s : stationeries) {
				StationeryDto stationeryDto = new StationeryDto();
				stationeryDto.setCifStationery(s.getCifStationery());
				stationeryDto.setNameStationery(s.getNameStationery());
				stationeriesDto.add(stationeryDto);
			}

		} else {
			LOGGER.error("No hay papelerías en la BBDD");
		}

		LOGGER.info("Fin del servicio showAllStationeries");

		return stationeriesDto;
	}

	/**
	 * Buscar una papelería por su nombre
	 * 
	 * @return Stationery
	 */
	@Override
	public Stationery findByNameStationery(final String nameStationery) {

		LOGGER.info("Inicio del servicio findByNameStationery");

		// Resultado
		Stationery s = null;

		if (nameStationery != null) {

			// Consulta a la base de datos
			s = stationeryRep.findByNameStationery(nameStationery);

			// Volcado de Entidad a DTO
			if (s != null) {
				StationeryDto stationeryDto = new StationeryDto();
				stationeryDto.setCifStationery(s.getCifStationery());
				stationeryDto.setNameStationery(s.getNameStationery());
			} else {
				LOGGER.error("Esta papelería no se encuentra en la BBDD");
			}

		} else {
			LOGGER.error("El nameStationery no puede ser nulo");
		}

		LOGGER.info("Fin del servicio findByNameStationery");

		return s;
	}

	/**
	 * Buscar una papelería por su CIF
	 * 
	 * @return Stationery
	 */
	@Override
	public Stationery findByCifStationery(final String cifStationery) {

		LOGGER.info("Inicio del servicio findByCifStationery");

		// Resultado
		Stationery s = null;

		if (cifStationery != null) {

			// Condición de integridad
			if (cifStationery.length() == 9) {

				// Consulta a la base de datos
				s = stationeryRep.findByCifStationery(cifStationery);

				// Volcado de Entidad a DTO
				if (s != null) {
					StationeryDto stationeryDto = new StationeryDto();
					stationeryDto.setCifStationery(s.getCifStationery());
					stationeryDto.setNameStationery(s.getNameStationery());
				} else {
					LOGGER.error("Esta papelería no se encuentra en la BBDD");
				}

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El cifStationery no puede ser nulo");
		}

		LOGGER.info("Fin del servicio findByCifStationery");

		return s;
	}

}
