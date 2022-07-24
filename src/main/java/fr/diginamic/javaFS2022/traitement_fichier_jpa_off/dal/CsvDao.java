package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.dal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvDao {

	public CsvDao() {}
	
	public List<String> generateListFromCsv() throws IOException {
		Path path = Paths.get("/home/jacques/springToolsSuite/workSTS/traitement-fichier-jpa-off/src/main/resources/csv/open-food-facts.csv");
		boolean isFile = Files.isRegularFile(path);
		boolean isReadable = Files.isReadable(path);
		boolean isFileExists = Files.exists(path);
		if(!isFileExists || !isFile || !isReadable) {
			return null;
		}
		else {
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.remove(0);
			return lines;
		}
		
	}

}
