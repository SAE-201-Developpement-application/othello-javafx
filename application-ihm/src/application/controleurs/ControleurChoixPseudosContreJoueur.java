/*
 * ControleurChoixPseudosContreJoueur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.vues.GestionVues;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurChoixPseudosContreJoueur extends ControleurPrincipal {
	
	/* Chaine de caractere pour l'erreur de pseudo */
	private final String REGLES_PSEUDO 
	= "Veuillez entrer un nom contenant 2 à 16 caractères.";
	
	/* Booleen pour savoir si le nom du joueur 1 est bien saisi */
	private boolean nomOKJoueur1 = false;
	
	/* Booleen pour savoir si le nom du joueur 2 est bien saisi */
	private boolean nomOKJoueur2 = false;
	
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
		// échanger la vue courante avec celle du menu principale
		GestionVues.activerMenuPrincipal(); 
		
		/* Remise à zéro des valeurs pour éviter les bugs quand on change de mode de jeu */
		pseudoJoueur.setText("");
		pseudoJoueur2.setText("");
		modelePrincipal.setNomJoueur1("");
		modelePrincipal.setNomJoueur2("");
		
		cocheJoueur1.setVisible(false);
		croixJoueur1.setVisible(false);
		
		cocheJoueur2.setVisible(false);
		croixJoueur2.setVisible(false);
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {
		
		String nomJoueur1 = pseudoJoueur.getText();
		
		if (nomJoueur1.isEmpty()) {
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(false);
			
			nomOKJoueur1 = false;
		} else if (nomJoueur1.length() > 1 && nomJoueur1.length() <= 16 
										   && contientDeuxCaracteresNonVides(nomJoueur1)) {
			
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(true);
			
			modelePrincipal.setNomJoueur1(nomJoueur1);
			nomOKJoueur1 = true;
		} else {
			cocheJoueur1.setVisible(false);
			croixJoueur1.setVisible(true);
			
			nomOKJoueur1 = false;
		}
	}
	
	@FXML
	private void gererMiseAJourNomJoueur2() {
	
		String nomJoueur2 = pseudoJoueur2.getText();
		
		if (nomJoueur2.isEmpty()) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(false);
			nomOKJoueur2 = false;
		} else if (nomJoueur2.length() > 1 && nomJoueur2.length() <= 16 
										   && contientDeuxCaracteresNonVides(nomJoueur2)) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(true);
			modelePrincipal.setNomJoueur2(nomJoueur2);
			nomOKJoueur2 = true;
		} else {
			cocheJoueur2.setVisible(false);
			croixJoueur2.setVisible(true);
			nomOKJoueur2 = false;	
		}
	}
	
	public boolean contientDeuxCaracteresNonVides(String str) {
	    Pattern patternARespecter = Pattern.compile(".*\\S.*\\S.*");
	    Matcher matcher = patternARespecter.matcher(str);
	    return matcher.matches();
	}
	
	@FXML
	private void gererClicJouer() {
		if (nomOKJoueur1 && nomOKJoueur2) {			
			modeleJeu.setPartieOrdinateur(false);
			pseudoJoueur.setText(null);
			pseudoJoueur2.setText(null);
			cocheJoueur1.setVisible(false);
			cocheJoueur2.setVisible(false);
			nomOKJoueur1 = false;
			nomOKJoueur2 = false;
			
			modeleJeu.setPartieCommence(false);

			GestionVues.activerJeu();
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
