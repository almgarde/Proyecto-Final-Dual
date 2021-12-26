package com.nttdata.PF.Services;

/**
 * DTO para la entidad PROVIDER
 * 
 * @author agadelao
 *
 */
public class ProviderDto {

	/** CIF del proveedor (PK) */
	private String cifProvider;

	/** Nombre del proveedor */
	private String nameProvider;

	/**
	 * @return the cifProvider
	 */
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
	public String getNameProvider() {
		return nameProvider;
	}

	/**
	 * @param nameProvider the nameProvider to set
	 */
	public void setNameProvider(String nameProvider) {
		this.nameProvider = nameProvider;
	}

}
