package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.AdditifDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.CategorieDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.MarqueDao;

public class DbDao {

	public DbDao() {}

	public Boolean populateDb(List<String> lines) {
		/*
		for(String line : lines) {
			System.out.println(line);
		}*/
		
		List<Object> listCat = new ArrayList<>();
		List<Object> listMarq = new ArrayList<>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
    	EntityManager em = emf.createEntityManager();
    	//System.out.println("connection ok : " + em);
    	CategorieDao catDao = new CategorieDao();
    	MarqueDao marqDao = new MarqueDao();
    	AdditifDao addDao = new AdditifDao();
    	
    	//1e boucle pour peupler Categorie Marque Ingredient Additif Allergene
    	for (String line : lines) {
    		//System.out.println(line);
    		
    		// Attention avec "|" : double echappement a faire
    		String[] lineDatas = line.split("\\|");
    		String catString = lineDatas[0];
    		String marqString = lineDatas[1];
    		//System.out.println("cat : " + catString + " / marq : " + marqString);
    		
    		Categorie cat = new Categorie(catString);
    		Marque marq = new Marque(marqString);
    		//System.out.println(cat + " / " + marq);
    		
    		if (!listCat.contains(cat)) {
    			listCat.add(cat);
    		}
    		if (!listMarq.contains(marq)) {
    			listMarq.add(marq);
    		}
    	}
    	
    	catDao.addListToDb(listCat, em);
    	marqDao.addListToDb(listMarq, em);
    	
    	//2e boucle pour peupler produit avec appel prep stat pour recuperer les objets a partir des noms
    	
    	em.close();
    	emf.close();
		return true;
	}

}
