package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.List;

import javax.persistence.EntityManager;

public class CategorieDao implements IPojoDao{

	public CategorieDao() {}

	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Categorie elem = (Categorie) object;
			em.persist(elem);
		}
		em.getTransaction().commit();
	}
}