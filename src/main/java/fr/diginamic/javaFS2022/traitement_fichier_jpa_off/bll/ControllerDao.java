package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;


import java.util.List;

import javax.persistence.Persistence;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.GradeNutrition;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.ModelDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.PalmOilPresence;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class ControllerDao {

	private ModelDao model;

	public ControllerDao() {
	}

	public void init() {
		this.model = new ModelDao();
		this.model.init();
		this.model.setEmf(Persistence.createEntityManagerFactory(this.model.getPersistUnitName()));
		this.model.setEm(this.model.getEmf().createEntityManager());
	}

	public void close() {
		this.model.getEm().close();
		this.model.getEmf().close();
	}

	public Boolean populateDb(List<String> lines, Vue vue) {

		// 1e boucle pour peupler les tables Categorie Marque Ingredient Additif
		// Allergene
		vue.displayMessage("Début 1e boucle");
		int compteur = 1;
		for (String line : lines) {
			StringBuilder builder = new StringBuilder();
			builder.append("1e boucle : ligne n° : ").append(compteur);
			vue.displayMessage(builder.toString());

			String[] lineDatas = line.split("\\|");

			String catString = lineDatas[0];
			if (!catString.equals("")) {
				catString = Library.stringProcess(catString);
				Categorie cat = new Categorie(catString);
				if (!this.model.getListCat().contains(cat)) {
					this.model.getListCat().add(cat);
				}
			}

			String marqString = lineDatas[1];
			if (!marqString.equals("")) {
				marqString = Library.stringProcess(marqString);
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
						ingName = Library.stringProcess(ingName);
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
						String allName = allDatas[i];
						allName = Library.stringProcess(allName);
						Allergene all = new Allergene(allName);
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
						String addName = addDatas[i];
						addName = Library.stringProcess(addName);
						Additif add = new Additif(addName);
						if (!this.model.getListAdd().contains(add)) {
							this.model.getListAdd().add(add);
						}
					}

				}
			}
			compteur++;
		}

		vue.displayMessage("Début sauvegarde après 1e boucle");
		this.model.getCatDao().addListToDb(this.model.getListCat(), this.model.getEm());
		this.model.getMarqDao().addListToDb(this.model.getListMarq(), this.model.getEm());
		this.model.getAddDao().addListToDb(this.model.getListAdd(), this.model.getEm());
		this.model.getAllDao().addListToDb(this.model.getListAll(), this.model.getEm());
		this.model.getIngDao().addListToDb(this.model.getListIng(), this.model.getEm());

		// 2e boucle pour peupler la table Produit avec appel a la bd pour recuperer les
		// objets de la bd a partir des noms
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
			} else {
				palmOil = PalmOilPresence.NSP;
			}
			produit.setPalmOil(palmOil);
			
			catString = Library.stringProcess(catString);
			Categorie categorie = this.model.getCatDao().getByName(catString, this.model.getEm());
			produit.addCat(categorie);

			marqString = Library.stringProcess(marqString);
			Marque marque = this.model.getMarqDao().getByName(marqString, this.model.getEm());
			produit.addMarq(marque);

			if (lineDatas.length >= 5) {
				String ingString = lineDatas[4];
				String[] ingDatas = ingString.split(",");

				for (int i = 0; i < ingDatas.length; i++) {
					if (!ingDatas[i].equals("") && (null != ingDatas[i])) {
						String ingName = ingDatas[i];
						ingName = Library.stringProcess(ingName);
						Ingredient ing = this.getIngByName(ingName, this.model.getListIng());
						if (null != ing) {
							produit.addIng(ing);
						}
					}
				}
			}

			if (lineDatas.length >= 29) {
				String allString = lineDatas[28];
				String[] allDatas = allString.split(",");
				for (int i = 0; i < allDatas.length; i++) {
					if (!allDatas[i].equals("")) {
						String allName = allDatas[i];
						allName = Library.stringProcess(allName);
						Allergene all = this.getAllByName(allName, this.model.getListAll());
						if (null != all) {
							produit.addAller(all);
						}
					}
				}
			}

			if (lineDatas.length >= 30) {
				String addString = lineDatas[29];
				String[] addDatas = addString.split(" - ");

				for (int i = 0; i < addDatas.length; i++) {
					if (!addDatas[i].equals("")) {
						String addName = addDatas[i];
						addName = Library.stringProcess(addName);
						Additif add = this.getAddByName(addName, this.model.getListAdd());
						if (null != add) {
							produit.getAdditifs().add(add);
							// TODO modifier
						}
					}
				}
			}
			this.model.getListProd().add(produit);
			compteur++;
		}
		
		vue.displayMessage("Début sauvegarde après 2e boucle");
		this.model.getProdDao().addListToDb(this.model.getListProd(), this.model.getEm());

		return true;
	}

	private Ingredient getIngByName(String ingName, List<Object> list) {
		Ingredient ingredient = new Ingredient(ingName);
		for (Object object : list) {
			Ingredient ing = (Ingredient) object;
			if (ing.equals(ingredient)) {
				return ing;
			}
		}

		return null;
	}

	private Allergene getAllByName(String allName, List<Object> list) {
		Allergene allergene = new Allergene(allName);
		for (Object object : list) {
			Allergene all = (Allergene) object;
			if (all.equals(allergene)) {
				return all;
			}
		}

		return null;
	}

	private Additif getAddByName(String addName, List<Additif> list) {
		Additif additif = new Additif(addName);
		for (Object object : list) {
			Additif add = (Additif) object;
			if (add.equals(additif)) {
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
		List<Categorie> listToReturn = this.model.getCatDao().getList(this.model.getEm());
		return listToReturn;
	}

	public List<Marque> getMarqList() {
		List<Marque> listToReturn = this.model.getMarqDao().getList(this.model.getEm());
		return listToReturn;
	}

	public List<Additif> getAddList() {
		List<Additif> listToReturn = this.model.getAddDao().getList(this.model.getEm());
		return listToReturn;
	}

	public List<Allergene> getAllList() {
		List<Allergene> listToReturn = this.model.getAllDao().getList(this.model.getEm());
		return listToReturn;
	}

	public List<Ingredient> getIngList() {
		List<Ingredient> listToReturn = this.model.getIngDao().getList(this.model.getEm());
		return listToReturn;
	}

	public List<Produit> getProdList() {
		List<Produit> listToReturn = this.model.getProdDao().getList(this.model.getEm());
		return listToReturn;
	}

	public void emptyTables() {
		System.out.println("  Vidage des tables");
		this.model.getProdDao().emptyTable(this.model.getEm());
		this.model.getCatDao().emptyTable(this.model.getEm());
		this.model.getMarqDao().emptyTable(this.model.getEm());
		this.model.getAddDao().emptyTable(this.model.getEm());
		this.model.getAllDao().emptyTable(this.model.getEm());
		this.model.getIngDao().emptyTable(this.model.getEm());
	}

	public Boolean isTablesNotEmpty() {
		Boolean isAddTableFull = this.model.getAddDao().getElementNb(this.model.getEm()) > 0;
		Boolean isAllTableFull = this.model.getAllDao().getElementNb(this.model.getEm()) > 0;
		Boolean isCatTableFull = this.model.getCatDao().getElementNb(this.model.getEm()) > 0;
		Boolean isIngTableFull = this.model.getIngDao().getElementNb(this.model.getEm()) > 0;
		Boolean isMarqTableFull = this.model.getMarqDao().getElementNb(this.model.getEm()) > 0;
		Boolean isProdTableFull = this.model.getProdDao().getElementNb(this.model.getEm()) > 0;
		return (isAddTableFull && isAllTableFull && isCatTableFull && isIngTableFull && isMarqTableFull
				&& isProdTableFull);
	}

}
