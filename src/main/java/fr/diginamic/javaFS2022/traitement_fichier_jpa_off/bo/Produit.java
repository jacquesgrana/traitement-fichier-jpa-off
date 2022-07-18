package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	@ManyToOne(cascade = CascadeType.ALL)
	private Categorie categorie;
	
	//marque
	@ManyToOne(cascade = CascadeType.ALL)
	private Marque marque;
	
	//ingredients
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Possede_Ing",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_ingredient", referencedColumnName="id"))
	private Set<Ingredient> ingredients = new HashSet<>();		
	
	//additifs
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Possede_Add",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_additif", referencedColumnName="id"))
	private Set<Additif> additifs = new HashSet<>();
	
	//allergenes
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Possede_All",
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="id_allergene", referencedColumnName="id"))
	private Set<Allergene> allergenes = new HashSet<>();

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
	
	// TODO ajouter methodes addAdd et removeAdd
	
	public void addAdd(Additif additif) {
		if (!this.additifs.contains(additif)) {
			this.additifs.add(additif);
		}
		if (!additif.getProduits().contains(this)) {
			additif.getProduits().add(this);
		}
	}
	
	public void removeAdd(Additif additif) {
		if (this.additifs.contains(additif)) {
			this.additifs.remove(additif);
		}
		if (additif.getProduits().contains(this)) {
			additif.getProduits().remove(this);
		}
	}
	
	// TODO ajouter methodes addAll et removeAll
	
	public void addAller(Allergene allergene) {
		if (!this.allergenes.contains(allergene)) {
			this.allergenes.add(allergene);
		}
		if (!allergene.getProduits().contains(this)) {
			allergene.getProduits().add(this);
		}
	}
	
	public void removeAller(Allergene allergene) {
		if (this.allergenes.contains(allergene)) {
			this.allergenes.remove(allergene);
		}
		if (allergene.getProduits().contains(this)) {
			allergene.getProduits().remove(this);
		}
	}
	
	// TODO ajouter methodes addIng et removeIng
	
	public void addIng(Ingredient ingredient) {
		if (!this.ingredients.contains(ingredient)) {
			this.ingredients.add(ingredient);
		}
		if (!ingredient.getProduits().contains(this)) {
			ingredient.getProduits().add(this);
		}
	}
	
	public void removeIng(Ingredient ingredient) {
		if (this.ingredients.contains(ingredient)) {
			this.ingredients.remove(ingredient);
		}
		if (ingredient.getProduits().contains(this)) {
			ingredient.getProduits().remove(this);
		}
	}
	
	// TODO ajouter methodes addCat et removeCat
	
	public void addCat(Categorie categorie) {
		if (null != this.categorie) {
			categorie.getProduits().remove(this);
		}
		this.categorie = categorie;
		if (null != this.categorie) {
			categorie.getProduits().add(this);
		}
	}

	public void removeCat(Categorie categorie) {
		categorie.getProduits().remove(this);
		this.categorie = null;
	}
	
	

	// TODO ajouter methodes addMarq et removeMarq


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
