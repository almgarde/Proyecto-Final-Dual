package com.nttdata.PF.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de persistencia de la entidad PRODUCT
 * 
 * @author agadelao
 *
 */
@Repository
public interface ProductRepositoryI extends JpaRepository<Product, Long>, CustomizedProductRepository {

	/** Método para buscar un producto por nombre */
	public Product findByNameProduct(final String nameProduct);

}