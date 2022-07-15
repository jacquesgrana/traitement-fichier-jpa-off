package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

public class MarqueDao implements PojoDao{

	public MarqueDao() {}
	
	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Marque elem = (Marque) object;
			em.persist(elem);
		}
		em.getTransaction().commit();
	}
	
	/*
	public void addListToDb(List<Marque> setToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Marque elem : setToAdd) {
			em.persist(elem);
		}
		em.getTransaction().commit();
	}*/

}
