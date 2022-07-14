package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

public class Model {

	private Boolean isDataLoaded;
	
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

}
