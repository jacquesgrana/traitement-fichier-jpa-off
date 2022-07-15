package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;

public interface IPojoDao {
	
	public void addListToDb(List<Object> listToAdd, EntityManager em);
	
	// TODO ajouter méthode getByName qui renvoie un objet
	public Object getByName(String nom, EntityManager em);

}
