package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;

public class AdditifDao implements IPojoDao{

	public AdditifDao() {}
	
	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Additif elem = (Additif) object;
			em.persist(elem);
		}
		em.getTransaction().commit();
	}

}
