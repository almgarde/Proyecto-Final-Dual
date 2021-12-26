package com.nttdata.PF.Persistence;

import java.util.List;

/**
 * Interfaz de persistencia con Criteria de la entidad PRODUCT
 * 
 * @author agadelao
 *
 */
public interface CustomizedProductRepository {

	/** Método para listar los productos comprados por el cliente */
	public List<Product> productsClient(final Long idClient);

}
