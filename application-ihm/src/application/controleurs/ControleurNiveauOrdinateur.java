/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.Othello;

import javafx.fxml.FXML;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurNiveauOrdinateur {
	
	boolean facileActif = false;
	boolean difficileActif = false;

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres TODO c'est quoi ce commentaire xtf ?
		Othello.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicFacile() {
		if (!facileActif || difficileActif) {
			facileActif = true;
			difficileActif = false;
		}
		// TODO echanger l'image pour la mettre en plus gros avec un if si c'est juste au dessus c'est activé
	}
	
	@FXML
	private void gererClicDifficile() {
		if (!difficileActif || facileActif) {
			difficileActif = true;
			facileActif = false;
		}
		// TODO echanger l'image pour la mettre en plus gros avec un if si c'est juste au dessus c'est activé
	}
	
	@FXML
	private void gererClicContinuer() {
		
	}
}