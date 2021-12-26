package com.nttdata.PF.Services;

/**
 * DTO para la entidad CLIENT
 * 
 * @author agadelao
 *
 */
public class ClientDto {

	/** DNI del cliente */
	private String dniClient;

	/** Nombre del Cliente */
	private String nameClient;

	/** Apellido del Cliente */
	private String surnameClient;

	/**
	 * @return the dniClient
	 */
	public String getDniClient() {
		return dniClient;
	}

	/**
	 * @param dniClient the dniClient to set
	 */
	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}

	/**
	 * @return the nameClient
	 */
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
	public String getSurnameClient() {
		return surnameClient;
	}

	/**
	 * @param surnameClient the surnameClient to set
	 */
	public void setSurnameClient(String surnameClient) {
		this.surnameClient = surnameClient;
	}

}
