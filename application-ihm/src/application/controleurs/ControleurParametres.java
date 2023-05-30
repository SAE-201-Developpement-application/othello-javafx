/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.Othello;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurParametres {

	@FXML
	private ImageView iconePositionsPossibles;
	
	@FXML
	private ImageView iconePositionsPossiblesActivee;
	
	@FXML
	private ImageView iconePionsEnlevables;
	
	@FXML
	private ImageView iconePionsEnlevablesActivee;
	
	@FXML
	private ImageView iconeMusique;
	
	@FXML
	private ImageView iconeMusiqueDesactivee;
	
	@FXML
	private ImageView iconeSon;
	
	@FXML
	private ImageView iconeSonDesactivee;
	
	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		Othello.activerMenuPrincipal(); 
	}
	
	/**
	 * Échanger les visibilités de l'image 1 et 2.
	 * 
	 * @param image1 Première image à échanger
	 * @param image2 Seconde image à échanger
	 */
	private void echangerVisibilite(ImageView image1, ImageView image2) {
		image1.setVisible(image1.isVisible() == true ? false : true);
		image2.setVisible(image2.isVisible() == true ? false : true);
	}
	
	@FXML
	private void gererClicPositionsPossibles() {
		// Ajouter ou retirer un contour vert pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconePositionsPossibles, iconePositionsPossiblesActivee);
	}
	
	@FXML
	private void gererClicPionsEnlevables() {
		// Ajouter ou retirer un contour vert pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconePionsEnlevables, iconePionsEnlevablesActivee);
	}
	
	@FXML
	private void gererClicMusique() {
		// Ajouter ou retirer une croix rouge pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconeMusique, iconeMusiqueDesactivee);
	}
	
	@FXML
	private void gererClicSon() {
		// Ajouter ou retirer une croix rouge pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconeSon, iconeSonDesactivee);
	}
	
	@FXML
	private void gererClicCredits() {
		
		// TODO lancer une alerte avec les crédits de l'app
		
	}
	
	@FXML
	private void gererClicValiderParametres() {
		
	}

}