package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;

public class MarqueDao implements IPojoDao{
	
	private final static String GET_MARQ_BY_NAME_REQ = "SELECT m FROM Marque m WHERE m.nom = :nom";
	private final static String GET_MARQ_ORDER_BY_NAME_REQ = "SELECT m FROM Marque m ORDER BY m.nom";
	private final static String EMPTY_MARQ_TABLE_REQ = "DELETE FROM Marque";
	private final static String GET_MARQ_ELEM_NB_REQ = "SELECT COUNT(m) FROM Marque m";

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
	
	public List<Marque> getList(EntityManager em) {
		TypedQuery<Marque> query = em.createQuery(GET_MARQ_ORDER_BY_NAME_REQ, Marque.class);
		return query.getResultList();
	}

	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_MARQ_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public Long getElementNb(EntityManager em) {
		Long valToReturn = 0L;
		em.getTransaction().begin();
		TypedQuery<Long> query = em.createQuery(GET_MARQ_ELEM_NB_REQ , Long.class);
		valToReturn = query.getSingleResult();
		em.getTransaction().commit();
		return valToReturn;
	}
}
