package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;

public class IngredientDao implements IPojoDao {

	public IngredientDao() {}
	
	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Ingredient elem = (Ingredient) object;
			if(elem.getNom().length() > 255) {
				elem.setNom(elem.getNom().substring(0, 255));
			}
			em.persist(elem);
		}
		em.getTransaction().commit();
	}

}
