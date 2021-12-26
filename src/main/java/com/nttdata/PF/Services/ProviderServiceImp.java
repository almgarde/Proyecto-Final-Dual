package com.nttdata.PF.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.PF.Persistence.Provider;
import com.nttdata.PF.Persistence.ProviderRepositoryI;

/**
 * Servicios de gestión de los PROVEEDORES
 * 
 * @author agadelao
 *
 */
@Service
public class ProviderServiceImp implements ProviderServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImp.class);

	/** Métodos de gestión de los proveedores */
	@Autowired
	ProviderRepositoryI providerRep;

	/**
	 * Añadir un proveedor a la BBDD
	 * 
	 * @return ProviderDto
	 */
	@Override
	public Provider addProvider(final ProviderDto providerDto) {

		LOGGER.info("Inicio del servicio addProvider");

		Provider providerSaved = null;

		if (providerDto != null) {

			// Condicion de integridad
			if (providerDto.getCifProvider().length() == 9) {

				// Volcado de DTO a entidad
				Provider p = new Provider();
				p.setCifProvider(providerDto.getCifProvider());
				p.setNameProvider(providerDto.getNameProvider());

				// Guardado
				providerSaved = providerRep.save(p);
				LOGGER.debug(providerSaved.toString());
				LOGGER.info("Proveedor almacenado correctamente");

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El providerDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio addProvider");

		return providerSaved;
	}

	/**
	 * Actualizar un proveedor de la BBDD
	 * 
	 * @return ProviderDto
	 */
	@Override
	public Provider updateProvider(final ProviderDto providerDto) {

		LOGGER.info("Inicio del servicio updateProvider");

		Provider providerUpdated = null;

		if (providerDto != null) {

			if (providerDto.getCifProvider().length() == 9) {

				// Consulta a la BBDD
				Provider providerBbdd = providerRep.findByCifProvider(providerDto.getCifProvider());
				LOGGER.debug(providerBbdd.toString());

				// Actualizado del proveedor
				providerBbdd.setCifProvider(providerDto.getCifProvider());
				providerBbdd.setNameProvider(providerDto.getNameProvider());

				// Guardado
				providerUpdated = providerRep.save(providerBbdd);
				LOGGER.debug(providerUpdated.toString());
				LOGGER.info("Proveedor actualizado correctamente");

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El providerDto no puede ser nulo");
		}

		LOGGER.info("Fin del servicio updateProvider");

		return providerUpdated;
	}

	/**
	 * Eliminar un proveedor de la BBDD
	 * 
	 * @return ProviderDto
	 */
	@Override
	public void deleteProvider(final String cifProvider) {

		LOGGER.info("Inicio del servicio deleteProvider");

		if (cifProvider != null) {

			// Volcado de DTO a Entidad
			Provider p = providerRep.findByCifProvider(cifProvider);
			LOGGER.debug(p.toString());

			if (p != null) {
				// Borrado
				providerRep.delete(p);
			} else {
				LOGGER.error("No se ha encontrado el proveedor");
			}

		} else {
			LOGGER.error("El cifProvider no puede ser nulo");
		}

		LOGGER.info("Fin del servicio deleteProvider");
	}

	/**
	 * Muestra todos los proveedores almacenados en la BBDD
	 * 
	 * @return List<ProviderDto>
	 */
	@Override
	public List<ProviderDto> showAllProviders() {

		LOGGER.info("Inicio del servicio showAllProviders");

		// Resultado
		List<ProviderDto> providersDto = new ArrayList<ProviderDto>();

		// Consulta a la base de datos
		List<Provider> providers = providerRep.findAll();

		// Condición de integridad
		if (providers != null && providers.size() > 0) {

			// Volcado de Entidad a Dto
			for (Provider p : providers) {
				ProviderDto providerDto = new ProviderDto();
				providerDto.setCifProvider(p.getCifProvider());
				providerDto.setNameProvider(p.getNameProvider());
				providersDto.add(providerDto);
			}

		} else {
			LOGGER.error("No hay proveedores en la BBDD");
		}

		LOGGER.info("Fin del servicio showAllProviders");

		return providersDto;
	}

	/**
	 * Buscar un proveedor por su CIF
	 * 
	 * @return Provider
	 */
	@Override
	public Provider findByCifProvider(final String cifProvider) {

		LOGGER.info("Inicio del servicio findByCifProvider");

		// Resultado
		Provider p = null;

		if (cifProvider != null) {

			if (cifProvider.length() == 9) {

				// Consulta a la base de datos
				p = providerRep.findByCifProvider(cifProvider);

				// Volcado de Entidad a DTO
				if (p != null) {
					ProviderDto providerDto = new ProviderDto();
					providerDto.setCifProvider(p.getCifProvider());
					providerDto.setNameProvider(p.getNameProvider());
				} else {
					LOGGER.error("Este proveedor no se encuentra en la BBDD");
				}

			} else {
				LOGGER.error("El CIF debe tener 9 dígitos");
			}

		} else {
			LOGGER.error("El cifProvider no puede ser nulo");
		}

		LOGGER.info("Fin del servicio findByCifProvider");

		return p;
	}

}
