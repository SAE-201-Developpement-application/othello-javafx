/*
 * ControleurChoixPseudosContreJoueur.java                      				            25 mai 2023
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
	
	
	
	
	@FXML
	private TextField pseudoJoueur;
	
	@FXML
	private TextField pseudoJoueur2;
	
	@FXML
	private ImageView cocheJoueur1;
	
	@FXML
	private ImageView cocheJoueur2;
	
	@FXML
	private ImageView croixJoueur1;
	
	@FXML
	private ImageView croixJoueur2;

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {	// TODO mettre le déclencheur sur FX builder, je sait plus lequel c'est
		
		String nomJoueur1 = pseudoJoueur.getText();
		
		if (nomJoueur1.isEmpty()) {
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(false);
		} else if (nomJoueur1.length() > 1 && nomJoueur1.length() <= 16) {
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(true);
			modelePrincipal.setNomJoueur1(nomJoueur1);
		} else {
			cocheJoueur1.setVisible(false);
			croixJoueur1.setVisible(true);
			
		}
	}
	
	@FXML
	private void gererMiseAJourNomJoueur2() {	// TODO mettre le déclencheur sur FX builder, je sait plus lequel c'est
	
		String nomJoueur2 = pseudoJoueur2.getText();
		
		if (nomJoueur2.isEmpty()) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(false);
		} else if (nomJoueur2.length() > 1 && nomJoueur2.length() <= 16) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(true);
			modelePrincipal.setNomJoueur1(nomJoueur2);
		} else {
			cocheJoueur2.setVisible(false);
			croixJoueur2.setVisible(true);
			
		}
	}
	
	@FXML
	private void gererClicJouer() {
// PAS BESOIN POUR l'instant
//		final String REGLES_PSEUDO 
//	    = "Veuillez entrer un pseudonyme contenant 2 à 16 caractères.";
//			
//		Alert boitePseudoIncompatible = new Alert(Alert.AlertType.ERROR);
//		
//		Stage stage = (Stage) boitePseudoIncompatible.getDialogPane().getScene().getWindow();
//		stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
//		
//		boitePseudoIncompatible.setTitle("Othello - Pseudonyme invalide");
//		boitePseudoIncompatible.setHeaderText(REGLES_PSEUDO);
//		boitePseudoIncompatible.showAndWait();
		GestionVues.activerJeu();
											      
		
	}
}
