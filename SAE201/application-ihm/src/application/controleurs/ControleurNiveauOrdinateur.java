/*
 * ControleurNiveauOrdinateur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurNiveauOrdinateur extends ControleurPrincipal {
	
	final String CHOISIR_DIFFICULTEE = 
	"Pour continuer, veuillez choisir une difficulté !";
	
	@FXML
	private ImageView difficileOff;
	
	@FXML
	private ImageView difficileOn;
	
	@FXML
	private ImageView facileOff;
	
	@FXML
	private ImageView facileOn;

	private void reinitialisationVueParDefaut() {
		facileOn.setVisible(false);
		facileOff.setVisible(true);
		
		difficileOn.setVisible(false);
		difficileOff.setVisible(true);
	}
	
	@FXML
	private void gererClicRetourMenuPrincipal() {		
		reinitialisationVueParDefaut();
		
		// échanger la vue courante avec celle du menu principal
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicFacile() {
		
		/* Modification de l'image facile affichée :
		   affichage de l'image + grosse pour confirmer l'action */
		facileOff.setVisible(false);
		facileOn.setVisible(true);
		
		/* Modification de l'image difficile affichée :
		   affichage de l'image normale pour échanger le choix */
		difficileOn.setVisible(false);
		difficileOff.setVisible(true);
		
		System.out.println("\n>> ControleurNiveauOrdinateur :"
						   + " niveau facile cliqué\n");
	}
	
	@FXML
	private void gererClicDifficile() {
		
		/* Modification de l'image difficile affichée :
		   affichage de l'image + grosse pour confirmer l'action */
		difficileOff.setVisible(false);
		difficileOn.setVisible(true);
		
		/* Modification de l'image facile affichée :
		   affichage de l'image normale pour échanger le choix */
		facileOn.setVisible(false);
		facileOff.setVisible(true);
		
		System.out.println("\n>> ControleurNiveauOrdinateur :"
						   + " niveau difficile cliqué\n");
	}
	
	@FXML
	private void gererClicContinuer() {
		
		boolean facileActif = facileOn.isVisible();
		boolean difficileActif = difficileOn.isVisible();
		
		if (facileActif || difficileActif) {
			modeleJeu.setOrdinateurFacile(facileActif ? true : false);
			
			modelePrincipal.setPseudoJoueur2("Bot "
										     + (modeleJeu.isOrdinateurFacile()
                    					     ? "facile" : "difficile"));
			reinitialisationVueParDefaut();
			GestionVues.activerChoixPseudoContreIA();
		} else {
			Alert difficulteeNonChoisie = new Alert(Alert.AlertType.ERROR, CHOISIR_DIFFICULTEE);
			
			difficulteeNonChoisie.getDialogPane().getStylesheets().add(getClass()
          			 			 .getResource("../vues/application.css").toExternalForm());
			
			Stage stage = (Stage) difficulteeNonChoisie.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
			
			difficulteeNonChoisie.setTitle("Othello - Difficulté non choisie");
			difficulteeNonChoisie.setHeaderText("Difficulté non choisie");
			difficulteeNonChoisie.showAndWait();		
		}
	}
}