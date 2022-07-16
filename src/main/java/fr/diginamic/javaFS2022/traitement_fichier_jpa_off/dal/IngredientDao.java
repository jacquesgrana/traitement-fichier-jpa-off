package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;

public class IngredientDao implements IPojoDao {
	
	private final static String GET_ING_BY_NAME_REQ = "SELECT i FROM Ingredient i WHERE i.nom = :nom";
	private final static String GET_ING_ORDER_BY_NAME_REQ = "SELECT i FROM Ingredient i ORDER BY i.nom";
	private final static String EMPTY_ING_TABLE_REQ = "DELETE FROM Ingredient";

	public IngredientDao() {}
	
	@Override
	public void addListToDb(List<Object> listToAdd, EntityManager em) {
		em.getTransaction().begin();
		for(Object object : listToAdd) {
			Ingredient elem = (Ingredient) object;
			if(elem.getNom().length() > 255) {
				elem.setNom(elem.getNom().substring(0, 255));
			}
			em.persist(elem);
		}
		em.getTransaction().commit();
	}
	
	@Override
	public Ingredient getByName(String nom, EntityManager em) {
		TypedQuery<Ingredient> query = em.createQuery(GET_ING_BY_NAME_REQ, Ingredient.class);
    	query.setParameter("nom", nom);
    	List<Ingredient> ingredients = query.getResultList();
    	if(ingredients.isEmpty()) {
    		return null;
    	}
		return ingredients.get(0);
	}
	
	public List<Ingredient> getList(EntityManager em) {
		TypedQuery<Ingredient> query = em.createQuery(GET_ING_ORDER_BY_NAME_REQ, Ingredient.class);
		return query.getResultList();
	}

	@Override
	public void emptyTable(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery(EMPTY_ING_TABLE_REQ);
		query.executeUpdate();
		em.getTransaction().commit();
	}

}
