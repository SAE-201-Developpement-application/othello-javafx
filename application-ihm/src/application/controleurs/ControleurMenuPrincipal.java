/*
 * ControleurMenuPrincipal.java                				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

import application.vues.GestionVues;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurMenuPrincipal extends ControleurPrincipal {

	/* 
	 * Le texte sur le bouton de sauvegarde 
	 * pour les informations de la partie sauvegardée 
	 */
	@FXML
	private Text infosPartie;
	
	@FXML
	private void gererClicFermer() {
		System.out.println("\nFermeture de l'application.");
		Platform.exit();
	}
	
	@FXML
	private void gererClicParametres() {		
		// échanger la vue courante avec celle des paramètres
		GestionVues.activerParametres(); 
	}
	
	@FXML
	private void gererClicPartieOrdinateur() {
		// échanger la vue courante avec celle du niveau de l'ordinateur
		GestionVues.activerNiveauOrdinateur(); 
	}
	
	@FXML
	private void gererClicPartieJoueur() {
		GestionVues.activerChoixPseudosContreJoueur();
	}
	
	@FXML
	private void generer() {
		
	}
	
	@FXML
	private void gererClicCorbeille() {
		
		infosPartie.setText("Aucune partie sauvegardée");
		// TODO  active la corbeille pour supprimer la partie sauvegardée dans ModeleSauvegardes
	}
	
	@FXML
	private void gererClicReprendrePartie() {
		
		final String MESSAGE_EN_COURS_DEV =
		"""
		Cette fonctionnalité est toujours en cours de développement.
				
		Elle ne sera probablement jamais développée sauf si Loïc reçoit 
		une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
		""";
		
		Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION,
								      MESSAGE_EN_COURS_DEV);
		
		boiteAlerte.getDialogPane().getStylesheets().add(getClass()
	               .getResource("../vues/application.css").toExternalForm());

		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/EnConstruction.png"));

		boiteAlerte.setTitle("Othello - En cours de développement");
		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
		
		// Si une partie sauvegardée existe
		if (modelePrincipal.getSauvegardeExiste()) {
			boiteAlerte.showAndWait();
		} else {
			infosPartie.setText("Aucune partie sauvegardée");
		}
		// va chercher la partie sauvegardée dans le fichier ModeleSauvegardes
	}

}