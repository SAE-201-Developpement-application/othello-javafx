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
import javafx.scene.control.ButtonType;
import java.util.Optional;

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
		
	final String QUITTER_SANS_SAUVEGARDER =
    """
    Une partie de jeu est en cours.
    
    Souhaitez-vous tout de même quitter sans la sauvegarder ?
    """;
	
	@FXML
	private Text pseudoJoueur1;
	
	@FXML
	private Text pseudoJoueur2;
	
	@FXML
	private Text scoreJoueur1;
	
	@FXML
	private Text scoreJoueur2;
	
	@FXML
	private ImageView imageScoreJoueur1;
	
	@FXML
	private ImageView imageScoreJoueur2;
	
	@FXML
	private ImageView tourJoueur1;
	
	@FXML
	private ImageView tourJoueur2;
	
	@FXML
	private ImageView jouerMaintenant;
	
	@FXML
	private GridPane plateau;
		
	@FXML
	private void lancerJeu() {
		System.out.println("PK tu marche pas bowdel ?");
		System.out.println(modelePrincipal.getNomJoueur1());
		System.out.println(modelePrincipal.getNomJoueur2());
		
		initialiserPlateauJeu();
	}
	
	@FXML
	private void gererClicRetourMenuPrincipal() {
		// demander une confirmation si partie commencée
		if (modeleJeu.isPartieCommence()) {
    		// TODO mettre des garde fou pour eviter de perdre la partie sans sauvegarder comme un con
    		
            /* Création d'une boîte d'alerte de type attention. */
            Alert boiteModificationNonEnregistre
            = new Alert(Alert.AlertType.WARNING,
                        QUITTER_SANS_SAUVEGARDER);
            
            ButtonType boutonQuitter = new ButtonType("Quitter sans sauvegarder");
            ButtonType boutonRetourJeu = new ButtonType("Retour au jeu");
            
            boiteModificationNonEnregistre.getButtonTypes()
                                          .setAll(boutonQuitter,
                                                  boutonRetourJeu);
            
            Stage stage = (Stage) boiteModificationNonEnregistre.getDialogPane()
                                  .getScene().getWindow();
            stage.getIcons().add(new Image("application/vues/images/Attention.png"));
            
            boiteModificationNonEnregistre
            .setTitle("Othello - Retour menu principal");
            
            boiteModificationNonEnregistre
            .setHeaderText("Partie non sauvegardée");
            
            Optional<ButtonType> resultat = boiteModificationNonEnregistre.showAndWait();
            
            if (resultat.get() == boutonQuitter) {
                
                modelePrincipal.setNomJoueur1(null);
                modelePrincipal.setNomJoueur2(null);
                
                // échanger la vue courante avec celle du menu principal
                modificationVueAvantRetourMenuPrincipal();
            }
		} else {
			// échanger la vue courante avec celle du menu principal
            modificationVueAvantRetourMenuPrincipal();
		}
	}
	
	/**
	 * Change la vue et réinitialise la vue du jeu par défaut.
	 */
	private void modificationVueAvantRetourMenuPrincipal() {
		GestionVues.activerMenuPrincipal();
		
		// reinitialiserPlateauJeu();
                
        jouerMaintenant.setVisible(true);
        
        pseudoJoueur1.setVisible(false);
        pseudoJoueur2.setVisible(false);
        
        scoreJoueur1.setVisible(false);
        scoreJoueur2.setVisible(false);
        
        imageScoreJoueur1.setVisible(false);
        imageScoreJoueur2.setVisible(false);
        
        tourJoueur1.setVisible(false);
	}
	
	/**
     * Réinitialise le plateau de jeu par défaut.
     */
    private void reinitialiserPlateauJeu() {
        initialiserPlateauJeu();
        
        // TODO cacher les pions supplémentaires ajoutés
    }
    
    /**
     * Initialise le plateau de jeu par défaut.
     */
    private void initialiserPlateauJeu() {
        
        apparaitrePion("Blanc", 3, 3);
        apparaitrePion("Blanc", 4, 4);
        apparaitrePion("Noir", 3, 4);
        apparaitrePion("Noir", 4, 3);
        
        pseudoJoueur1.setText(modelePrincipal.getNomJoueur1());
        pseudoJoueur2.setText(modelePrincipal.getNomJoueur2());
        
        jouerMaintenant.setVisible(false);
        
        pseudoJoueur1.setVisible(true);
        pseudoJoueur2.setVisible(true);
        
        scoreJoueur1.setVisible(true);
        scoreJoueur2.setVisible(true);
        
        imageScoreJoueur1.setVisible(true);
        imageScoreJoueur2.setVisible(true);
        
        tourJoueur1.setVisible(true);
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
	
	private void apparaitrePion(String couleur, int coordonneeX, int coordonneeY) {
		ImageView pion
		= new ImageView("application/vues/images/Jeu/" + couleur + ".png");
		
		pion.setFitWidth(65);
		pion.setFitHeight(65);
		pion.setId("pion" + coordonneeX + coordonneeY);
		
		// Ajout d'un écouteur de clic au pion affiché
		pion.addEventFilter(MouseEvent.MOUSE_CLICKED,
							new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        gererClicCase(mouseEvent);
		    }
		});
		
		GridPane.setColumnIndex(pion, coordonneeX);
		GridPane.setRowIndex(pion, coordonneeY);
		
		ajouterPion(pion);
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
			
			int[][] pionsARetourner = modeleJeu.clicCase(coordonneeX, coordonneeY);
			
			/* Si pionsARetourner ==
			 * - null   =   joueur peut pas placer de pion là où il a cliqué */
			if (pionsARetourner == null) {
				System.out.println("ControleurJeu >> Impossible placer pion où clic");
				
				// ajouterErreurPlacementJoueur1() TODO appeler fonction modèle
				if (modeleJeu.getNombreErreursPlacementJoueur1()
					== modeleJeu.NOMBRE_MAX_ERREURS_PLACEMENT) {
					// TODO pop-up rappelant les règles car 5 clics impossibles
				}
			} else {
				for (int[] pionARetourner : pionsARetourner) {
					System.out.println("\nPion à retourner :"
									   + "\nX = "
									   + pionARetourner[0]
									   + "\nY = "
									   + pionARetourner[1]);
					// TODO @Tom retourne moi ce pion en fonction de la couleur
					// (bonne change jsp encore comment faire là - méthode getCouleurParCoordonnees ds modele ?)
				}
				
				apparaitrePion("Noir", coordonneeX, coordonneeY);
			}
		} else {
			retirerPion(caseCliquee);
		}
	}	
}