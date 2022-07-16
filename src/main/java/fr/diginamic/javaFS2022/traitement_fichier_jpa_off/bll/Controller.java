package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Model;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CsvDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.DbDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class Controller {
	
	private Vue vue;
	private Model model;
	
	//DbDao dbDao;
	
	// TODO ajouter au lancement si la base existe et est op pour proposer un menu different
	
	public void init() {
		this.model = new Model();
		this.vue = new Vue();
		this.model.init();
		this.vue.init();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		this.model.setDbDao(new DbDao());
		this.model.getDbDao().init();
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
				List<Categorie> listCat = this.model.getDbDao().getCatList();
				this.vue.displayCatList(listCat);
				this.vue.waitForCToContinue();
				break;
				
			case '2':
				//dbDao = new DbDao();
				List<Marque> listMarq = this.model.getDbDao().getMarqList();
				this.vue.displayMarqList(listMarq);
				this.vue.waitForCToContinue();
				break;
			}
			
		} while (!quit);
		//this.vue.waitForCToContinue();
		this.model.getDbDao().close();
		this.vue.closeScanner();
		this.vue.displayQuitMessage();
	}
}