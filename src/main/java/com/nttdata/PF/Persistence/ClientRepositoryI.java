package com.nttdata.PF.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de persistencia de la entidad CLIENT
 * 
 * @author agadelao
 *
 */
@Repository
public interface ClientRepositoryI extends JpaRepository<Client, Long> {

	public Client findByDniClient(final String dni);

}
