package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Model;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CsvDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.DbDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class Controller {
	
	private Vue vue;
	private Model model;
	
	// TODO ajouter 3e categorie dans enum huiledepalme : "NSP"
	// TODO modifier dbDao 2e boucle pour mettre NSP qd pas renseigné
	
	public void init() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		this.model = new Model();
		this.vue = new Vue();
		this.model.init();
		this.vue.init();
		this.model.setDbDao(new DbDao());
		this.model.getDbDao().init();
		this.model.setIsDataLoaded(this.model.getDbDao().isTablesFull());
		//System.out.println("booléen : " + this.model.getIsDataLoaded());
		//this.vue.waitForCToContinue();
		// TODO ajouter appel methode de dbDao qui set le booleen isFilesLoaded selon si la base est pleine ou pas
	}
	
	public void run() {
		boolean quit = false;
		char choice = ' ';
		do {
			this.vue.displayGeneralMenu(this.model.getIsDataLoaded());
			choice = this.vue.inputChar();
			switch (choice) {
			case 'Q':
			case 'q' :
				quit = true;
				break;
			case '0':
				CsvDao csvDao = new CsvDao();
				// TODO ajouter vidage des tables : 
				this.model.getDbDao().emptyTables();
				List<String> lines;
				try {
					lines = csvDao.generateListFromCsv();
					model.setIsDataLoaded(this.model.getDbDao().populateDb(lines, this.vue)); // TODO enlever set booleen
					vue.displayMessage(this.model.getDbDao().getLoadReport());
					vue.waitForCToContinue();
				} 
				catch (IOException e) {
					this.vue.displayMessage("Problème sur le chargement du fichier csv : " + e.getMessage());
					//System.out.println("Problème sur le chargement du fichier csv : " + e.getMessage());
					model.setIsDataLoaded(false);
				}
				break;
				
			case '1':
				//dbDao = new DbDao();
				if (this.model.getIsDataLoaded()) {
					List<Categorie> listCat = this.model.getDbDao().getCatList();
					this.vue.displayCatList(listCat);
					this.vue.waitForCToContinue();
				}
				break;
			case '2':
				if (this.model.getIsDataLoaded()) {
					List<Marque> listMarq = this.model.getDbDao().getMarqList();
					this.vue.displayMarqList(listMarq);
					this.vue.waitForCToContinue();
					
				}
				break;
			case '3':
				if (this.model.getIsDataLoaded()) {
					List<Additif> listAdd = this.model.getDbDao().getAddList();
					this.vue.displayAddList(listAdd);
					this.vue.waitForCToContinue();
				}
				break;
			case '4':
				if (this.model.getIsDataLoaded()) {
					List<Allergene> listAll = this.model.getDbDao().getAllList();
					this.vue.displayAllList(listAll);
					this.vue.waitForCToContinue();
				}
				break;
			case '5':
				if (this.model.getIsDataLoaded()) {
					List<Ingredient> listIng = this.model.getDbDao().getIngList();
					this.vue.displayIngList(listIng);
					this.vue.waitForCToContinue();
				}
				break;
			case '6':
				if (this.model.getIsDataLoaded()) {
					List<Produit> listProd = this.model.getDbDao().getProdList();
					this.vue.displayProdList(listProd);
					this.vue.waitForCToContinue();
				}
				break;
			}
			
		} while (!quit);
		//this.vue.waitForCToContinue();
		this.model.getDbDao().close();
		this.vue.closeScanner();
		this.vue.displayQuitMessage();
	}
}