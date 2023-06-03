/*
 * ControleurChoixPseudosContreJoueur .java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;

import javafx.fxml.FXMLLoader;
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
public class ControleurChoixPseudoContreIA extends ControleurPrincipal {
	
	/* Chaine de caractere pour l'erreur de pseudo */
	private final String REGLES_PSEUDO 
	= "Veuillez entrer un nom contenant 2 à 16 caractères.";
	
	/* Booleen pour savoir si le nom du joueur 1 est bien saisi */
	private boolean nomOKJoueur1 = false;
	
	@FXML
	private TextField pseudoJoueur1;
	
	@FXML
	private ImageView cocheJoueur;
	
	@FXML
	private ImageView croixJoueur;

	@FXML
	private void gererClicRetourNiveauOrdinateur() {		
		// échanger la vue courante avec celle du niveau Ordinateur
		GestionVues.activerNiveauOrdinateur(); 
		
		/* Remise à zéro des valeurs pour éviter les bugs quand on change de mode de jeu */
		pseudoJoueur1.setText("");
		modelePrincipal.setNomJoueur1("");
		modelePrincipal.setNomJoueur2("");
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {
		
		String nomJoueur1 = pseudoJoueur1.getText();
		
		if (nomJoueur1.isEmpty()) {
			croixJoueur.setVisible(false);
			cocheJoueur.setVisible(false);
			nomOKJoueur1 = false;
		} else if (nomJoueur1.length() > 1 && nomJoueur1.length() <= 16) {
			croixJoueur.setVisible(false);
			cocheJoueur.setVisible(true);
			modelePrincipal.setNomJoueur1(nomJoueur1);
			nomOKJoueur1 = true;
		} else {
			cocheJoueur.setVisible(false);
			croixJoueur.setVisible(true);
			nomOKJoueur1 = false;
		}
	}
	
	
	@FXML
	private void gererClicJouer() {	
				
		if (nomOKJoueur1) {			
			modeleJeu.setPartieCommence(true);
			modeleJeu.setPartieOrdinateur(true);;
			pseudoJoueur1.setText(null);
			cocheJoueur.setVisible(false);
			GestionVues.activerJeu();
			nomOKJoueur1 = false;
		} else {
			Alert boitePseudoIncompatible = new Alert(Alert.AlertType.ERROR);
			
			Stage stage = (Stage) boitePseudoIncompatible.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
			
			boitePseudoIncompatible.setTitle("Othello - Pseudonyme invalide");
			boitePseudoIncompatible.setHeaderText(REGLES_PSEUDO);
			boitePseudoIncompatible.showAndWait();		
		}
		

	}
}