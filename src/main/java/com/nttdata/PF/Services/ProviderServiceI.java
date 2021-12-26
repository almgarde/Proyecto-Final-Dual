package com.nttdata.PF.Services;

import java.util.List;

import com.nttdata.PF.Persistence.Product;
import com.nttdata.PF.Persistence.Provider;

/**
 * Interfaz de los Servicios de gestión de los proveedores
 * 
 * @author agadelao
 *
 */
public interface ProviderServiceI {

	/** Añadir un proveedor a la BBDD */
	public Provider addProvider(final ProviderDto providerDto);

	/** Actualizar un proveedor de la BBDD */
	public Provider updateProvider(final ProviderDto providerDto);

	/** Eliminar un proveedor de la BBDD */
	public void deleteProvider(final String cifProvider);

	/** Muestra todos los proveedores almacenados en la BBDD */
	public List<ProviderDto> showAllProviders();

	/** Buscar un proveedor por su CIF */
	public Provider findByCifProvider(final String cifProvider);

}
