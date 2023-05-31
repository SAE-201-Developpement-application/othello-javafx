/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurChoixPseudosContreJoueur extends ControleurPrincipal {

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {		// TODO mettre le déclencheur sur FX builder, je sait plus lequel c'est
		// TODO gérer la maj
	}
	
	@FXML
	private void gererClicJouer() {		
		// TODO activer vue Jeu
	}
}