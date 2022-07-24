package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;

public class ProduitDao implements IPojoDao {
	
	private final static String GET_PROD_BY_NAME_REQ = "SELECT p FROM Produit p WHERE p.nom = :nom";
	private final static String GET_PROD_ORDER_BY_NAME_REQ = "SELECT p FROM Produit p ORDER BY p.nom";
	private final static String EMPTY_PROD_TABLE_REQ = "DELETE FROM Produit";
	private final static String GET_PROD_ELEM_NB_REQ = "SELECT COUNT(p) FROM Produit p";

	public ProduitDao() {}
	

	public void addListToDb(List<?> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			if(object.getClass().equals(Produit.class)) {
				Produit elem = (Produit) object;
				em.persist(elem);
			}
		}
		em.getTransaction().commit();
	}
	
	@Override
	public Produit getByName(String nom, EntityManager em) {
		TypedQuery<Produit> query = em.createQuery(GET_PROD_BY_NAME_REQ, Produit.class);
    	query.setParameter("nom", nom);
    	List<Produit> categories = query.getResultList();
		return categories.get(0);
	}

	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_PROD_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public List<Produit> getList(EntityManager em) {
		TypedQuery<Produit> query = em.createQuery(GET_PROD_ORDER_BY_NAME_REQ, Produit.class);
		return query.getResultList();
	}

	@Override
	public Long getElementNb(EntityManager em) {
		Long valToReturn = 0L;
		em.getTransaction().begin();
		TypedQuery<Long> query = em.createQuery(GET_PROD_ELEM_NB_REQ , Long.class);
		valToReturn = query.getSingleResult();
		em.getTransaction().commit();
		return valToReturn;
	}
}
