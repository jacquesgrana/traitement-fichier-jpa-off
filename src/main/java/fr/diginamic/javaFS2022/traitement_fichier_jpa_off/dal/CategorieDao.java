package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;

public class CategorieDao implements IPojoDao{
	
	private final static String GET_CAT_BY_NAME_REQ = "SELECT c FROM Categorie c WHERE c.nom = :nom";
	private final static String GET_CAT_ORDER_BY_NAME_REQ = "SELECT c FROM Categorie c ORDER BY c.nom";
	private final static String EMPTY_CAT_TABLE_REQ = "DELETE FROM Categorie";
	private final static String GET_CAT_ELEM_NB_REQ = "SELECT COUNT(c) FROM Categorie c";

	public CategorieDao() {}


	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Categorie elem = (Categorie) object;
			em.persist(elem);
		}
		em.getTransaction().commit();
	}

	@Override
	public Categorie getByName(String nom, EntityManager em) {
		TypedQuery<Categorie> query = em.createQuery(GET_CAT_BY_NAME_REQ, Categorie.class);
    	query.setParameter("nom", nom);
    	List<Categorie> categories = query.getResultList();
		return categories.get(0);
	}

	public List<Categorie> getList(EntityManager em) {
		TypedQuery<Categorie> query = em.createQuery(GET_CAT_ORDER_BY_NAME_REQ, Categorie.class);
		return query.getResultList();
	}

	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_CAT_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public Long getElementNb(EntityManager em) {
		Long valToReturn = 0L;
		em.getTransaction().begin();
		TypedQuery<Long> query = em.createQuery(GET_CAT_ELEM_NB_REQ , Long.class);
		valToReturn = query.getSingleResult();
		em.getTransaction().commit();
		return valToReturn;
	}
}
