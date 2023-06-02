/*
 * ControleurJeu.java                      				            01 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;


/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurJeu extends ControleurPrincipal {
	
	@FXML
	private GridPane plateau;
	
	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		// TODO mettre des garde fou pour eviter de perdre la partie sans sauvegarder comme un con
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicSauvegarder() {
		final String MESSAGE_EN_COURS_DEV =
		"""
		Cette fonctionnalité est toujours en cours de développement.
		
		Elle ne sera probablement jamais développée sauf si Loïc reçoit 
		une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
		""";
		
		/* Création d'une boîte d'alerte de type attention. */
		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
									  MESSAGE_EN_COURS_DEV);
		
		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/EnConstruction.png"));
		
		boiteAlerte.setTitle("Othello - En cours de développement");
		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
		boiteAlerte.showAndWait();
	}
	
	@FXML
	private void gererClicAide() {
		final String MESSAGE_EN_COURS_DEV =
		"""
		Cette fonctionnalité est toujours en cours de développement.
		
		Elle ne sera probablement jamais développée sauf si Loïc reçoit 
		une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
		""";
		
		/* Création d'une boîte d'alerte de type attention. */
		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
									  MESSAGE_EN_COURS_DEV);
		
		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/EnConstruction.png"));
		
		boiteAlerte.setTitle("Othello - En cours de développement");
		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
		boiteAlerte.showAndWait();
	}
	
	@FXML
	private void gererClicCase(MouseEvent e) {
		
		System.out.println("CLIC");
		Node caseCliquee = (Node) e.getTarget();
		
		int indexCaseCliquee;
		
		System.out.println(GridPane.getColumnIndex(caseCliquee) + " = X");
		
//		int Y = GridPane.getRowIndex(caseCliquee);
//		int X = GridPane.getColumnIndex(caseCliquee);
		
//		String idCase = e.getSource().toString().substring(17, 19);
//		
//		int X = Integer.parseInt(idCase.substring(0, 1));
//		int Y = Integer.parseInt(idCase.substring(1));
		
//		System.out.println("X = " + X);
//		System.out.println("Y = " + Y + "\n");
		
//		for (Node nodeCourante : plateau.getChildren()) {
//			int indexLigneY = GridPane.getRowIndex(nodeCourante);
//			int indexColonneX = GridPane.getColumnIndex(nodeCourante);
//			
//			if (indexLigneY == Y && indexColonneX == X) {
//				//plateau.getChildren().get(indexCase).remove();
//				caseCliquee = nodeCourante;
//				//indexCaseCliquee = plateau.get
//				System.out.println("Case trouvée : X = " + X + " Y = " + Y);
//			}
//		}
//		
//		if (caseCliquee != null) {
//			plateau.getChildren().remove(caseCliquee);
//		}
		
	}
	
	/*private void creerPlateau() {
		
	}*/
	
}