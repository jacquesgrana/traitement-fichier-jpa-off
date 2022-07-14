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
	
	@Column(nullable = false)
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
	
	public boolean Equals(Produit object) {
		if(this.nom.equals(object.getNom())) {
			return true;
		}
		else {
			return false;		
		}
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the grade
	 */
	public GradeNutrition getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(GradeNutrition grade) {
		this.grade = grade;
	}
	
	

}
