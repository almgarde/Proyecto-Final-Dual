package com.nttdata.PF.Persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad producto. Representa la tabla PRODUCTS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PRODUCTS")
public class Product extends AbstractEntity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** ID del producto (PK) */
	private Long idProduct;

	/** Nombre del producto */
	private String nameProduct;

	/** Precio del producto */
	private double priceProduct;

	/** Cliente asociado al producto */
	private Client idClient;

	/** Proveedor asociado al producto */
	private Provider idProvider;

	/**
	 * @return the idProduct
	 */
	@Id
	@GeneratedValue(generator = "genSeq2")
	@SequenceGenerator(name = "genSeq2", sequenceName = "SEQ_PRODUCTS", allocationSize = 1)
	public Long getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the nameProduct
	 */
	@Column(name = "NAME_PRODUCT", nullable = false)
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * @param nameProduct the nameProduct to set
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	/**
	 * @return the priceProduct
	 */
	@Column(name = "PRICE_PRODUCT", nullable = false)
	public double getPriceProduct() {
		return priceProduct;
	}

	/**
	 * @param priceProduct the priceProduct to set
	 */
	public void setPriceProduct(double priceProduct) {
		this.priceProduct = priceProduct;
	}

	/**
	 * @return the idClient
	 */
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT")

	public Client getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the idProvider
	 */
	@ManyToOne
	@JoinColumn(name = "ID_PROVIDER")
	public Provider getIdProvider() {
		return idProvider;
	}

	/**
	 * @param idProvider the idProvider to set
	 */
	public void setIdProvider(Provider idProvider) {
		this.idProvider = idProvider;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct
				+ "]";
	}

}
