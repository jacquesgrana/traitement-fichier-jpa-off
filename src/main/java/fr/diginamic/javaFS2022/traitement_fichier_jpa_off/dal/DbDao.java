package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbDao {

	public DbDao() {}

	public Boolean populateDb(List<String> lines) {
		/*
		for(String line : lines) {
			System.out.println(line);
		}*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
    	EntityManager em = emf.createEntityManager();
    	System.out.println("connection ok : " + em);
    	
    	//1e boucle pour peupler Categorie Marque Ingredient Additif Allergene
    	
    	//2e boucle pour peupler produit avec appel prep stat pour recuperer les objets a partir des noms
    	
    	em.close();
    	emf.close();
		return true;
	}

}
