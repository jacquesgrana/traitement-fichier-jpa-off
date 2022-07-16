package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.DbDao;

public class Model {

	private Boolean isDbFull;
	
	private DbDao dbDao;
	
	public Model() {}
	
	public void init() {
		this.isDbFull = false;
	}

	/**
	 * @return the isDataLoaded
	 */
	public Boolean getIsDataLoaded() {
		return isDbFull;
	}

	/**
	 * @param isDataLoaded the isDataLoaded to set
	 */
	public void setIsDataLoaded(Boolean isDataLoaded) {
		this.isDbFull = isDataLoaded;
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
