package com.nttdata.PF.Services;

/**
 * DTO para la entidad PRODUCT
 * 
 * @author agadelao
 *
 */
public class ProductDto {

	/** Nombre del producto */
	private String nameProduct;

	/** Precio del producto */
	private String priceProduct;

	/**
	 * @return the nameProduct
	 */
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
	public String getPriceProduct() {
		return priceProduct;
	}

	/**
	 * @param priceProduct the priceProduct to set
	 */
	public void setPriceProduct(String priceProduct) {
		this.priceProduct = priceProduct;
	}

}
