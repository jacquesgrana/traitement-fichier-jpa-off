package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.GradeNutrition;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class DbDao {

	private List<Object> listCat = new ArrayList<>();
	private List<Object> listMarq = new ArrayList<>();
	private List<Object> listAdd = new ArrayList<>();
	private List<Object> listAll = new ArrayList<>();
	private List<Object> listIng = new ArrayList<>();
	private List<Object> listProd = new ArrayList<>();
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	CategorieDao catDao;
	MarqueDao marqDao;
	AdditifDao addDao;
	AllergeneDao allDao;
	IngredientDao ingDao;
	ProduitDao prodDao;

	public DbDao() {
	}
	
	public void init() {
		emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
		em = emf.createEntityManager();
		this.catDao = new CategorieDao();
		this.marqDao = new MarqueDao();
		this.addDao = new AdditifDao();
		this.allDao = new AllergeneDao();
		this.ingDao = new IngredientDao();
		this.prodDao = new ProduitDao();
	}
	
	public void close() {
		em.close();
		emf.close();
	}

	public Boolean populateDb(List<String> lines, Vue vue) {
		/*
		 * for(String line : lines) { System.out.println(line); }
		 */
		//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
		//EntityManager em = emf.createEntityManager()
		// System.out.println("connection ok : " + em);
		

		// 1e boucle pour peupler Categorie Marque Ingredient Additif Allergene
		//System.out.println("début 1e boucle");
		vue.displayMessage("Début 1e boucle");
		int compteur = 1;
		for (String line : lines) {
			StringBuilder builder = new StringBuilder();
			builder.append("1e boucle : ligne n° : ").append(compteur);
			vue.displayMessage(builder.toString());

			// Attention avec "|" : double echappement a faire
			String[] lineDatas = line.split("\\|");

			String catString = lineDatas[0];
			if (!catString.equals("")) {
				Categorie cat = new Categorie(catString);
				if (!listCat.contains(cat)) {
					listCat.add(cat);
				}
			}

			String marqString = lineDatas[1];
			if (!marqString.equals("")) {
				Marque marq = new Marque(marqString);
				if (!listMarq.contains(marq)) {
					listMarq.add(marq);
				}

			}

			if (lineDatas.length >= 5) {
				String ingString = lineDatas[4];
				String[] ingDatas = ingString.split(",");
				for (int i = 0; i < ingDatas.length; i++) {
					if (!ingDatas[i].equals("")) {
						String ingName = ingDatas[i];
						ingName = ingName.trim();
						Ingredient ing = new Ingredient(ingName);
						if (!listIng.contains(ing)) {
							listIng.add(ing);
						}
					}

				}
			}

			if (lineDatas.length >= 29) {
				String allString = lineDatas[28];
				String[] allDatas = allString.split(",");
				for (int i = 0; i < allDatas.length; i++) {
					if (!allDatas[i].equals("")) {
						allDatas[i] = allDatas[i].trim();
						Allergene all = new Allergene(allDatas[i]);
						if (!listAll.contains(all)) {
							listAll.add(all);
						}
					}
				}
			}

			if (lineDatas.length >= 30) {
				String addString = lineDatas[29];
				String[] addDatas = addString.split(" - ");
				for (int i = 0; i < addDatas.length; i++) {
					if (!addDatas[i].equals("")) {
						Additif add = new Additif(addDatas[i]);
						if (!listAdd.contains(add)) {
							listAdd.add(add);
						}
					}

				}
			}
			compteur++;
		}

		// TODO Traiter les listes pour enlever les doublons
		// ajouter appel d'une méthode de cette classe pour chaque liste

		//System.out.println("début sauvegarde après 1e boucle");
		vue.displayMessage("Début sauvegarde après 1e boucle");
		catDao.addListToDb(listCat, em);
		marqDao.addListToDb(listMarq, em);
		addDao.addListToDb(listAdd, em);
		allDao.addListToDb(listAll, em);
		ingDao.addListToDb(listIng, em);

		// 2e boucle pour peupler produit avec appel prep stat pour recuperer les objets
		// de la bd a partir des noms
		// boucle sur lines
		//System.out.println("début 2e boucle");
		vue.displayMessage("Début 2e boucle");
		// em.getTransaction().begin();
		compteur = 1;
		for (String line : lines) {
			// extraire le nom du produit
			StringBuilder builder = new StringBuilder();
			builder.append("2e boucle : ligne n° : ").append(compteur);
			vue.displayMessage(builder.toString());
			//System.out.println(builder.toString());
			String[] lineDatas = line.split("\\|");

			// extraire categorie, marque

			String catString = lineDatas[0];
			String marqString = lineDatas[1];
			String nomString = lineDatas[2];
			String gradeString = lineDatas[3];

			// creer objet Produit
			Produit produit = new Produit(nomString);
			// extraire le grade nutriscore et obtenir valeur
			GradeNutrition grade = GradeNutrition.getGradeByChar(gradeString.charAt(0));
			// System.out.println("grade : " + grade);
			produit.setGrade(grade);

			// chercher objet de la bd categorie et marque
			Categorie categorie = catDao.getByName(catString, em);
			produit.setCategorie(categorie);

			Marque marque = marqDao.getByName(marqString, em);
			produit.setMarque(marque);

			// System.out.println("produit : " + produit);

			// recuperer ingredients
			// boucle sur ingredients
			// recuperer objet ingredient de la bd
			// ajouter ing a set des ingredients de l'objet produit

			if (lineDatas.length >= 5) {
				String ingString = lineDatas[4];
				String[] ingDatas = ingString.split(",");

				for (int i = 0; i < ingDatas.length; i++) {
					if (!ingDatas[i].equals("") && (null != ingDatas[i])) {
						String ingName = ingDatas[i];
						ingName = ingName.trim();
						// Ingredient ing = this.getIngByName(ingName, listIng, em);
						//Ingredient ing = ingDao.getByName(ingName, em);
						Ingredient ing = this.getIngByName(ingName, listIng);
						if (null != ing) {
							produit.getIngredients().add(ing);
							/*
							 * StringBuilder builder2 = new StringBuilder();
							 * builder2.append("ajoût ingrédient : ").append(ing.getNom()).
							 * append(" / au produit : ").append(compteur);
							 * System.out.println(builder2.toString());
							 */
						}

						// System.out.println("ajoût ingrédient au produit : " + compteur);
					}
				}

			}

			if (lineDatas.length >= 29) {
				String allString = lineDatas[28];
				String[] allDatas = allString.split(",");
				for (int i = 0; i < allDatas.length; i++) {
					if (!allDatas[i].equals("")) {
						allDatas[i] = allDatas[i].trim();
						//Allergene all = allDao.getByName(allDatas[i], em);
						Allergene all = this.getAllByName(allDatas[i], listAll);
						if (null != all) {
							produit.getAllergenes().add(all);
						}
					}
				}
			}
			
			if (lineDatas.length >= 30) {
				String addString = lineDatas[29];
				String[] addDatas = addString.split(" - ");
				
				for (int i = 0; i < addDatas.length; i++) {
					if (!addDatas[i].equals("")) {
						Additif add = this.getAddByName(addDatas[i], listAdd);
						if (null != add) {
							produit.getAdditifs().add(add);
						}
					}
				}
			}

			// idem pour allergenes et additifs
			// enregistrer produit dans la liste des produits
			// em.persist(produit);
			listProd.add(produit);
			compteur++;
		}
		// em.getTransaction().commit();
		//System.out.println("début sauvegarde après 2e boucle");
		vue.displayMessage("Début sauvegarde après 2e boucle");
		prodDao.addListToDb(listProd, em);

		//em.close();
		//emf.close();
		return true;
	}
	/*
	private Categorie getCatByName(String catName, List<Object> list) { 
		Categorie categorie = new Categorie(catName); 
	 for(Object object : list) { 
		 Categorie cat = (Categorie) object; 
		 if(cat.equals(categorie)) { 
			 return cat; 
			 } 
		 }
	 return null; 
	 }
	*/
	/*
	private Marque getMarqByName(String marqName, List<Object> list) { 
		Marque marque = new Marque(marqName); 
	 for(Object object : list) { 
		 Marque marq = (Marque) object; 
		 if(marq.equals(marque)) { 
			 return marq; 
			 } 
		 }
	 return null; 
	 }
	 */
	
	 private Ingredient getIngByName(String ingName, List<Object> list) { 
		 Ingredient ingredient = new Ingredient(ingName); 
	 for(Object object : list) { 
		 Ingredient ing = (Ingredient) object; 
		 if(ing.equals(ingredient)) { 
			 return ing; 
			 } 
		 }
	 
	 return null; 
	 }
	 
	 private Allergene getAllByName(String allName, List<Object> list) { 
		 Allergene allergene = new Allergene(allName); 
	 for(Object object : list) { 
		 Allergene all = (Allergene) object; 
		 if(all.equals(allergene)) { 
			 return all; 
			 } 
		 }
	 
	 return null; 
	 }
	 
	 private Additif getAddByName(String addName, List<Object> list) { 
		 Additif additif = new Additif(addName); 
	 for(Object object : list) { 
		 Additif add = (Additif) object; 
		 if(add.equals(additif)) { 
			 return add; 
			 } 
		 }
	 
	 return null; 
	 }
	 
	public String getLoadReport() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nb d'éléments récupérés : catégories : ").append(listCat.size());
		builder.append(" / marques : ").append(listMarq.size());
		builder.append(" / ingrédients : ").append(listIng.size());
		builder.append(" / produits : ").append(listProd.size());
		builder.append(" / allergènes : ").append(listAll.size());
		builder.append(" / additifs : ").append(listAdd.size());
		/*
		return "Nb d'éléments récupérés : catégories : " + listCat.size() + " / marques : " + listMarq.size()
				+ " / ingrédients : " + listIng.size() + " / produits : " + listProd.size() + " / allergènes : "
				+ listAll.size() + " / additifs : " + listAdd.size();*/
		return builder.toString();
	}

	public List<Categorie> getCatList() {
		//CategorieDao catDao = new CategorieDao();
		//System.out.println("  Connection ok" + em.toString());
		List<Categorie> listToReturn = this.catDao.getList(this.em);
		return listToReturn;
	}
	
	public List<Marque> getMarqList() {
		List<Marque> listToReturn = this.marqDao.getList(this.em);
		return listToReturn;
	}

	public void emptyTables() {
		prodDao.emptyTable(em);
		catDao.emptyTable(em);
		
		// TODO vider les 3 tables d'association
	}

}
