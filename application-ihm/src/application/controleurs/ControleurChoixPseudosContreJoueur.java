/*
 * ControleurChoixPseudosContreJoueur .java                      				            25 mai 2023
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
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.util.Optional;


/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurChoixPseudosContreJoueur extends ControleurPrincipal {
	
	
	private final String REGLES_PSEUDO 
	= "Veuillez entrer un nom contenant 2 à 16 caractères.";
	
	@FXML
	private TextField pseudoJoueur;

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {	// TODO mettre le déclencheur sur FX builder, je sait plus lequel c'est
		String nomJoueur1 = pseudoJoueur.getText();
		if(nomJoueur1.length() > 1 && nomJoueur1.length() <= 16) {
			modelePrincipal.setNomJoueur1(nomJoueur1);
		} else {
			Alert boitePseudoIncompatible = new Alert(Alert.AlertType.ERROR,
											      REGLES_PSEUDO);
		}
	}
	
	@FXML
	private void gererMiseAJourNomJoueur2() {	// TODO mettre le déclencheur sur FX builder, je sait plus lequel c'est
		// TODO gérer la maj
	}
	
	@FXML
	private void gererClicJouer() {		
		// TODO activer vue Jeu
	}
}