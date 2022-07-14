package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nom;
	
	@Enumerated
	private GradeNutrition grade;
	
	//categorie
	
	//marque
	
	//ingredients
	
	//additifs
	
	//allegenes
	
	//presence huile de palme --> creer enum oui/non

	public Produit() {}

}
