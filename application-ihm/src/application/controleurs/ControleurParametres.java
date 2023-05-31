/*
 * Controlleur.java                      				            25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;


/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurParametres extends ControleurPrincipal {

	@FXML
	private ImageView iconePositionsPossibles;
	
	@FXML
	private ImageView iconePositionsPossiblesActivee;
	
	@FXML
	private ImageView iconePionsEnlevables;
	
	@FXML
	private ImageView iconePionsEnlevablesActivee;
	
	@FXML
	private ImageView iconeMusique;
	
	@FXML
	private ImageView iconeMusiqueDesactivee;
	
	@FXML
	private ImageView iconeSon;
	
	@FXML
	private ImageView iconeSonDesactivee;
	
	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		GestionVues.activerMenuPrincipal(); 
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
	private void gererClicPositionsPossibles() {
		// Ajouter ou retirer un contour vert pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconePositionsPossibles, iconePositionsPossiblesActivee);
	}
	
	@FXML
	private void gererClicPionsEnlevables() {
		// Ajouter ou retirer un contour vert pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconePionsEnlevables, iconePionsEnlevablesActivee);
	}
	
	@FXML
	private void gererClicMusique() {
		// Ajouter ou retirer une croix rouge pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconeMusique, iconeMusiqueDesactivee);
	}
	
	@FXML
	private void gererClicSon() {
		// Ajouter ou retirer une croix rouge pour confirmer l'action
		// et l'état du paramètre
		echangerVisibilite(iconeSon, iconeSonDesactivee);
	}
	
	@FXML
	private void gererClicCredits() {
		
		final String MESSAGE_BOITE =
		"""
		Tom DOUAUD
		Loïc FAUGIERES
		Simon GUIRAUD
		
		Étudiants en BUT Informatique 1 (promotion 2022-2023) à l'IUT de Rodez.
		
		Application réalisée dans le cadre de la SAÉ 2.01
		Développement d'une application.
		""";
		
		/* Création d'une boîte d'alerte de type information. */
		Alert boiteAlerte = new Alert(Alert.AlertType.INFORMATION,
						 			  MESSAGE_BOITE);
		
		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/Parametres/IconeCredits.png"));
		
		boiteAlerte.setTitle("Othello - Crédits");
		boiteAlerte.setHeaderText("Crédits :");
		boiteAlerte.showAndWait();
		
	}
	
	@FXML
	private void gererClicValiderParametres() {
		
		boolean musique = iconeMusique.isVisible();
		boolean son = iconeSon.isVisible();
		boolean voirPositionsPossibles = iconePositionsPossiblesActivee
									     .isVisible();
		boolean voirPionsEnlevables = iconePionsEnlevablesActivee
									  .isVisible();
		
		final String VALIDATION_PARAMETRES
		= "Voulez-vous appliquer les nouveaux paramètres ?";
		
		final String ANNULATION_VALIDATION
		= "Les modifications ont été annulées.";
		
		final String AUCUNE_MODIFICATION
		= "Il n'y a eu aucune modification des paramètres.";
				
		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
									  VALIDATION_PARAMETRES);
		
		Alert boiteAnnulation = new Alert(Alert.AlertType.INFORMATION,
										  ANNULATION_VALIDATION);
		
		Alert boiteAucuneModification = new Alert(Alert.AlertType.ERROR,
											      AUCUNE_MODIFICATION);
		
		ButtonType boutonConfirmer = new ButtonType("Appliquer");
        ButtonType boutonAnnuler = new ButtonType("Annuler");
        
        boiteAlerte.getButtonTypes().setAll(boutonConfirmer, boutonAnnuler);

		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons()
		.add(new Image("application/vues/images/Parametres/ValidationParametres.png"));

		boiteAlerte.setTitle("Othello - Paramètres");
		boiteAlerte.setHeaderText("Validation des paramètres");
		
		Stage stage2 = (Stage) boiteAnnulation.getDialogPane().getScene().getWindow();
		stage2.getIcons()
		.add(new Image("application/vues/images/Parametres/Attention.png"));

		boiteAnnulation.setTitle("Othello - Annulation paramètres");
		boiteAnnulation.setHeaderText("Annulation paramètres");
		
		Stage stage3 = (Stage) boiteAucuneModification.getDialogPane().getScene().getWindow();
		stage3.getIcons().add(new Image("application/vues/images/Parametres/Annulation.png"));

		boiteAucuneModification.setTitle("Othello - Aucune modification");
		boiteAucuneModification.setHeaderText("Aucune modification");
			
		if (modelePrincipal.parametresModifies(musique, son,
												 voirPositionsPossibles,
												 voirPionsEnlevables)) {
	        Optional<ButtonType> resultat = boiteAlerte.showAndWait();
	        
	        if (resultat.get() == boutonConfirmer) {
	        	modelePrincipal.setParametres(musique, son,
	        								  voirPositionsPossibles,
	        								  voirPionsEnlevables);
	        } else if (resultat.get() == boutonAnnuler) {
	        	boiteAnnulation.showAndWait();
	        	// TODO : remettre sur l'IHM les boutons dans leur état original en fonction du modèle...
	        }
		} else {
			boiteAucuneModification.showAndWait();
		}
	}

}