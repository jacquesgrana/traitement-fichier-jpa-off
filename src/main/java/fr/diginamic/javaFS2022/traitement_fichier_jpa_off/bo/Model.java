package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.DbDao;

public class Model {

	private Boolean isDataLoaded;
	
	private DbDao dbDao;
	
	public Model() {}
	
	public void init() {
		this.isDataLoaded = false;
	}

	/**
	 * @return the isDataLoaded
	 */
	public Boolean getIsDataLoaded() {
		return isDataLoaded;
	}

	/**
	 * @param isDataLoaded the isDataLoaded to set
	 */
	public void setIsDataLoaded(Boolean isDataLoaded) {
		this.isDataLoaded = isDataLoaded;
	}

	/**
	 * @return the dbDao
	 */
	public DbDao getDbDao() {
		return dbDao;
	}

	/**
	 * @param dbDao the dbDao to set
	 */
	public void setDbDao(DbDao dbDao) {
		this.dbDao = dbDao;
	}

	
}
