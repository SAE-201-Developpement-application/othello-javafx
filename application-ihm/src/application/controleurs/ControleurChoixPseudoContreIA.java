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
import javafx.scene.control.TextField;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurChoixPseudoContreIA extends ControleurPrincipal {
	
	/* Chaîne de caractères pour l'erreur de pseudo */
	private static final String REGLES_PSEUDO 
	= "Veuillez entrer un nom contenant 2 à 16 caractères.";
	
	/* Booléen pour savoir si le nom du joueur 1 est bien saisi */
	private static boolean nomOkJoueur1 = false;
	
	private static final String NOM_PAR_DEFAUT = "Joueur humain";
		
	@FXML
	private TextField pseudoJoueur1;
	
	@FXML
	private ImageView cocheJoueur;
	
	@FXML
	private ImageView croixJoueur;
	
	private String getPseudoJoueur1() {
		return !pseudoJoueur1.getText().isEmpty()
			   ? pseudoJoueur1.getText()
			   : NOM_PAR_DEFAUT;
	}

	@FXML
	private void gererClicRetourNiveauOrdinateur() {		
		// échanger la vue courante avec celle du niveau Ordinateur
		GestionVues.activerNiveauOrdinateur(); 
		
		/* Remise à zéro des valeurs pour éviter les bugs quand on change de mode de jeu */
		pseudoJoueur1.setText("");
		
		modelePrincipal.setPseudoJoueur1(NOM_PAR_DEFAUT);
		modelePrincipal.setPseudoJoueur2("");
		
		cocheJoueur.setVisible(false);
		croixJoueur.setVisible(false);
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {
		verifierNom(getPseudoJoueur1());		
	}
	
	private boolean contientDeuxCaracteresNonVides(String str) {
	    Pattern patternARespecter = Pattern.compile(".*\\S.*\\S.*");
	    Matcher matcher = patternARespecter.matcher(str);
	    return matcher.matches();
	}
	
	private boolean verifierNom(String nom) {
		
		boolean resultat = false;
		
		if (nom.isEmpty()) {
			croixJoueur.setVisible(false);
			cocheJoueur.setVisible(false);
			
			resultat = true;
		} else if (nom.length() > 1
				 && nom.length() <= 16 
				 && contientDeuxCaracteresNonVides(nom)) {
			
			croixJoueur.setVisible(false);
			cocheJoueur.setVisible(true);
			
			resultat = true;
		} else {
			cocheJoueur.setVisible(false);
			croixJoueur.setVisible(true);
			resultat = false;
		}
		return resultat;
	}
	
	@FXML
	private void gererClicJouer() {
		nomOkJoueur1 = verifierNom(getPseudoJoueur1());
		
		if (nomOkJoueur1) {
			modeleJeu.setPartieOrdinateur(true);
			modeleJeu.setPartieCommencee(false);
			modelePrincipal.setPseudoJoueur1(getPseudoJoueur1());
			
			pseudoJoueur1.setText("");
			cocheJoueur.setVisible(false);
			
			nomOkJoueur1 = false;
			
			GestionVues.activerJeu();
		} else {
			Alert boitePseudoIncompatible = new Alert(Alert.AlertType.ERROR);
			boitePseudoIncompatible.getDialogPane().getStylesheets().add(getClass()
								   .getResource("../vues/application.css").toExternalForm());
			Stage stage = (Stage) boitePseudoIncompatible.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
			
			boitePseudoIncompatible.setTitle("Othello - Pseudonyme invalide");
			boitePseudoIncompatible.setHeaderText(REGLES_PSEUDO);
			boitePseudoIncompatible.showAndWait();

		}
	}
}