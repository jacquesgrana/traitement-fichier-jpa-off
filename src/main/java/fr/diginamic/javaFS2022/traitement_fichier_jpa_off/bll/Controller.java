package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Model;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CsvDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.DbDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class Controller {
	
	private Vue vue;
	private Model model;
	
	
	public void init() {
		this.model = new Model();
		this.vue = new Vue();
		this.model.init();
		this.vue.init();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
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
				DbDao dbDao = new DbDao();
				List<String> lines;
				try {
					lines = csvDao.generateListFromCsv();
					model.setIsDataLoaded(dbDao.populateDb(lines));
					vue.displayMessage(dbDao.getLoadReport());
					vue.waitForCToContinue();
				} 
				catch (IOException e) {
					System.out.println("Problème sur le chargement du fichier csv : " + e.getMessage());
					model.setIsDataLoaded(false);
				}
				// TODO appels dao des classes pojo pour peupler la bd
				//this.model.setIsDataLoaded(true);
				break;
			}
			
		} while (!quit);
		//this.vue.waitForCToContinue();
		
		this.vue.closeScanner();
		this.vue.displayQuitMessage();
	}
}