package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;

public class AdditifDao implements IPojoDao{
	
	private final static String GET_ADD_BY_NAME_REQ = "SELECT a FROM Additif a WHERE a.nom = :nom";
	private final static String GET_ADD_ORDER_BY_NAME_REQ = "SELECT a FROM Additif a ORDER BY a.nom";
	private final static String EMPTY_ADD_TABLE_REQ = "DELETE FROM Additif";
	private final static String GET_ADD_ELEM_NB_REQ = "SELECT COUNT(a) FROM Additif a";

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
	
	@Override
	public Object getByName(String nom, EntityManager em) {
		TypedQuery<Additif> query = em.createQuery(GET_ADD_BY_NAME_REQ, Additif.class);
    	query.setParameter("nom", nom);
    	List<Additif> categories = query.getResultList();
		return categories.get(0);
	}
	
	public List<Additif> getList(EntityManager em) {
		TypedQuery<Additif> query = em.createQuery(GET_ADD_ORDER_BY_NAME_REQ, Additif.class);
		return query.getResultList();
	}
	
	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_ADD_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public Long getElementNb(EntityManager em) {
		Long valToReturn = 0L;
		em.getTransaction().begin();
		TypedQuery<Long> query = em.createQuery(GET_ADD_ELEM_NB_REQ , Long.class);
		valToReturn = query.getSingleResult();
		em.getTransaction().commit();
		return valToReturn;
	}

}
