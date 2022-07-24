package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.util.List;

import javax.persistence.EntityManager;

public interface IPojoDao {
	
	public void addListToDb(List<?> listToAdd, EntityManager em);
	
	public Object getByName(String nom, EntityManager em);
	
	public List<?> getList(EntityManager em);
	
	public void emptyTable(EntityManager em);
	
	public Long getElementNb(EntityManager em);

	//void addListToDb(List<Additif> listToAdd, EntityManager em);

}
