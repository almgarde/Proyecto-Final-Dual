package com.nttdata.PF.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de persistencia con JPA de la entidad STATIONERY
 * 
 * @author agadelao
 *
 */
@Repository
public interface StationeryRepositoryI extends JpaRepository<Stationery, Long>, CustomizedStationeryRepository {

	/** Método para buscar una papelería por nombre */
	public Stationery findByNameStationery(final String nameStationery);

	/** Método para buscar una papelería por su CIF */
	public Stationery findByCifStationery(final String cifStationery);

}
