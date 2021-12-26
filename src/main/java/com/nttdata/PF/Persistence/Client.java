package com.nttdata.PF.Persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad Cliente. Representa la tabla CLIENTS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "CLIENTS")
public class Client extends AbstractEntity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** ID del Cliente (PK) */
	private Long idClient;

	/** DNI del cliente */
	private String dniClient;

	/** Nombre del Cliente */
	private String nameClient;

	/** Apellido del Cliente */
	private String surnameClient;

	/** Productos comprados por el cliente */
	private List<Product> productsClient;

	/** Papelerías a las que acuden los clientes */
	private List<Stationery> stationeriesList;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "genSeq1")
	@SequenceGenerator(name = "genSeq1", sequenceName = "SEQ_CLIENTS", allocationSize = 1)
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the dni
	 */
	@Column(name = "DNI_CLIENT", nullable = false, length = 9, unique = true)
	public String getDniClient() {
		return dniClient;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDniClient(String dni) {
		this.dniClient = dni;
	}

	/**
	 * @return the nameClient
	 */
	@Column(name = "NAME_CLIENT", nullable = false)
	public String getNameClient() {
		return nameClient;
	}

	/**
	 * @param nameClient the nameClient to set
	 */
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	/**
	 * @return the surnameClient
	 */
	@Column(name = "SURNAME_CLIENT", nullable = false)
	public String getSurnameClient() {
		return surnameClient;
	}

	/**
	 * @param surnameClient the surnameClient to set
	 */
	public void setSurnameClient(String surnameClient) {
		this.surnameClient = surnameClient;
	}

	/**
	 * @return the productsClient
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
	public List<Product> getProductsClient() {
		return productsClient;
	}

	/**
	 * @param productsClient the productsClient to set
	 */
	public void setProductsClient(List<Product> productsClient) {
		this.productsClient = productsClient;
	}

	/**
	 * @return the stationeriesList
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "clientsList")
	public List<Stationery> getStationeriesList() {
		return stationeriesList;
	}

	/**
	 * @param stationeriesList the stationeriesList to set
	 */
	public void setStationeriesList(List<Stationery> stationeriesList) {
		this.stationeriesList = stationeriesList;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", dniClient=" + dniClient + ", nameClient=" + nameClient
				+ ", surnameClient=" + surnameClient + "]";
	}

}
