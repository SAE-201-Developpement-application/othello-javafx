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
	
	/* Chaîne de caractère pour l'erreur de pseudo */
	private final String REGLES_PSEUDO 
	= "Veuillez entrer un pseudonyme contenant 2 à 16 caractères."
	  + "\nLes 2 pseudos doivent être différents.";
	
	/* Booléen pour savoir si le nom du joueur 1 est bien saisi */
	private boolean nomOkJoueur1 = false;
	
	/* Booléen pour savoir si le nom du joueur 2 est bien saisi */
	private boolean nomOkJoueur2 = false;
	
	@FXML
	private TextField pseudoJoueur1;
	
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
	
	/** @return Le pseudo entré ou celui par défaut. */
	private String getPseudoJoueur1() {
		return !pseudoJoueur1.getText().isEmpty()
			   ? pseudoJoueur1.getText()
			   : "";
	}
	
	/** @return Le pseudo entré ou celui par défaut. */
	private String getPseudoJoueur2() {
		return !pseudoJoueur2.getText().isEmpty()
			   ? pseudoJoueur2.getText()
			   : "";
	}

	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle du menu principale
		GestionVues.activerMenuPrincipal(); 
		
		/* Remise à zéro des valeurs pour éviter les bugs quand on change de mode de jeu */
		pseudoJoueur1.setText("");
		pseudoJoueur2.setText("");
		
		modelePrincipal.setPseudoJoueur1(modelePrincipal.PSEUDO_PAR_DEFAUT);
		modelePrincipal.setPseudoJoueur2("");
		
		cocheJoueur1.setVisible(false);
		croixJoueur1.setVisible(false);
		
		cocheJoueur2.setVisible(false);
		croixJoueur2.setVisible(false);
	}
	
	@FXML
	private void gererMiseAJourNomJoueur1() {
		
		if (getPseudoJoueur1().isEmpty()) {
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(false);
			
			nomOkJoueur1 = false;
		} else if (getPseudoJoueur1().length() > 1
				   && getPseudoJoueur1().length() <= 16 
				   && contientDeuxCaracteresNonVides(getPseudoJoueur1())) {
			
			croixJoueur1.setVisible(false);
			cocheJoueur1.setVisible(true);
			
			nomOkJoueur1 = true;
		} else {
			cocheJoueur1.setVisible(false);
			croixJoueur1.setVisible(true);
			
			nomOkJoueur1 = false;
		}
	}
	
	@FXML
	private void gererMiseAJourNomJoueur2() {
	
		if (getPseudoJoueur2().isEmpty()) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(false);
			nomOkJoueur2 = false;
		} else if (getPseudoJoueur2().length() > 1
				   && getPseudoJoueur2().length() <= 16 
				   && contientDeuxCaracteresNonVides(getPseudoJoueur2())) {
			croixJoueur2.setVisible(false);
			cocheJoueur2.setVisible(true);
			
			nomOkJoueur2 = true;
		} else {
			cocheJoueur2.setVisible(false);
			croixJoueur2.setVisible(true);
			
			nomOkJoueur2 = false;	
		}
	}
	
	public boolean contientDeuxCaracteresNonVides(String str) {
	    Pattern patternARespecter = Pattern.compile(".*\\S.*\\S.*");
	    Matcher matcher = patternARespecter.matcher(str);
	    return matcher.matches();
	}
	
	@FXML
	private void gererClicJouer() {
		if (nomOkJoueur1 && nomOkJoueur2
			&& !getPseudoJoueur1().equals(getPseudoJoueur2())) {
			
			modeleJeu.setPartieOrdinateur(false);
			modeleJeu.setPartieCommencee(false);
			
			modelePrincipal.setPseudoJoueur1(getPseudoJoueur1());
			modelePrincipal.setPseudoJoueur2(getPseudoJoueur2());			
			
			pseudoJoueur1.setText(null);
			pseudoJoueur2.setText(null);
			
			cocheJoueur1.setVisible(false);
			cocheJoueur2.setVisible(false);
			
			nomOkJoueur1 = false;
			nomOkJoueur2 = false;

			GestionVues.activerJeu();
		} else {
			Alert boitePseudoIncompatible = new Alert(Alert.AlertType.ERROR);
			
			boitePseudoIncompatible.getDialogPane().getStylesheets()
			.add(getClass().getResource("../vues/application.css").toExternalForm());
			
			Stage stage = (Stage) boitePseudoIncompatible.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("application/vues/images/Annulation.png"));
			
			boitePseudoIncompatible.setTitle("Othello - Pseudonyme invalide");
			boitePseudoIncompatible.setHeaderText(REGLES_PSEUDO);
			boitePseudoIncompatible.showAndWait();		
		}								      
	}
}
