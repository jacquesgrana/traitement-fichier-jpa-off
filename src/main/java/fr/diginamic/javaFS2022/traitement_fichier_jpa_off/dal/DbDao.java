package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;

public class DbDao {
	
	private List<Object> listCat = new ArrayList<>();
	private List<Object> listMarq = new ArrayList<>();
	private List<Object> listAdd = new ArrayList<>();
	private List<Object> listAll = new ArrayList<>();
	private List<Object> listIng = new ArrayList<>();
	

	public DbDao() {}

	public Boolean populateDb(List<String> lines) {
		/*
		for(String line : lines) {
			System.out.println(line);
		}*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
    	EntityManager em = emf.createEntityManager();
    	//System.out.println("connection ok : " + em);
    	CategorieDao catDao = new CategorieDao();
    	MarqueDao marqDao = new MarqueDao();
    	AdditifDao addDao = new AdditifDao();
    	AllergeneDao allDao = new AllergeneDao();
    	IngredientDao ingDao = new IngredientDao();
    	
    	//1e boucle pour peupler Categorie Marque Ingredient Additif Allergene
    	for (String line : lines) {
    		
    		// Attention avec "|" : double echappement a faire
    		String[] lineDatas = line.split("\\|");
    		
    		String catString = lineDatas[0];
    		if(!catString.equals("")) {
    			Categorie cat = new Categorie(catString);
    			if (!listCat.contains(cat)) {
        			listCat.add(cat);
        		}
    		}
    		
    		String marqString = lineDatas[1];
    		if(!marqString.equals("")) {
    			Marque marq = new Marque(marqString);
    			if (!listMarq.contains(marq)) {
        			listMarq.add(marq);
        		}
    		}
    		
    		if(lineDatas.length >= 5) {
    			String ingString = lineDatas[4];
    			String[] ingDatas = ingString.split(",");
    			for(int i=0; i<ingDatas.length; i++) {
    				if (!ingDatas[i].equals("")) {
    					ingDatas[i] = ingDatas[i].trim();
    					Ingredient ing = new Ingredient(ingDatas[i]);
    					if (!listIng.contains(ing)) {
    						listIng.add(ing);
    					}
    				}
    				
    			}
    		}
    		
    		if(lineDatas.length >= 29) {
    			String allString = lineDatas[28];
    			String[] allDatas = allString.split(",");
    			for(int i=0; i<allDatas.length; i++) {
    				if (!allDatas[i].equals("")) {
    					allDatas[i] = allDatas[i].trim();
    					Allergene all = new Allergene(allDatas[i]);
    					if (!listAll.contains(all)) {
        					listAll.add(all);
        				}
    				}
    			}
    		}
    		

    		if(lineDatas.length >= 30) {
    			String addString = lineDatas[29];
    			String[] addDatas = addString.split(" - ");
    			for(int i=0; i<addDatas.length; i++) {
    				if (!addDatas[i].equals("")) {
    					Additif add = new Additif(addDatas[i]);
        				if (!listAdd.contains(add)) {
        					listAdd.add(add);
        				}
    				}
    				
    			}
    		}
    		
    		
    	}
    	
		// TODO Traiter les listes pour enlever les doublons
    	catDao.addListToDb(listCat, em);
    	marqDao.addListToDb(listMarq, em);
    	addDao.addListToDb(listAdd, em);
    	allDao.addListToDb(listAll, em);
    	ingDao.addListToDb(listIng, em);
    	
    	//2e boucle pour peupler produit avec appel prep stat pour recuperer les objets de la bd a partir des noms
    	
    	em.close();
    	emf.close();
		return true;
	}

	public String getLoadReport() {
		return "Nb d'éléments récupérés : catégories : " + listCat.size() 
		+ " / marques : " + listMarq.size() 
		+ " / ingrédients : " + listIng.size() 
		+ " / allergènes : " + listAll.size() 
		+ " / additifs : " + listAdd.size();
	}

}
