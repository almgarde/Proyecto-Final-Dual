package com.nttdata.PF.Services;

/**
 * DTO para la entidad STATIONERY
 * 
 * @author agadelao
 *
 */
public class StationeryDto {

	/** CIF de las papelerías */
	private String cifStationery;

	/** Nombre de las papelerías */
	private String nameStationery;

	/**
	 * @return the cifStationery
	 */
	public String getCifStationery() {
		return cifStationery;
	}

	/**
	 * @param cifStationery the cifStationery to set
	 */
	public void setCifStationery(String cifStationery) {
		this.cifStationery = cifStationery;
	}

	/**
	 * @return the nameStationery
	 */
	public String getNameStationery() {
		return nameStationery;
	}

	/**
	 * @param nameStationery the nameStationery to set
	 */
	public void setNameStationery(String nameStationery) {
		this.nameStationery = nameStationery;
	}

}
