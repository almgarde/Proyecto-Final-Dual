package com.nttdata.PF.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementación de la Interfaz de persistencia con Criteria de la entidad
 * PRODUCT
 * 
 * @author agadelao
 *
 */
@Repository
public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(CustomizedProductRepositoryImpl.class);

	@Autowired
	EntityManager em;

	/** Método para listar los productos comprados por el cliente */
	@Override
	public List<Product> productsClient(final Long idClient) {

		LOGGER.info("Inicio del método productsClient");

		// Estructura Criteria
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Product> cQuery = cb.createQuery(Product.class);
		final Root<Product> rootC = cQuery.from(Product.class);

		// Where
		final Predicate pr1 = cb.equal(rootC.get("idClient"), idClient);

		// Select
		cQuery.select(rootC).where(pr1);

		// Ejecución consulta
		List<Product> products = em.createQuery(cQuery.distinct(true)).getResultList();

		LOGGER.info("Fin del método productsClient");

		return products;
	}

}
