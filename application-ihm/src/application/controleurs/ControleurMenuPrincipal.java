/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.Othello;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurMenuPrincipal {

	/* 
	 * Le texte sur le bouton de sauvegarde 
	 * pour les informations de la partie sauvegardée 
	 */
	@FXML
	private Text infosPartie;
	
	@FXML
	private void gererClicFermer() {		
		// ferme la vue courante
		// TODO : chercher comment faire
		
		Platform.exit();
		
//		final String MESSAGE_EN_COURS_DEV =
//		"""
//		Cette fonctionnalité est toujours en cours de développement.
//		
//		Elle ne sera probablement jamais développée sauf si Loïc reçoit 
//		une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
//		""";
//		
//		/* Création d'une boîte d'alerte de type attention. */
//		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
//									  MESSAGE_EN_COURS_DEV);
//		
//		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
//		stage.getIcons().add(new Image("application/vues/images/InConstruction.png"));
//		
//		boiteAlerte.setTitle("Othello - Fermer");
//		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
//		boiteAlerte.showAndWait();
	}
	
	@FXML
	private void gererClicParametres() {		
		// échanger la vue courante avec celle des paramètres
		Othello.activerParametres(); 
	}
	
	@FXML
	private void gererClicPartieOrdinateur() {
		// échanger la vue courante avec celle du niveau de l'ordinateur
		Othello.activerNiveauOrdinateur(); 
	}
	
	@FXML
	private void gererClicPartieJoueur() {
		System.out.println("SU");
		Othello.activerChoixPseudosContreJoueur();
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

		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/EnConstruction.png"));

		boiteAlerte.setTitle("Othello - Fermer");
		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
		
		if (true) { // TODO vérifier qu'il y a bien une partie
			boiteAlerte.showAndWait();
		} else {
			infosPartie.setText("Aucune partie sauvegardée");
		}
		// va chercher la partie sauvegardée dans le fichier ModeleSauvegardes
	}

}