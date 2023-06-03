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
	
	boolean facileActif = false;
	boolean difficileActif = false;
	
	final String CHOISIR_DIFFICULTEE = 
	"Pour continuer, veuillez choisir une difficultée";
	
	@FXML
	private ImageView DifficileOff;
	
	@FXML
	private ImageView DifficileOn;
	
	@FXML
	private ImageView FacileOff;
	
	@FXML
	private ImageView FacileOn;

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle du menu principal
		facileActif = false;
		difficileActif = false;
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicFacile() {
		
		
		if (!facileActif || difficileActif) {
			facileActif = true;
			difficileActif = false;
		} 
		
		if (facileActif) {
			FacileOn.setVisible(true);
			FacileOff.setVisible(false);
			DifficileOff.setVisible(true);
			DifficileOn.setVisible(false);
		} else {
			FacileOn.setVisible(false);
			FacileOff.setVisible(true);
		}
		System.out.println("difficle : " + difficileActif + "facile " + facileActif);

	}
	
	@FXML
	private void gererClicDifficile() { // TODO refractor le code, tres probable que la moitiée des if sert a rien
		
		
		if (!difficileActif || facileActif) {
			difficileActif = true;
			facileActif = false;
		} else {
		}
		
		if (difficileActif) {
			DifficileOn.setVisible(true);
			DifficileOff.setVisible(false);
			FacileOn.setVisible(false);
			FacileOff.setVisible(true);
		} else {
			DifficileOn.setVisible(false);
			DifficileOff.setVisible(true);
		}
	}
	
	@FXML
	private void gererClicContinuer() {
		
		if (facileActif || difficileActif) {
			
			if (facileActif) {
				modeleJeu.setOrdinateurFacile(true);
			} else {
				modeleJeu.setOrdinateurFacile(false);
			}
			
			modelePrincipal.setNomJoueur2(modeleJeu.isOrdinateurFacile()
                    ? "Bot Facile" : "Bot Difficile");
			GestionVues.activerChoixPseudoContreIA();
		} else {
			Alert difficulteeNonChoisie = new Alert(Alert.AlertType.ERROR, CHOISIR_DIFFICULTEE);
			
			Stage stage = (Stage) difficulteeNonChoisie.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
			
			difficulteeNonChoisie.setTitle("Othello - Difficultée non choisie");
			difficulteeNonChoisie.setHeaderText("Difficultée non choisie");
			difficulteeNonChoisie.showAndWait();		
		}
	}
}