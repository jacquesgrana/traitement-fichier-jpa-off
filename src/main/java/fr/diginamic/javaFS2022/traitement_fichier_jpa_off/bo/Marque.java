package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marque {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nom;

	// produits
	@OneToMany(mappedBy = "marque")
	private Set<Produit> produits = new HashSet<>();

	public Marque() {
	}

	/**
	 * @param nom
	 */
	public Marque(String nom) {
		this.nom = nom;
	}

	public boolean Equals(Marque object) {
		if (this.nom.equals(object.getNom())) {
			return true;
		} else {
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
		builder.append("Marque [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append("]");
		return builder.toString();
	}

}
