package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nom;

	public Ingredient() {}

	/**
	 * @param nom
	 */
	public Ingredient(String nom) {
		this.nom = nom;
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingredient [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append("]");
		return builder.toString();
	}


}
