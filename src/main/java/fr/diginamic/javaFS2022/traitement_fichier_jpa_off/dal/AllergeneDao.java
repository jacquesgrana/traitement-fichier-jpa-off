package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;

public class AllergeneDao implements IPojoDao{
	
	private final static String GET_ALL_BY_NAME_REQ = "SELECT a FROM Allergene a WHERE a.nom = :nom";
	private final static String GET_ALL_ORDER_BY_NAME_REQ = "SELECT a FROM Allergene a ORDER BY a.nom";
	private final static String EMPTY_ALL_TABLE_REQ = "DELETE FROM Allergene";

	public AllergeneDao() {}

	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Allergene elem = (Allergene) object;
			em.persist(elem);
		}
		em.getTransaction().commit();
	}
	
	@Override
	public Allergene getByName(String nom, EntityManager em) {
		TypedQuery<Allergene> query = em.createQuery(GET_ALL_BY_NAME_REQ, Allergene.class);
    	query.setParameter("nom", nom);
    	List<Allergene> categories = query.getResultList();
		return categories.get(0);
	}
	
	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_ALL_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public List<Allergene> getList(EntityManager em) {
		TypedQuery<Allergene> query = em.createQuery(GET_ALL_ORDER_BY_NAME_REQ, Allergene.class);
		return query.getResultList();
	}

}
