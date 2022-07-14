package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvDao {

	public CsvDao() {}
	
	public Boolean loadDatasFromCsv() {
		Path path = Paths.get("/home/jacques/springToolsSuite/workSTS/traitement-fichier-jpa-off/src/main/resources/csv/open-food-facts.csv");
		boolean isFile = Files.isRegularFile(path);
		boolean isReadable = Files.isReadable(path);
		boolean isFileExists = Files.exists(path);
		if(!isFileExists || !isFile || !isReadable) {
			System.out.println("Fichier csv ko");
			return false;
		}
		else {
			System.out.println("Fichier csv prêt");
			return true;
		}
		
	}

}
