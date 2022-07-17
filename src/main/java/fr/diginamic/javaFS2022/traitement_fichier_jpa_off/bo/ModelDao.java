package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.AdditifDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.AllergeneDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.CategorieDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.IngredientDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.MarqueDao;
import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal.ProduitDao;

public class ModelDao {

	private List<Object> listCat;
	private List<Object> listMarq;
	private List<Object> listAdd;
	private List<Object> listAll;
	private List<Object> listIng;
	private List<Object> listProd;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private CategorieDao catDao;
	private MarqueDao marqDao;
	private AdditifDao addDao;
	private AllergeneDao allDao;
	private IngredientDao ingDao;
	private ProduitDao prodDao;
	
	public ModelDao() {}

	public void init() {
		this.listCat = new ArrayList<>();
		this.listMarq = new ArrayList<>();
		this.listAdd = new ArrayList<>();
		this.listAll = new ArrayList<>();
		this.listIng = new ArrayList<>();
		this.listProd = new ArrayList<>();
		this.emf = Persistence.createEntityManagerFactory("jpa_traitement_fichier");
		em = this.emf.createEntityManager();
		catDao = new CategorieDao();
		marqDao = new MarqueDao();
		addDao = new AdditifDao();
		allDao = new AllergeneDao();
		ingDao = new IngredientDao();
		prodDao = new ProduitDao();
	}

	/**
	 * @return the listCat
	 */
	public List<Object> getListCat() {
		return listCat;
	}

	/**
	 * @param listCat the listCat to set
	 */
	public void setListCat(List<Object> listCat) {
		this.listCat = listCat;
	}

	/**
	 * @return the listMarq
	 */
	public List<Object> getListMarq() {
		return listMarq;
	}

	/**
	 * @param listMarq the listMarq to set
	 */
	public void setListMarq(List<Object> listMarq) {
		this.listMarq = listMarq;
	}

	/**
	 * @return the listAdd
	 */
	public List<Object> getListAdd() {
		return listAdd;
	}

	/**
	 * @param listAdd the listAdd to set
	 */
	public void setListAdd(List<Object> listAdd) {
		this.listAdd = listAdd;
	}

	/**
	 * @return the listAll
	 */
	public List<Object> getListAll() {
		return listAll;
	}

	/**
	 * @param listAll the listAll to set
	 */
	public void setListAll(List<Object> listAll) {
		this.listAll = listAll;
	}

	/**
	 * @return the listIng
	 */
	public List<Object> getListIng() {
		return listIng;
	}

	/**
	 * @param listIng the listIng to set
	 */
	public void setListIng(List<Object> listIng) {
		this.listIng = listIng;
	}

	/**
	 * @return the listProd
	 */
	public List<Object> getListProd() {
		return listProd;
	}

	/**
	 * @param listProd the listProd to set
	 */
	public void setListProd(List<Object> listProd) {
		this.listProd = listProd;
	}

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * @param emf the emf to set
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * @return the catDao
	 */
	public CategorieDao getCatDao() {
		return catDao;
	}

	/**
	 * @param catDao the catDao to set
	 */
	public void setCatDao(CategorieDao catDao) {
		this.catDao = catDao;
	}

	/**
	 * @return the marqDao
	 */
	public MarqueDao getMarqDao() {
		return marqDao;
	}

	/**
	 * @param marqDao the marqDao to set
	 */
	public void setMarqDao(MarqueDao marqDao) {
		this.marqDao = marqDao;
	}

	/**
	 * @return the addDao
	 */
	public AdditifDao getAddDao() {
		return addDao;
	}

	/**
	 * @param addDao the addDao to set
	 */
	public void setAddDao(AdditifDao addDao) {
		this.addDao = addDao;
	}

	/**
	 * @return the allDao
	 */
	public AllergeneDao getAllDao() {
		return allDao;
	}

	/**
	 * @param allDao the allDao to set
	 */
	public void setAllDao(AllergeneDao allDao) {
		this.allDao = allDao;
	}

	/**
	 * @return the ingDao
	 */
	public IngredientDao getIngDao() {
		return ingDao;
	}

	/**
	 * @param ingDao the ingDao to set
	 */
	public void setIngDao(IngredientDao ingDao) {
		this.ingDao = ingDao;
	}

	/**
	 * @return the prodDao
	 */
	public ProduitDao getProdDao() {
		return prodDao;
	}

	/**
	 * @param prodDao the prodDao to set
	 */
	public void setProdDao(ProduitDao prodDao) {
		this.prodDao = prodDao;
	}

}
