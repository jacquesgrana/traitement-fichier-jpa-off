package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.List;

import javax.persistence.EntityManager;

public interface PojoDao {
	
	public void addListToDb(List<Object> listToAdd, EntityManager em);

}
