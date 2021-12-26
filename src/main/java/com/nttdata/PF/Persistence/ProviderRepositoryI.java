package com.nttdata.PF.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Persistencia Proveedor
 * 
 * @author agadelao
 *
 */
@Repository
public interface ProviderRepositoryI extends JpaRepository<Provider, Long> {

	/** Método para buscar un proveedor por su CIF */
	public Provider findByCifProvider(final String cifProvider);

}
