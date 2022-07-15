package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm;

import java.util.Scanner;

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
		else {

			System.out.println("  * 1 : Afficher les catégories                *");
			
		}
		System.out.println("  * Q : Quitter l'application                  *");
		System.out.println("  *                                            *");
		System.out.println("  **********************************************");
		System.out.print("\n  Saisir votre choix et validez : ");
	}
	
	public void displayQuitMessage() {
		System.out.println("\n  Fin du programme.");
	}
	
	public void displayContinue() {
		System.out.print("\n  'C' pour continuer : ");
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
		System.out.println("\n  " + message);
	}

}