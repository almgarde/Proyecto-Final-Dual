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
 * Entidad proveedor. Representa la tabla PROVIDERS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PROVIDERS")
public class Provider extends AbstractEntity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** ID del proveedor */
	private Long idProvider;

	/** CIF del proveedor (PK) */
	private String cifProvider;

	/** Nombre del proveedor */
	private String nameProvider;

	/** Provincia del proveedor */
	private String provinceProvider;

	/** Localidad del proveedor */
	private String cityProvider;

	/** Productos suministrados por el proveedor */
	private List<Product> productsProvider;

	/** Papelerías a las que suministra el proveedor */
	private List<Stationery> stationeriesList;

	/**
	 * @return the idProvider
	 */
	@Id
	@GeneratedValue(generator = "genSeq3")
	@SequenceGenerator(name = "genSeq3", sequenceName = "SEQ_PROVIDERS", allocationSize = 1)
	public Long getIdProvider() {
		return idProvider;
	}

	/**
	 * @param idProvider the idProvider to set
	 */
	public void setIdProvider(Long idProvider) {
		this.idProvider = idProvider;
	}

	/**
	 * @return the cifProvider
	 */
	@Column(name = "CIF_PROVIDER", nullable = false, length = 9, unique = true)
	public String getCifProvider() {
		return cifProvider;
	}

	/**
	 * @param cifProvider the cifProvider to set
	 */
	public void setCifProvider(String cifProvider) {
		this.cifProvider = cifProvider;
	}

	/**
	 * @return the nameProvider
	 */
	@Column(name = "NAME_PROVIDER", nullable = false)
	public String getNameProvider() {
		return nameProvider;
	}

	/**
	 * @param nameProvider the nameProvider to set
	 */
	public void setNameProvider(String nameProvider) {
		this.nameProvider = nameProvider;
	}

	/**
	 * @return the provinceProvider
	 */
	@Column(name = "PROVINCE_PROVIDER")
	public String getProvinceProvider() {
		return provinceProvider;
	}

	/**
	 * @param provinceProvider the provinceProvider to set
	 */
	public void setProvinceProvider(String provinceProvider) {
		this.provinceProvider = provinceProvider;
	}

	/**
	 * @return the cityProvider
	 */
	@Column(name = "CITY_PROVIDER")
	public String getCityProvider() {
		return cityProvider;
	}

	/**
	 * @param cityProvider the cityProvider to set
	 */
	public void setCityProvider(String cityProvider) {
		this.cityProvider = cityProvider;
	}

	/**
	 * @return the productsProvider
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvider")
	public List<Product> getProductsProvider() {
		return productsProvider;
	}

	/**
	 * @param productsProvider the productsProvider to set
	 */
	public void setProductsProvider(List<Product> productsProvider) {
		this.productsProvider = productsProvider;
	}

	/**
	 * @return the stationeriesProvider
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "providersList")
	public List<Stationery> getStationeriesProvider() {
		return stationeriesList;
	}

	/**
	 * @param stationeriesProvider the stationeriesProvider to set
	 */
	public void setStationeriesProvider(List<Stationery> stationeriesProvider) {
		this.stationeriesList = stationeriesProvider;
	}

	@Override
	public String toString() {
		return "Provider [idProvider=" + idProvider + ", cifProvider=" + cifProvider + ", nameProvider=" + nameProvider
				+ ", provinceProvider=" + provinceProvider + ", cityProvider=" + cityProvider + "]";
	}

}
