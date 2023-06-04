/*
 * ControleurJeu.java                      				            01 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.modeles.ModeleJeu;
import application.vues.GestionVues;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.ButtonType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Optional;

/**
 * Contrôle via FXML les interactions avec la vue : les pages FXML.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ControleurJeu extends ControleurPrincipal {
	
	final static String MESSAGE_EN_COURS_DEV =
	"""
	Cette fonctionnalité est toujours en cours de développement.
	
	Elle ne sera probablement jamais développée sauf si Loïc reçoit 
	une somme assez conséquente (paypal.me/loicfaugieres1) pour le motiver.
	""";
		
	final static String QUITTER_SANS_SAUVEGARDER =
    """
    Une partie de jeu est en cours.
    
    Souhaitez-vous tout de même quitter sans la sauvegarder ?
    """;
	
	private Node[] toutesLesNodes = {};
    
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
		initialiserPlateauJeu();
	}
	
    /**
     * Initialise le plateau de jeu par défaut (affichage des pions
     * et des informations de la partie).
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
	private void gererClicRetourMenuPrincipal() {
		// Demander une confirmation si partie commencée
		if (modeleJeu.isPartieCommence()) {
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
            	
            	// échanger la vue courante avec celle du menu principal
                GestionVues.activerMenuPrincipal();
                
            	// réinitialiser la vue par défaut et le modèle du jeu
                reinitialisationVueModeleJeu();
            }
		} else {
			// échanger la vue courante avec celle du menu principal
            GestionVues.activerMenuPrincipal();
            
        	// réinitialiser la vue par défaut et le modèle du jeu
            reinitialisationVueModeleJeu();
		}
	}
	
	/**
	 * Change la vue et réinitialise la vue du jeu par défaut.
	 */
	private void reinitialisationVueModeleJeu() {
		reinitialiserPlateauJeu();
		
        modelePrincipal.setNomJoueur1(null);
        modelePrincipal.setNomJoueur2(null);
        
        // réinitialisation du modèle jeu (des paramètres de la partie)
        modeleJeu = new ModeleJeu();
        
        pseudoJoueur1.setVisible(false);
        pseudoJoueur2.setVisible(false);
        
        scoreJoueur1.setVisible(false);
        scoreJoueur2.setVisible(false);
        
        imageScoreJoueur1.setVisible(false);
        imageScoreJoueur2.setVisible(false);
        
        tourJoueur1.setVisible(false);
        
        jouerMaintenant.setVisible(true);
	}
	
	/**
     * Réinitialise le plateau de jeu par défaut.
     */
    private void reinitialiserPlateauJeu() {
        // TODO cacher les pions supplémentaires ajoutés
        for (Node nodeActuelle : this.toutesLesNodes) {
        	
        	// Récupération de l'identifiant de la node
    		String idCase = nodeActuelle.toString().substring(13, 19);
    		
    		if (idCase.startsWith("pion")) {
    			retirerPion(nodeActuelle);
    		}
        }
        initialiserPlateauJeu();
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
		
		String messageAide =
		"""
		Pour apprendre les règles d'Othello, rendez vous sur ce site internet :
		https://www.ffothello.org/othello/regles-du-jeu/
		
		Au bout de 5 clics sur des cases où vous ne pouvez pas cliquer,
		cette fenêtre s'affichera pour vous aider.
		""";
		
		/* Création d'une boîte d'alerte de type attention. */
		Alert boiteAide = new Alert(Alert.AlertType.INFORMATION,
								    messageAide);
									
		Stage stage = (Stage) boiteAide.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/Jeu/IconeAide.png"));
		
		boiteAide.setTitle("Othello - Aide");
		boiteAide.setHeaderText("Aide et règles d'Othello");
		boiteAide.showAndWait();
	}
	
	/**
	 * @param idCase Id de la case dont on veut retourner les coordonnées x/y.
	 * @return un tableau d'entiers contenant les coordonnées de la case.
	 */
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
		ajouterAToutesLesNodes(image);
		plateau.getChildren().add(image);
	}
	
	private void retirerPion(Node caseNode) {
		retirerAToutesLesNodes(caseNode);
		plateau.getChildren().remove(caseNode);
	}
	
	/**
	 * Ajouter à la liste de l'attribut toutesLesNodes un élément.
	 * 
	 * @param element Node à ajouter à la toutesLesNodes.
	 */
	private void ajouterAToutesLesNodes(Node element) {
		this.toutesLesNodes = Arrays.copyOf(toutesLesNodes,
											toutesLesNodes.length + 1);
		this.toutesLesNodes[this.toutesLesNodes.length - 1] = element;
	}
	
	/**
	 * Ajouter à la liste de Node en paramètre un élément.
	 * 
	 * @param element Node à ajouter à la liste.
	 * @param liste Liste à laquelle ajouter l'élément.
	 */
	private Node[] ajouterAListe(Node element, Node[] liste) {
		liste = Arrays.copyOf(liste,
							  liste.length + 1);
		liste[liste.length - 1] = element;
		
		return liste;
	}
	
	/**
	 * Retirer de la liste de l'attribut toutesLesNodes un élément.
	 * 
	 * @param element Node à retirer de la liste.
	 */
	private void retirerAToutesLesNodes(Node element) {
		Node[] listeTemporaire = {};
		
		for (Node nodeActuelle : this.toutesLesNodes) {
			if (nodeActuelle != element) {
				listeTemporaire = ajouterAListe(nodeActuelle, listeTemporaire);
			}
		}
		this.toutesLesNodes = listeTemporaire;
	}
	
	@FXML
	private void gererClicCase(MouseEvent event) {
		
		Node caseCliquee = (Node) event.getTarget();
		
		ajouterAToutesLesNodes(caseCliquee);
		
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
			
			/* Si pionsARetourner.length == 0
			 * => joueur peut pas placer de pion là où il a cliqué */
			if (pionsARetourner.length == 0) {
				gererErreurClicImpossible(modeleJeu.isTourJoueur1());
			} else {
				modeleJeu.setPartieCommence(true);
				
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
	
	/**
	 * Gestion des erreurs des joueurs lorsqu'ils cliquent sur une case où
	 * il ne peuvent pas cliquer (si ils ne respectent pas les règles).
	 * Affichage d'une fenêtre d'aide au bout de
	 * {@link application.modeles.ModeleJeu#NOMBRE_MAX_ERREURS_PLACEMENT}
	 * erreurs consécutives.
	 * 
	 * @param tourJoueur1 Si c'est le tour du joueur 1
	 */
	private void gererErreurClicImpossible(boolean tourJoueur1) {
		System.out.println("ControleurJeu >> Impossible placer pion où clic");
		
		boolean clicsImpossiblesMax
		= modeleJeu.getNombreErreursPlacementJoueur1()
		  >= ModeleJeu.NOMBRE_MAX_ERREURS_PLACEMENT
		  || modeleJeu.getNombreErreursPlacementJoueur2()
		     >= ModeleJeu.NOMBRE_MAX_ERREURS_PLACEMENT;
		
		// Au bout de 5 clics impossibles => Affichage aide
		if (clicsImpossiblesMax) {
			System.out.println("NONNN TOMMMMM 5 ERRREUURS");
			gererClicAide();
			modeleJeu.reinitialiserNombreErreurs(modeleJeu.isTourJoueur1());
		}
	}
	
	@FXML // TODO Loic d'apres GPT c'est comme ca qu'on utilise un gif, ping moi si tu bite rien (tiens bite est un mot du dictionnaire ¯\_(ツ)_/¯) 
	private void demarrerGIF() {
	    ImageView gifNoirVersBlanc = new ImageView(new Image("application/vues/images/Jeu/GIF1.gif"));
	    ImageView gifBlancVersNoir = new ImageView(new Image("application/vues/images/Jeu/GIF2.gif"));
	    
	    gifNoirVersBlanc.setFitWidth(65);
	    gifNoirVersBlanc.setFitHeight(65);
	    
	    gifBlancVersNoir.setFitWidth(65);
	    gifBlancVersNoir.setFitHeight(65);

	    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
	        System.out.println("test"); // Instructions à exécuter à la fin de l'animation
	    }));
	    
	    timeline.setCycleCount(1); // Exécute l'animation une seule fois
	    timeline.play();

	    // Ajoutez l'ImageView du GIF à votre interface graphique
	    // par exemple, en l'ajoutant à un conteneur approprié, comme un GridPane
	    // ou en remplaçant une ImageView existante
	    
		// GridPane.setColumnIndex(pion, coordonneeX);
		// GridPane.setRowIndex(pion, coordonneeY);
	    plateau.getChildren().add(gifNoirVersBlanc); // Assurez-vous d'avoir un GridPane nommé "plateau" dans votre FXML
	}
}