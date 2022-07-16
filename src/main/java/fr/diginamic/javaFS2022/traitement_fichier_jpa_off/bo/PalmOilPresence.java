package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

public enum PalmOilPresence {
	OUI, NON, NSP;
	
	public static PalmOilPresence getOilPresenceByChar(char c) {
		switch (c){
		case '0' :
			return PalmOilPresence.NON;
		case '1' :
			return PalmOilPresence.OUI;
		default :
			return PalmOilPresence.NSP;
		}
	}
}
