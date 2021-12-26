package com.nttdata.PF.Persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad Papelería. Representa la tabla STATIONERIES
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "STATIONERIES")
public class Stationery extends AbstractEntity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** ID de las papelerías (PK) */
	private Long idStationery;

	/** CIF de las papelerías */
	private String cifStationery;

	/** Nombre de las papelerías */
	private String nameStationery;

	/** Provincia de las papelerías */
	private String provinceStationery;

	/** Localidad de las papelerías */
	private String cityStationery;

	/** Proveedores de las papelerías */
	private List<Provider> providersList;

	/** Clientes de las papelerías */
	private List<Client> clientsList;

	/**
	 * @return the idStationery
	 */
	@Id
	@GeneratedValue(generator = "genSeq4")
	@SequenceGenerator(name = "genSeq4", sequenceName = "SEQ_STATIONERIES", allocationSize = 1)
	public Long getIdStationery() {
		return idStationery;
	}

	/**
	 * @param idStationery the idStationery to set
	 */

	public void setIdStationery(Long idStationery) {
		this.idStationery = idStationery;
	}

	/**
	 * @return the cifStationery
	 */

	public String getCifStationery() {
		return cifStationery;
	}

	/**
	 * @param cifStationery the cifStationery to set
	 */
	@Column(name = "CIF_STATIONERY", nullable = false, length = 9, unique = true)
	public void setCifStationery(String cifStationery) {
		this.cifStationery = cifStationery;
	}

	/**
	 * @return the nameStationery
	 */
	@Column(name = "NAME_STATIONERY", nullable = false)
	public String getNameStationery() {
		return nameStationery;
	}

	/**
	 * @param nameStationery the nameStationery to set
	 */
	public void setNameStationery(String nameStationery) {
		this.nameStationery = nameStationery;
	}

	/**
	 * @return the provinceStationery
	 */
	@Column(name = "PROVINCE_STATIONERY")
	public String getProvinceStationery() {
		return provinceStationery;
	}

	/**
	 * @param provinceStationery the provinceStationery to set
	 */
	public void setProvinceStationery(String provinceStationery) {
		this.provinceStationery = provinceStationery;
	}

	/**
	 * @return the cityStationery
	 */
	@Column(name = "CITY_STATIONERY")
	public String getCityStationery() {
		return cityStationery;
	}

	/**
	 * @param cityStationery the cityStationery to set
	 */
	public void setCityStationery(String cityStationery) {
		this.cityStationery = cityStationery;
	}

	/**
	 * @return the providerList
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STATIONERIES_PROVIDERS", joinColumns = @JoinColumn(name = "ID_STATIONERY"), inverseJoinColumns = @JoinColumn(name = "ID_PROVIDER"))

	public List<Provider> getProvidersList() {
		return providersList;
	}

	/**
	 * @param providerList the providerList to set
	 */
	public void setProvidersList(List<Provider> providersList) {
		this.providersList = providersList;
	}

	/**
	 * @return the clientsList
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STATIONERIES_CLIENTS", joinColumns = @JoinColumn(name = "ID_STATIONERY"), inverseJoinColumns = @JoinColumn(name = "ID_CLIENT"))
	public List<Client> getClientsList() {
		return clientsList;
	}

	/**
	 * @param clientsList the clientsList to set
	 */
	public void setClientsList(List<Client> clientsList) {
		this.clientsList = clientsList;
	}

	@Override
	public String toString() {
		return "Stationery [idStationery=" + idStationery + ", cifStationery=" + cifStationery + ", nameStationery="
				+ nameStationery + ", provinceStationery=" + provinceStationery + ", cityStationery=" + cityStationery
				+ "]";
	}

}
