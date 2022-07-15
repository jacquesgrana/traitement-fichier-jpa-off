package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;

public class AdditifDao implements IPojoDao{
	
	private final static String GET_ADD_BY_NAME_REQ = "SELECT a FROM Additif a WHERE a.nom = :nom";

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
	

	public Additif getByName(String nom, EntityManager em) {
		TypedQuery<Additif> query = em.createQuery(GET_ADD_BY_NAME_REQ, Additif.class);
    	query.setParameter("nom", nom);
    	List<Additif> categories = query.getResultList();
		return categories.get(0);
	}

}
