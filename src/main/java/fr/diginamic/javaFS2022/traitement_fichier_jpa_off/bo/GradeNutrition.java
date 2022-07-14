package fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bo;

public enum GradeNutrition {
	A, B, C, D, E, F;
	
	public static GradeNutrition getByChar(char c) {
		switch (c){
		case 'a' :
			return GradeNutrition.A;
		case 'b' :
			return GradeNutrition.B;
		case 'c' :
			return GradeNutrition.C;
		case 'd' :
			return GradeNutrition.D;
		case 'e' :
			return GradeNutrition.E;
		case 'f' :
			return GradeNutrition.F;
		default :
			return null;
		}
	}
}
