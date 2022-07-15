package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String nom;
	
	//produits
	@OneToMany(mappedBy="categorie")
	private Set<Produit> produits = new HashSet<>();

	public Categorie() {}

	/**
	 * @param nom
	 */
	public Categorie(String nom) {
		this.nom = nom;
	}
	
	@Override
	public  boolean equals(Object o) {
		Categorie cat = (Categorie) o;
		//boolean equals = this.nom.equals(cat.getNom());
		boolean equals = cat.getNom().equals(this.nom);
		return equals;
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

	/**
	 * @return the produits
	 */
	public Set<Produit> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append("]");
		return builder.toString();
	}

}
