package fr.diginamic.javaFS2022.traitement_fichier_jpa_off;

import fr.diginamic.javaFS2022.traitement_fichier_jpa_off.bll.Controller;

/**
 * Hello world!
 *
 */
public class IntegrationOpenFoodFacts 
{
    public static void main( String[] args ) {
    	Controller controller = new Controller();
    	controller.init();
    	controller.run();
    }
}
