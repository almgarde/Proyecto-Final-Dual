package com.nttdata.PF.Persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * SUPERCLASE ABSTRACT-ENTITY
 * 
 * @author agadelao
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/** Auditoría - Usuario */
	private String updateUser;

	/** Auditoría - Fecha del registro */
	private Date updateDate;

	/**
	 * @return the updateUser
	 */
	@Column(name = "UPDATE_USER")
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
