/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.Othello;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurMenuPrincipal {

	@FXML
	private Text menuPrincipalInfosPartie;
	
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
		menuPrincipalInfosPartie.setText("TODO");
	}
	
	@FXML
	private void generer() {
		
	}

}