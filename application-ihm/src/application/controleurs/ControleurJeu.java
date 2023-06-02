/*
 * ControleurJeu.java                      				            01 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.vues.GestionVues;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import javafx.scene.text.Text;
//import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurJeu extends ControleurPrincipal {
	
	final String MESSAGE_EN_COURS_DEV =
		"""
		Cette fonctionnalité est toujours en cours de développement.
		
		Elle ne sera probablement jamais développée sauf si Loïc reçoit 
		une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
		""";
	
	@FXML
	private Text pseudoJoueur1;
	
	@FXML
	private Text pseudoJoueur2;
	
	@FXML
	private GridPane plateau;
		
	@FXML
	private void initialize() { // TODO faire que ca marche quand on lance cette fenetre
		getPseudoJoueur1().setText(modeleJeu.getNomJoueur1());
		getPseudoJoueur2().setText(modeleJeu.getNomJoueur2());	
	}
	
	@FXML
	private void gererClicRetourMenuPrincipal() {		
		// échanger la vue courante avec celle des paramètres
		// TODO mettre des garde fou pour eviter de perdre la partie sans sauvegarder comme un con
		GestionVues.activerMenuPrincipal(); 
	}
	
	@FXML
	private void gererClicSauvegarder() {
		
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
		
		/* Création d'une boîte d'alerte de type attention. */
		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
									  MESSAGE_EN_COURS_DEV);
		
		Stage stage = (Stage) boiteAlerte.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/EnConstruction.png"));
		
		boiteAlerte.setTitle("Othello - En cours de développement");
		boiteAlerte.setHeaderText("Fonctionnalité en cours de développement...");
		boiteAlerte.showAndWait();
	}
	
	private int[] getCoordonneesCase(String idCase) {
		int[] resultat = new int[2];
		
		String coordonneesCase = idCase.substring(4, 6);
		
		int indexColonneX = Integer.parseInt(coordonneesCase.substring(0, 1));
		int indexLigneY = Integer.parseInt(coordonneesCase.substring(1));
		
		resultat[0] = indexColonneX;
		resultat[1] = indexLigneY;
		return resultat;
	}
	
	private void ajouterPion(ImageView image) {
		plateau.getChildren().add(image);
	}
	
	private void retirerPion(Node caseNode) {
		plateau.getChildren().remove(caseNode);
	}
	
	@FXML
	private void gererClicCase(MouseEvent event) {
		
		Node caseCliquee = (Node) event.getTarget();
		
		// Récupération de l'identifiant de l'image cliquée
		String idCase = caseCliquee.toString().substring(13, 19);
		
		int[] coordonnees = getCoordonneesCase(idCase);
		int coordonneeX = coordonnees[0];
		int coordonneeY = coordonnees[1];

		System.out.println("ControleurJeu >> Case cliquée :"
						   + "\nID = "+ idCase
						   + "\nX = " + coordonneeX
						   + "\nY = " + coordonneeY + "\n");
		
		// Si image cliquée == image/fond invisible
		if (idCase.startsWith("case")) {
			ImageView pionBlanc
			= new ImageView("application/vues/images/Jeu/Blanc.png");
			
			pionBlanc.setFitWidth(65);
			pionBlanc.setFitHeight(65);
			pionBlanc.setId("pion" + coordonneeX + coordonneeY);
			
			// Ajout d'un écouteur de clic au pion affiché
			pionBlanc.addEventFilter(MouseEvent.MOUSE_CLICKED,
									 new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        gererClicCase(mouseEvent);
			    }
			});
			
			GridPane.setColumnIndex(pionBlanc, coordonneeX);
			GridPane.setRowIndex(pionBlanc, coordonneeY);
			
			ajouterPion(pionBlanc);			
		} else {
			retirerPion(caseCliquee);
		}
	}

	public Text getPseudoJoueur1() {
		return pseudoJoueur1;
	}

	public void setPseudoJoueur1(Text pseudoJoueur1) {
		this.pseudoJoueur1 = pseudoJoueur1;
	}

	public Text getPseudoJoueur2() {
		return pseudoJoueur2;
	}

	public void setPseudoJoueur2(Text pseudoJoueur2) {
		this.pseudoJoueur2 = pseudoJoueur2;
	}
	
}