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
import javafx.geometry.HPos;
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
	
	final static String LOG_FICHIER = "\n>> ControleurJeu : ";
	
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
		modeleJeu.setPartieCommencee(true);
		System.out.println(LOG_FICHIER + "Jeu lancé");
		afficherPositionsPossibles();
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
                
        pseudoJoueur1.setText(modelePrincipal.getPseudoJoueur1());
        pseudoJoueur2.setText(modelePrincipal.getPseudoJoueur2());
        
        scoreJoueur1.setText("" + modeleJeu.getScoreJoueur1());
        scoreJoueur2.setText("" + modeleJeu.getScoreJoueur2());
        
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
		// Demander une confirmation si partie en cours
		if (modeleJeu.isPartieEnCours() && !modeleJeu.partieFinie()) {
			
    		/* Création d'une boîte d'alerte de type attention. */
            Alert boiteModificationNonEnregistre
            = new Alert(Alert.AlertType.WARNING,
                        QUITTER_SANS_SAUVEGARDER);
            boiteModificationNonEnregistre.getDialogPane().getStylesheets().add(getClass()
            			                  .getResource("../vues/application.css").toExternalForm());
            
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
	private ModeleJeu reinitialisationVueModeleJeu() {
		reinitialiserPlateauJeu();
        
        // réinitialisation du modèle jeu (des paramètres de la partie)
        modeleJeu = new ModeleJeu();
        
        pseudoJoueur1.setVisible(false);
        pseudoJoueur2.setVisible(false);
        
        scoreJoueur1.setVisible(false);
        scoreJoueur2.setVisible(false);
        
        imageScoreJoueur1.setVisible(false);
        imageScoreJoueur2.setVisible(false);
        
		tourJoueur2.setVisible(false);
		tourJoueur1.setVisible(false);
        
        tourJoueur1.setVisible(false);
        
        jouerMaintenant.setVisible(true);
        
        return modeleJeu;
	}
	
	/**
     * Réinitialise le plateau de jeu par défaut.
     */
    private void reinitialiserPlateauJeu() {
        for (Node nodeActuelle : this.toutesLesNodes) {
        	
        	// Récupération de l'identifiant de la node
    		String idCase = nodeActuelle.toString().substring(13, 19);
    		
    		if (idCase.startsWith("pion") || idCase.startsWith("posi")) {
    			retirerImage(nodeActuelle);
    		}
        }
        initialiserPlateauJeu();
    }
	
	@FXML
	private void gererClicSauvegarder() {
		
		/* Création d'une boîte d'alerte de type attention. */
		Alert boiteAlerte = new Alert(Alert.AlertType.WARNING,
									  MESSAGE_EN_COURS_DEV);
		boiteAlerte.getDialogPane().getStylesheets().add(getClass()
				   .getResource("../vues/application.css").toExternalForm());
		
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
		
		boiteAide.getDialogPane().getStylesheets().add(getClass()
				 .getResource("../vues/application.css").toExternalForm());
									
		Stage stage = (Stage) boiteAide.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("application/vues/images/Jeu/IconeAide.png"));
		
		boiteAide.setTitle("Othello - Aide");
		boiteAide.setHeaderText("Aide et règles d'Othello");
		boiteAide.showAndWait();
	}
	
	@FXML
	private void gererClicPasserTour() {
		if (modeleJeu.partieFinie()) {
			gererPartieFinie();
		} else if (modeleJeu.isPartieCommencee()) {
			System.out.println(LOG_FICHIER
					           + (modeleJeu.isTourJoueur1()
			                      ? "Joueur 1" : "Joueur 2")
			                   + " passe son tour");
			modeleJeu.passerTour();
			permuterFlecheTour();
			retirerPositionsPossibles();
			verifierFinPartie();
			
			if (modeleJeu.isPartieOrdinateur()
				&& !modeleJeu.isTourJoueur1()) {
				if (modeleJeu.rechercheCasesClicPossible().length > 0) {
					jouerOrdinateur();							
				} else {
					gererClicPasserTour();
				}
			}
		} else {
			System.out.println(LOG_FICHIER
							   + "La partie n'est pas commencée ou est finie.\n");
		}
	}
	
	/**
	 * Vérifie si la partie est finie et déclenche la pop-up correspondante.
	 * Si la partie n'est pas finie, appelle la méthode d'affichage
	 * des positions possibles.
	 */
	private void verifierFinPartie() {
		if (modeleJeu.partieFinie()) {
			gererPartieFinie();
			
		} else if (modeleJeu.isTourJoueur1()) {
			afficherPositionsPossibles();
			
		} else if (!modeleJeu.isTourJoueur1()) {
			if (modeleJeu.rechercheCasesClicPossible().length == 0) {
			    gererClicPasserTour();
			}
		}
	}
	
	/**
	 * Affichage d'une pop-up indiquant la fin de la partie.
	 * Réinitialise ensuite le modèle et la vue si l'utilisateur choisi
	 * de retourner au menu principal ou de redémarrer une partie.
	 * Il peut également choisir de rester dans la fenêtre, 
	 * mais il ne pourra plus jouer dans cette partie : il sera bloqué
	 * lorsqu'il passera un tour ou essaiera de jouer.
	 */
	private void gererPartieFinie() {
		String textePartieFinie = "La partie est désormais terminée.\n";
		
		int scoreJ1 = modeleJeu.getScoreJoueur1();
		int scoreJ2 = modeleJeu.getScoreJoueur2();
		
		retirerPositionsPossibles();
		
		if (scoreJ1 == scoreJ2) {
			textePartieFinie += "Égalité, bien joué à vous deux !";
		} else {
			textePartieFinie += (scoreJ1 > scoreJ2
							    ? modelePrincipal.getPseudoJoueur1()
							    : modelePrincipal.getPseudoJoueur2())
								+ " a gagné, bien joué à lui !";
		}
		
		/* Création d'une boîte d'alerte de type erreur. */
		Alert boitePartieTerminee = new Alert(Alert.AlertType.INFORMATION,
											  textePartieFinie);
		
		boitePartieTerminee.getDialogPane().getStylesheets().add(getClass()
				           .getResource("../vues/application.css").toExternalForm());
								    		  
		ButtonType boutonRejouer = new ButtonType("Rejouer");
        ButtonType boutonRetourMenuPrincipal
        = new ButtonType("Retourner au menu principal");
        ButtonType boutonRetourJeu = new ButtonType("Retourner à la fenêtre de jeu");

                	
        boitePartieTerminee.getButtonTypes()
        				   .setAll(boutonRejouer, boutonRetourMenuPrincipal,
        						   boutonRetourJeu);
									
		Stage stage = (Stage) boitePartieTerminee.getDialogPane()
												 .getScene().getWindow();
		stage.getIcons()
		.add(new Image("application/vues/images/Jeu/FinPartie.png"));
		
		boitePartieTerminee.setTitle("Othello - Partie terminée");
		boitePartieTerminee.setHeaderText("Partie terminée");
		Optional<ButtonType> resultat = boitePartieTerminee.showAndWait();
		
		if (resultat.get() == boutonRejouer) {
			boolean partieOrdinateur = modeleJeu.isPartieOrdinateur();
			boolean ordinateurFacile = modeleJeu.isOrdinateurFacile();
			
			modeleJeu = reinitialisationVueModeleJeu();
			
			modeleJeu.setPartieOrdinateur(partieOrdinateur);
			modeleJeu.setOrdinateurFacile(ordinateurFacile);
			
		} else if (resultat.get() == boutonRetourMenuPrincipal) {
			gererClicRetourMenuPrincipal();
		}
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
	
	/**
	 * Récupère les nodes via les coordonnées x et y.
	 * @param x la coordonnée x qui correspond à la colonne du pion.
	 * @param y la coordonnée y qui correspond à la ligne du pion.
	 */
    private Node getNodeParCoordonnees(int x, int y) {
        Node nodeCherchee = null;
    	
        for (Node nodeActuelle : this.toutesLesNodes) {
        	
        	// Récupération de l'identifiant de la node
    		String idCase = nodeActuelle.toString().substring(17, 19);
    		
    		if (Integer.parseInt(idCase.substring(0, 1)) == x
    			&& Integer.parseInt(idCase.substring(1)) == y) {
    			nodeCherchee = nodeActuelle;
    		}
        }
        return nodeCherchee;
    }
	
    /**
     * Faire apparaître sur le plateau de jeu un pion de la couleur
     * passée en paramètre aux coordonnées en paramètres.
     * 
     * @param couleur Couleur ("Blanc" ou "Noir") du pion.
     * @param coordonneeX Coordonnée horizontale du pion.
     * @param coordonneeY Coordonnée verticale du pion.
     */
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
		
		ajouterImage(pion);
	}
	
	/**
     * Faire apparaître sur le plateau de jeu un signalement sur les
     * cases cliquables selon les règles du jeu.
     * 
     * @param coordonneeX Coordonnée horizontale de la case.
     * @param coordonneeY Coordonnée verticale de la case.
     */
	private void apparaitrePosition(int coordonneeX, int coordonneeY) {
		ImageView position
		= new ImageView("application/vues/images/Jeu/PositionPossible.gif");
		
		position.setFitWidth(30);
		position.setFitHeight(30);
		position.setId("posi" + coordonneeX + coordonneeY);
		
		// Ajout d'un écouteur de clic à la position affiché
		position.addEventFilter(MouseEvent.MOUSE_CLICKED,
							new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        gererClicCase(mouseEvent);
		    }
		});
		

		GridPane.setHalignment(position, HPos.CENTER);
		GridPane.setColumnIndex(position, coordonneeX);
		GridPane.setRowIndex(position, coordonneeY);
		
		ajouterImage(position);
	}
	
	/**
	 * Retire tous les GIF indiquant les positions possibles de la vue.
	 */
	private void retirerPositionsPossibles() {
		for (Node nodeActuelle : this.toutesLesNodes) {
        	// Récupération de l'identifiant de la node
    		String idCase = nodeActuelle.toString().substring(13, 17);
    		
    		if (idCase.startsWith("posi")) {
    			retirerImage(nodeActuelle);
    		}
        }
	}
	
	private void apparaitrePionGIF(String couleur, int coordonneeX, int coordonneeY) {
		
		ImageView gifNoirVersBlanc = new ImageView(new Image("application/vues/images/Jeu/GIF1.gif"));
	    ImageView gifBlancVersNoir = new ImageView(new Image("application/vues/images/Jeu/GIF2.gif"));
	    
	   	gifNoirVersBlanc.setFitWidth(65);
	    gifNoirVersBlanc.setFitHeight(65);
	    
	    gifBlancVersNoir.setFitWidth(65);
	    gifBlancVersNoir.setFitHeight(65);
		

		gifNoirVersBlanc.setId("pionGif" + coordonneeX + coordonneeY);
		gifBlancVersNoir.setId("pionGif" + coordonneeX + coordonneeY);

		// Ajout d'un écouteur de clic au pion affiché
		gifNoirVersBlanc.addEventFilter(MouseEvent.MOUSE_CLICKED,
										new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        gererClicCase(mouseEvent);
		    }
		});
		
		GridPane.setColumnIndex(gifNoirVersBlanc, coordonneeX);
		GridPane.setRowIndex(gifNoirVersBlanc, coordonneeY);
		
		ajouterImage(gifNoirVersBlanc);
	}
	
	private void ajouterImage(ImageView image) {
		ajouterAToutesLesNodes(image);
		plateau.getChildren().add(image);
	}
																			
	private void retirerImage(Node caseNode) {													
		retirerAToutesLesNodes(caseNode);	
		plateau.getChildren().remove(caseNode);
	}
	
	/**
	 * Alterne la position de la flèche indiquant 
	 * qui doit jouer entre le joueur1 et le joueur2
	 * Cette flèche est celle apparaissant devant les noms des joueurs.
	 */
	private void permuterFlecheTour() {
		if (modeleJeu.isTourJoueur1()) {
			tourJoueur2.setVisible(false);
			tourJoueur1.setVisible(true);
		} else {
			tourJoueur1.setVisible(false);
			tourJoueur2.setVisible(true);
		}
	}
	
	/**
	 * Mise à jour des scores des 2 joueurs après clic.
	 */
	private void mettreScoreAJour() {
		scoreJoueur1.setText("" + modeleJeu.getScoreJoueur1());
		scoreJoueur2.setText("" + modeleJeu.getScoreJoueur2());
	}
	
	/*private void retirerPionGIF(Node caseNode) {
		retirerAToutesLesNodes(caseNode);
		plateau.getChildren().remove(caseNode);
	}*/  
	
	/**
	 * Ajouter à la liste de l'attribut toutesLesNodes un élément.
	 * 
	 * @param element Node à ajouter à la toutesLesNodes.
	 */
	private void ajouterAToutesLesNodes(Node element) {
		boolean nodeDejaExistante = false;
		
		for (Node nodeActuelle : this.toutesLesNodes) {
			if (nodeActuelle == element) {
				nodeDejaExistante = true;
			}
		}
		
		if (!nodeDejaExistante) {
			this.toutesLesNodes = Arrays.copyOf(toutesLesNodes,
											    toutesLesNodes.length + 1);			
			this.toutesLesNodes[this.toutesLesNodes.length - 1] = element;
		}
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
	
	/**
	 * Récupère la couleur correspondante au joueur actuel.
	 * @return "Noir" ou "Blanc" selon le tour du joueur.
	 */
	private String getCouleurActuellePion() {
		if (modeleJeu.isTourJoueur1()) {
			return "Noir";
		}
		// else
		return "Blanc";
	}
	
	/**
	 * Si l'option est activée, affiche les positions possibles
	 * du tour suivant.
	 */
	private void afficherPositionsPossibles() {
				
		int[][] getPositionsPossibles = modeleJeu.rechercheCasesClicPossible();
		
		boolean positionsPossiblesExistent = getPositionsPossibles.length > 0;
		
		if (modelePrincipal.getVoirPositionsPossibles()
			&& positionsPossiblesExistent) {
				
			for (int[] position : getPositionsPossibles) {
				apparaitrePosition(position[0], position[1]);
			}
		}
		
		if (!positionsPossiblesExistent) {
			final String PASSER_TOUR = "Vous ne pouvez pas poser de pion.\n"
									   + "Veuillez passer votre tour.";
			
			/* Création d'une boîte d'alerte de type attention. */
			Alert boitePasserTour = new Alert(Alert.AlertType.WARNING,
										      PASSER_TOUR);
			
			boitePasserTour.getDialogPane().getStylesheets().add(getClass()
			               .getResource("../vues/application.css").toExternalForm());
									    		    
			ButtonType boutonPasserTour = new ButtonType("Passer le tour");
	
	        boitePasserTour.getButtonTypes().setAll(boutonPasserTour);
										
			Stage stage = (Stage) boitePasserTour.getDialogPane()
												 .getScene().getWindow();
			stage.getIcons()
			.add(new Image("application/vues/images/Attention.png"));
			
			boitePasserTour.setTitle("Othello - Passer le tour");
			boitePasserTour.setHeaderText("Impossible de placer un pion");
			Optional<ButtonType> resultat = boitePasserTour.showAndWait();
			
			if (resultat.get() == boutonPasserTour) {
				gererClicPasserTour();
			}
		}
	}
	
	@FXML
	private void gererClicCase(MouseEvent event) {
		
		if (modeleJeu.isPartieCommencee()
			&& !modeleJeu.partieFinie()) {
		
			String couleurActuellePion = getCouleurActuellePion();
			
			Node caseCliquee = (Node) event.getTarget();
			
			ajouterAToutesLesNodes(caseCliquee);
			
			// Récupération de l'identifiant de l'image cliquée
			String idCase = caseCliquee.toString().substring(13, 19);
			
			int[] coordonnees = getCoordonneesCase(idCase);
			int coordonneeX = coordonnees[0];
			int coordonneeY = coordonnees[1];
	
			System.out.println(LOG_FICHIER
							   + "Case cliquée :"
							   + "\nID = "+ idCase
							   + "\nX = " + coordonneeX
							   + "\nY = " + coordonneeY);
			
			// Si image cliquée == image/fond invisible ou icône position possible
			if (idCase.startsWith("case") || idCase.startsWith("posi")) {
				
				int[][] pionsARetourner = modeleJeu.clicCase(coordonneeX,
														     coordonneeY);
				
				/* Si pionsARetourner.length == 0
				 * => joueur peut pas placer de pion là où il a cliqué */
				if (pionsARetourner.length == 0) {
					gererErreurClicImpossible(modeleJeu.isTourJoueur1());
				} else {
					
					modeleJeu.setPartieEnCours(true);
					modeleJeu.reinitialiserTourPasses();
					
					retirerPositionsPossibles();
					
	//				apparaitrePionGIF(couleurActuellePion,
	//							   	  coordonneeX, coordonneeY);
	//				
	//				/* 
	//				 * Une fois que le gif est lancé, on mets en pause de 
	//				 * 100ms (la durée d'un cycle du gif) 
	//				 * et on fait apparaitre le pion
	//				 */
	//				 try {
	//					Thread.sleep(100);  // soit ca soit des timers mais on va encore importer 20000 trucs
	//				 } catch (InterruptedException e) {
	//            		e.printStackTrace();
	//       		 }
	//               retirerPionGIF()
	       			 
					apparaitrePion(couleurActuellePion,
								   coordonneeX, coordonneeY);
					
					System.out.println("\nPion(s) à retourner :");
					for (int[] pionARetourner : pionsARetourner) {
						int coordonneeXPionCourant = pionARetourner[0];
						int coordonneeYPionCourant = pionARetourner[1];
						
						System.out.println("X = "
										   + coordonneeXPionCourant
										   + "\tY = "
										   + coordonneeYPionCourant);
						
						Node nodePion
						= getNodeParCoordonnees(coordonneeXPionCourant,
											    coordonneeYPionCourant);
						retirerImage(nodePion);
						apparaitrePion(couleurActuellePion,
									   coordonneeXPionCourant,
									   coordonneeYPionCourant);
					}
					
					mettreScoreAJour();
					permuterFlecheTour();
					
					verifierFinPartie();
					
					if (modeleJeu.isPartieOrdinateur()
						&& !modeleJeu.isTourJoueur1()) {
						if (modeleJeu.rechercheCasesClicPossible().length > 0) {
							jouerOrdinateur();							
						} else {
							gererClicPasserTour();
						}
					}
				}
			}
		} else {
			System.out.println(LOG_FICHIER
							   + "La partie n'est pas commencée ou est finie.\n");
			verifierFinPartie();
		}
	}
	
	/**
	 * Jeu de l'ordinateur si la partie a été lancée contre un ordinateur.
	 * En fonction de sa difficulté l'ordinateur va jouer un coup plus
	 * ou moins efficace.
	 */
	private void jouerOrdinateur() {
		
		int[] caseChoisie = modeleJeu.choixOrdinateur();
		
		if (caseChoisie == null) {
			System.out.println("jouerOrdinateur caseChoisie = null");
			gererClicPasserTour();
		}
		
		int coordonneeX = caseChoisie[0];
		int coordonneeY = caseChoisie[1];
		
		int[][] pionsARetourner = modeleJeu.clicCase(coordonneeX,
													 coordonneeY);
		
		modeleJeu.setPartieEnCours(true);
		modeleJeu.reinitialiserTourPasses();
		
		apparaitrePion("Blanc", coordonneeX, coordonneeY);
		
		System.out.println("\nPion(s) à retourner :");
		for (int[] pionARetourner : pionsARetourner) {
			int coordonneeXPionCourant = pionARetourner[0];
			int coordonneeYPionCourant = pionARetourner[1];
			
			System.out.println("X = "
			+ coordonneeXPionCourant
			+ "\tY = "
			+ coordonneeYPionCourant);
			
			Node nodePion
			= getNodeParCoordonnees(coordonneeXPionCourant,
								    coordonneeYPionCourant);
			retirerImage(nodePion);
			apparaitrePion("Blanc",
						   coordonneeXPionCourant,
						   coordonneeYPionCourant);
		}
		
		mettreScoreAJour();
		permuterFlecheTour();
		
		verifierFinPartie();
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
		System.out.println(LOG_FICHIER + "Impossible placer pion où clic\n");
		
		boolean clicsImpossiblesMax
		= modeleJeu.getNombreErreursPlacementJoueur1()
		  >= ModeleJeu.NOMBRE_MAX_ERREURS_PLACEMENT
		  || modeleJeu.getNombreErreursPlacementJoueur2()
		     >= ModeleJeu.NOMBRE_MAX_ERREURS_PLACEMENT;
		
		// Au bout de 5 clics impossibles => Affichage aide
		if (clicsImpossiblesMax) {
			gererClicAide();
			modeleJeu.reinitialiserNombreErreurs(modeleJeu.isTourJoueur1());
		}
	}
	
	@FXML // TODO Loic d'apres "J'ai pété" c'est comme ca qu'on utilise un gif, ping moi si tu bite rien (tiens bite est un mot du dictionnaire ¯\_(ツ)_/¯) 
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