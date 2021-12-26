package com.nttdata.PF.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.PF.Controllers.ProviderController;

/**
 * Implementación de la Interfaz de persistencia con Criteria de la entidad
 * STATIONERY
 * 
 * @author agadelao
 *
 */
@Repository
public class CustomizedStationeryRepositoryImpl implements CustomizedStationeryRepository {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(CustomizedStationeryRepositoryImpl.class);

	@Autowired
	EntityManager em;

	/** Metodo que muestra las papelerías donde ha comprado el cliente */
	@Override
	public List<Stationery> searchStationeryByIdClient(final Long idClient) {

		LOGGER.info("Inicio del método searchStationeryByIdClient");

		// Estructura Criteria
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Stationery> cQuery = cb.createQuery(Stationery.class);
		final Root<Stationery> rootC = cQuery.from(Stationery.class);
		final Join<Stationery, Client> jStaCli = rootC.join("clientsList");

		// Where
		final Predicate pr1 = cb.equal(jStaCli.get("idClient"), idClient);

		// Select
		cQuery.select(rootC).where(pr1);

		// Ejecución consulta
		List<Stationery> stationeries = em.createQuery(cQuery.distinct(true)).getResultList();

		LOGGER.info("Fin del método searchStationeryByIdClient");

		return stationeries;

	}

}
