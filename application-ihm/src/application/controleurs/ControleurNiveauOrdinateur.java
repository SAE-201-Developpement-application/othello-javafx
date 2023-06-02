/*
 * ControleurNiveauOrdinateur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

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
		// échanger la vue courante avec celle des paramètres TODO c'est quoi ce commentaire wtf je comprends pas ?
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicFacile() {
		if (!facileActif || difficileActif) {
			echangerVisibilite(FacileOff, FacileOn);
			echangerVisibilite(DifficileOn, DifficileOff);
			facileActif = true;
			difficileActif = false;
		} else {
			echangerVisibilite(DifficileOn, DifficileOff);
		}
	}
	
	@FXML
	private void gererClicDifficile() {
		if (!difficileActif || facileActif) {
			echangerVisibilite(DifficileOff, DifficileOn);
			echangerVisibilite(FacileOn, FacileOff);
			difficileActif = true;
			facileActif = false;
		} else {
			echangerVisibilite(FacileOn, FacileOff);
		}
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
	private void gererClicContinuer() {
		// TODO bien faire cette page lol
		GestionVues.activerChoixPseudoContreIA();
		
	}
}