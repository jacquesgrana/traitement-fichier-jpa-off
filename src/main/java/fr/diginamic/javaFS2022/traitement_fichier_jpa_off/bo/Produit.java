package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String nom;
	
	@Enumerated(EnumType.STRING)
	private GradeNutrition grade;
	
	@Enumerated(EnumType.STRING)
	private PalmOilPresence palmOil;
	
	//categorie
	@ManyToOne
	private Categorie categorie;
	
	//marque
	@ManyToOne
	private Marque marque;
	
	//ingredients
	@ManyToMany
	@JoinTable(name="Possede_Ing",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_ingredient", referencedColumnName="id"))
	private Set<Ingredient> ingredients = new HashSet<>();		
	
	//additifs
	//@ManyToMany(mappedBy="produits")
	@ManyToMany
	@JoinTable(name="Possede_Add",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_additif", referencedColumnName="id"))
	private Set<Additif> additifs = new HashSet<>();
	
	//allergenes
	//@ManyToMany(mappedBy="produits")
	@ManyToMany
	@JoinTable(name="Possede_All",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_allergene", referencedColumnName="id"))
	private Set<Allergene> allergenes = new HashSet<>();
	
	//presence huile de palme --> creer enum oui/non

	public Produit() {}
	
	/**
	 * @param nom
	 */
	public Produit(String nom) {
		this.nom = nom;
	}

	

	/**
	 * @param nom
	 * @param grade
	 * @param categorie
	 * @param marque
	 */
	public Produit(String nom, GradeNutrition grade, Categorie categorie, Marque marque) {
		this.nom = nom;
		this.grade = grade;
		this.categorie = categorie;
		this.marque = marque;
	}

	@Override
	public  boolean equals(Object o) {
		if (o.getClass().equals(Produit.class)) {
			Produit prod = (Produit) o;
			boolean equals = prod.getNom().equals(this.nom);
			return equals;
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

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	/**
	 * @return the ingredients
	 */
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the additifs
	 */
	public Set<Additif> getAdditifs() {
		return additifs;
	}

	/**
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(Set<Additif> additifs) {
		this.additifs = additifs;
	}

	/**
	 * @return the allergenes
	 */
	public Set<Allergene> getAllergenes() {
		return allergenes;
	}

	/**
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(Set<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	/**
	 * @return the palmOil
	 */
	public PalmOilPresence getPalmOil() {
		return palmOil;
	}

	/**
	 * @param palmOil the palmOil to set
	 */
	public void setPalmOil(PalmOilPresence palmOil) {
		this.palmOil = palmOil;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produit [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", marque=");
		builder.append(marque);
		builder.append("]");
		return builder.toString();
	}

}
