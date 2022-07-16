package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;

public class Vue {
	private Scanner sc;
	
	public void init() {
		this.sc = new Scanner(System.in);
	}
	
	public void closeScanner() {
		this.sc.close();
	}
	
	public char inputChar() {
		char charToReturn;
		String input = this.sc.nextLine();
		if(input.length() > 0) {
			charToReturn = input.charAt(0);
		}
		else {
			charToReturn = ' ';
		}
		return charToReturn;
	}
	
	public void displayGeneralMenu(boolean isFilesLoaded) {
		System.out.println("\n\n\n\n");
		System.out.println("  **********************************************");
		System.out.println("  *                                            *");
		System.out.println("  * Traitement Données Openfoodfacts           *");
		System.out.println("  *                                            *");
		System.out.println("  * Menu :                                     *");
		System.out.println("  *                                            *");
		if (!isFilesLoaded) {
			System.out.println("  * 0 : Initialisation des données             *");
		} 
		System.out.println("  * 1 : Afficher les catégories                *");
		System.out.println("  * 2 : Afficher les marques                   *");
		System.out.println("  * Q : Quitter l'application                  *");
		System.out.println("  *                                            *");
		System.out.println("  **********************************************");
		System.out.print("\n  Saisir votre choix et valider : ");
	}
	
	public void displayQuitMessage() {
		System.out.println("\n  Fin du programme.");
	}
	
	public void displayContinue() {
		System.out.print("\n  Entrer 'C' et valider pour continuer : ");
	}

	public void waitForCToContinue() {
		char choice;
		do {
			this.displayContinue();
			String choiceString2 = this.sc.next();
			choice = choiceString2.charAt(0);
		} 
		while (choice != 'C' && choice != 'c');
	}

	public void displayMessage(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("\n  ").append(message);
		System.out.println(builder.toString());
	}

	public void displayCatList(List<Categorie> listCat) {
		System.out.println("\n\n  Liste des Catégories :\n");
		for (Categorie cat : listCat) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(cat.getNom());
			System.out.println(builder.toString());
		}
	}

	public void displayMarqList(List<Marque> listMarq) {
		System.out.println("\n\n  Liste des Catégories :\n");
		for (Marque marq : listMarq) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(marq.getNom());
			System.out.println(builder.toString());
		}
	}

}