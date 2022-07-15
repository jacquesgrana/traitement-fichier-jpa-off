package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;

public class MarqueDao implements IPojoDao{
	
	private final static String GET_MARQ_BY_NAME_REQ = "SELECT m FROM Marque m WHERE m.nom = :nom";

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

	@Override
	public Marque getByName(String nom, EntityManager em) {
		TypedQuery<Marque> query = em.createQuery(GET_MARQ_BY_NAME_REQ, Marque.class);
    	query.setParameter("nom", nom);
    	List<Marque> marques = query.getResultList();
		return marques.get(0);
	}

}
