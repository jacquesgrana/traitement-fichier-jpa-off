package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Model;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CsvDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm.Vue;

public class Controller {
	
	private Vue vue;
	private Model model;
	
	
	public void init() {
		this.model = new Model();
		this.vue = new Vue();
		this.model.init();
		this.vue.init();
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
				Boolean isCsvLoaded = csvDao.loadDatasFromCsv();
				// TODO appels dao des classes pojo pour peupler la bd
				this.model.setIsDataLoaded(true);
				break;
			}
			
		} while (!quit);
		//this.vue.waitForCToContinue();
		
		this.vue.closeScanner();
		this.vue.displayQuitMessage();
	}
}