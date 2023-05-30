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
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		Othello.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicPositionsPossibles() {
		// ajouter un contour vert pour confirmer l'action
		iconePositionsPossibles.setVisible(iconePositionsPossibles.isVisible()
										   == true ? false : true);
		iconePositionsPossiblesActivee.setVisible(iconePositionsPossiblesActivee
												  .isVisible() == true
												  ? false : true);
	}
	
	@FXML
	private void gererClicPionsEnlevables() {
		// ajouter un contour vert pour confirmer l'action
		iconePionsEnlevables.setVisible(iconePionsEnlevables.isVisible()
										== true ? false : true);
		iconePionsEnlevablesActivee.setVisible(iconePionsEnlevablesActivee
											   .isVisible() == true
											   ? false : true);
	}
	
	@FXML
	private void gererClicCredits() {
		
		// TODO lancer une alerte avec les crédits de l'app
		
	}
	
	@FXML
	private void gererClicValiderParametres() {
		
	}

}