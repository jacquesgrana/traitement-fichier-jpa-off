package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.ihm;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Additif;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Allergene;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Categorie;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Ingredient;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Marque;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo.Produit;

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
		System.out.println("  * 3 : Afficher les additifs                  *");
		System.out.println("  * 4 : Afficher les allergènes                *");
		System.out.println("  * 5 : Afficher les ingrédients               *");
		System.out.println("  * 6 : Afficher les produits                  *");
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

	public void displayAddList(List<Additif> listAdd) {
		System.out.println("\n\n  Liste des Additifs :\n");
		for (Additif add : listAdd) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(add.getNom());
			System.out.println(builder.toString());
		}
		
	}
	
	public void displayAllList(List<Allergene> listAll) {
		System.out.println("\n\n  Liste des Allergènes :\n");
		for (Allergene all : listAll) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(all.getNom());
			System.out.println(builder.toString());
		}
		
	}
	
	public void displayIngList(List<Ingredient> listIng) {
		System.out.println("\n\n  Liste des Ingrédients :\n");
		for (Ingredient ing : listIng) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(ing.getNom());
			System.out.println(builder.toString());
		}
		
	}
	
	public void displayProdList(List<Produit> listProd) {
		System.out.println("\n\n  Liste des Produits :\n");
		for (Produit prod : listProd) {
			StringBuilder builder = new StringBuilder();
			builder.append("  ").append(prod.getNom());
			System.out.println(builder.toString());
		}
		
	}

}