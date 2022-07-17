package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import java.util.List;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.GradeNutrition;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.ModelDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.PalmOilPresence;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.AdditifDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.AllergeneDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CategorieDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.IngredientDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.MarqueDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.ProduitDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class ControllerDao {
	
	//private final static String EMPTY_POSSEDE_ING_TABLE_REQ = "DELETE FROM Possede_Ing";
	//private final static String EMPTY_POSSEDE_ALL_TABLE_REQ = "DELETE FROM Possede_All";
	//private final static String EMPTY_POSSEDE_ADD_TABLE_REQ = "DELETE FROM Possede_Add";
	
	private ModelDao model;

	//private List<Object> listCat = new ArrayList<>();
	//private List<Object> listMarq = new ArrayList<>();
	//private List<Object> listAdd = new ArrayList<>();
	//private List<Object> listAll = new ArrayList<>();
	//private List<Object> listIng = new ArrayList<>();
	//private List<Object> listProd = new ArrayList<>();
	
	//private EntityManagerFactory emf;
	//private EntityManager em;
	
	private CategorieDao catDao;
	private MarqueDao marqDao;
	private AdditifDao addDao;
	private AllergeneDao allDao;
	private IngredientDao ingDao;
	private ProduitDao prodDao;

	public ControllerDao() {
	}
	
	public void init() {
		this.model = new ModelDao();
		this.model.init();
		
		catDao = new CategorieDao();
		marqDao = new MarqueDao();
		addDao = new AdditifDao();
		allDao = new AllergeneDao();
		ingDao = new IngredientDao();
		prodDao = new ProduitDao();
	}
	
	public void close() {
		this.model.getEm().close();
		this.model.getEmf().close();
	}

	public Boolean populateDb(List<String> lines, Vue vue) {
		
		// 1e boucle pour peupler Categorie Marque Ingredient Additif Allergene
		vue.displayMessage("Début 1e boucle");
		int compteur = 1;
		for (String line : lines) {
			StringBuilder builder = new StringBuilder();
			builder.append("1e boucle : ligne n° : ").append(compteur);
			vue.displayMessage(builder.toString());

			String[] lineDatas = line.split("\\|");

			String catString = lineDatas[0];
			if (!catString.equals("")) {
				Categorie cat = new Categorie(catString);
				if (!this.model.getListCat().contains(cat)) {
					this.model.getListCat().add(cat);
				}
			}

			String marqString = lineDatas[1];
			if (!marqString.equals("")) {
				Marque marq = new Marque(marqString);
				if (!this.model.getListMarq().contains(marq)) {
					this.model.getListMarq().add(marq);
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
						if (!this.model.getListIng().contains(ing)) {
							this.model.getListIng().add(ing);
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
						if (!this.model.getListAll().contains(all)) {
							this.model.getListAll().add(all);
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
						if (!this.model.getListAdd().contains(add)) {
							this.model.getListAdd().add(add);
						}
					}

				}
			}
			compteur++;
		}

		vue.displayMessage("Début sauvegarde après 1e boucle");
		catDao.addListToDb(this.model.getListCat(), this.model.getEm());
		marqDao.addListToDb(this.model.getListMarq(), this.model.getEm());
		addDao.addListToDb(this.model.getListAdd(), this.model.getEm());
		allDao.addListToDb(this.model.getListAll(), this.model.getEm());
		ingDao.addListToDb(this.model.getListIng(), this.model.getEm());

		// 2e boucle pour peupler produit avec appel prep stat pour recuperer les objets de la bd a partir des noms
		vue.displayMessage("Début 2e boucle");
		compteur = 1;
		for (String line : lines) {
			StringBuilder builder = new StringBuilder();
			builder.append("2e boucle : ligne n° : ").append(compteur);
			vue.displayMessage(builder.toString());
			String[] lineDatas = line.split("\\|");

			String catString = lineDatas[0];
			String marqString = lineDatas[1];
			String nomString = lineDatas[2];
			String gradeString = lineDatas[3];
			String palmOilString = lineDatas[27];
			
			Produit produit = new Produit(nomString);
			
			GradeNutrition grade = GradeNutrition.getGradeByChar(gradeString.charAt(0));
			produit.setGrade(grade);
			
			PalmOilPresence palmOil;
			if (palmOilString.length() > 0) { 
				palmOil = PalmOilPresence.getOilPresenceByChar(palmOilString.charAt(0));
			}
			else {
				palmOil = PalmOilPresence.NSP;
			}
			produit.setPalmOil(palmOil);

			Categorie categorie = catDao.getByName(catString, this.model.getEm());
			produit.setCategorie(categorie);

			Marque marque = marqDao.getByName(marqString, this.model.getEm());
			produit.setMarque(marque);

			if (lineDatas.length >= 5) {
				String ingString = lineDatas[4];
				String[] ingDatas = ingString.split(",");

				for (int i = 0; i < ingDatas.length; i++) {
					if (!ingDatas[i].equals("") && (null != ingDatas[i])) {
						String ingName = ingDatas[i];
						ingName = ingName.trim();
						Ingredient ing = this.getIngByName(ingName, this.model.getListIng());
						if (null != ing) {
							produit.getIngredients().add(ing);
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
						Allergene all = this.getAllByName(allDatas[i], this.model.getListAll());
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
						Additif add = this.getAddByName(addDatas[i], this.model.getListAdd());
						if (null != add) {
							produit.getAdditifs().add(add);
						}
					}
				}
			}
			this.model.getListProd().add(produit);
			compteur++;
		}
		// this.model.getEm().getTransaction().commit();
		//System.out.println("début sauvegarde après 2e boucle");
		vue.displayMessage("Début sauvegarde après 2e boucle");
		prodDao.addListToDb(this.model.getListProd(), this.model.getEm());
		
		
		// TODO mettre en place 3 procédures pour éliminier le max de doublons selon les noms 
		// pour les tables Ingredient Additif et Allergene
		// mettre les textes en minuscules
		// enlever les premiers carac (si besoin) pour que le nom commence par [a-z]
		// supprimer les doublons + correction (suppression et ajout) des tables d'associations
		// recuperation des bons objets Produit et Ing (ou Add ou All) et suppression dans le set de Produit 
		// de l'ancienne relation et ajout de la nouvelle objet Ing (ou Add ou All)
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
		builder.append("Nb d'éléments récupérés : catégories : ").append(this.model.getListCat().size());
		builder.append(" / marques : ").append(this.model.getListMarq().size());
		builder.append(" / ingrédients : ").append(this.model.getListIng().size());
		builder.append(" / produits : ").append(this.model.getListProd().size());
		builder.append(" / allergènes : ").append(this.model.getListAll().size());
		builder.append(" / additifs : ").append(this.model.getListAdd().size());
		return builder.toString();
	}

	public List<Categorie> getCatList() {
		List<Categorie> listToReturn = this.catDao.getList(this.model.getEm());
		return listToReturn;
	}
	
	public List<Marque> getMarqList() {
		List<Marque> listToReturn = this.marqDao.getList(this.model.getEm());
		return listToReturn;
	}
	
	public List<Additif> getAddList() {
		List<Additif> listToReturn = this.addDao.getList(this.model.getEm());
		return listToReturn;
	}
	
	public List<Allergene> getAllList() {
		List<Allergene> listToReturn = this.allDao.getList(this.model.getEm());
		return listToReturn;
	}
	
	public List<Ingredient> getIngList() {
		List<Ingredient> listToReturn = this.ingDao.getList(this.model.getEm());
		return listToReturn;
	}
	
	public List<Produit> getProdList() {
		List<Produit> listToReturn = this.prodDao.getList(this.model.getEm());
		return listToReturn;
	}

	public void emptyTables() {
		System.out.println("  Vidage des tables");
		this.prodDao.emptyTable(this.model.getEm());
		this.catDao.emptyTable(this.model.getEm());
		this.marqDao.emptyTable(this.model.getEm());
		this.addDao.emptyTable(this.model.getEm());
		this.allDao.emptyTable(this.model.getEm());
		this.ingDao.emptyTable(this.model.getEm());
		
		
		// TODO vider les 3 tables d'association
		/*
		System.out.println("  Vidage des tables d'association");
		em.getTransaction().begin();
		Query queryIng = em.createQuery(EMPTY_POSSEDE_ING_TABLE_REQ);
		Query queryAll = em.createQuery(EMPTY_POSSEDE_ALL_TABLE_REQ);
		Query queryAdd = em.createQuery(EMPTY_POSSEDE_ADD_TABLE_REQ);
		queryIng.executeUpdate();
		queryAll.executeUpdate();
		queryAdd.executeUpdate();
		em.getTransaction().commit();
		*/
	}
	
	public Boolean isTablesNotEmpty() {
		Boolean isAddTableFull = this.addDao.getElementNb(this.model.getEm()) > 0;
		Boolean isAllTableFull = this.allDao.getElementNb(this.model.getEm()) > 0;
		Boolean isCatTableFull = this.catDao.getElementNb(this.model.getEm()) > 0;
		Boolean isIngTableFull = this.ingDao.getElementNb(this.model.getEm()) > 0;
		Boolean isMarqTableFull = this.marqDao.getElementNb(this.model.getEm()) > 0;
		Boolean isProdTableFull = this.prodDao.getElementNb(this.model.getEm()) > 0;
		return (isAddTableFull && isAllTableFull && isCatTableFull && isIngTableFull && isMarqTableFull && isProdTableFull);
	}

}
