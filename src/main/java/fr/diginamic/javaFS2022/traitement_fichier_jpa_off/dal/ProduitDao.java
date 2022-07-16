package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;

public class ProduitDao implements IPojoDao {
	
	private final static String GET_PROD_BY_NAME_REQ = "SELECT p FROM Produit p WHERE p.nom = :nom";
	
	private final static String EMPTY_PROD_TABLE_REQ = "DELETE FROM Produit";

	public ProduitDao() {}
	
	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Produit elem = (Produit) object;
			em.persist(elem);
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

}
