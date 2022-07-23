package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll;

import org.apache.commons.lang3.StringUtils;

public class Library {

	@SuppressWarnings("unused")
	public static String trimBetter(String string) {
		String result = StringUtils.strip(string, "' %-+"); // 1e strip(string, "' %1234567890-+")
		return result;
	}

	public static String removeFromCommaToEnd(String string) {
		int indexComma = string.lastIndexOf(',');
		if(indexComma != -1) {
			string = string.substring(0, indexComma);
		}
		return string;
	}
	
	public static String stringProcess(String string) {
		String toReturn = Library.trimBetter(string);
		toReturn = Library.removeFromCommaToEnd(toReturn);
		toReturn = StringUtils.strip(toReturn, " _*");
		toReturn = toReturn.toLowerCase();
		return toReturn;
	}

}
