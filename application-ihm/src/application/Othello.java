/*
 * Main.java                      				                     25 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application;
	
import java.io.IOException;

import application.vues.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * Classe principale de l'application permettant d'afficher des fenêtres au contenu
 * différent (ce sont les vues qui changent).
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class Othello extends Application {
	
	/** Scène principale de l'application, celle qui contient les 2 boutons */
	private static Scene scenePrincipale;
	
	/** Scène permettant de gérer les paramètres */
	private static Scene sceneParametres;
	
	/** Scène permettant de gérer le niveau de l'ordinateur */
	private static Scene sceneNiveauOrdinateur;
	
	/** Scène permettant de gérer les pseudos en mode joueur contre joueur */
	private static Scene sceneChoixPseudosContreJoueur;
	
	/** Scène permettant de gérer le pseudo en mode joueur contre IA */
	private static Scene sceneChoixPseudosContreIA;
	
	/** Scène permettant de gérer le Jeu principal */
	private static Scene sceneJeu;
	
	
	/**
	 * Fenêtre principale de l'application
	 * La scène qui lui est associée sera modifiée en fonction
	 * des clics de l'utilisateur
	 */
	private static Stage fenetrePrincipale;
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du menu principal
	 */
	public static void activerMenuPrincipal() {
		fenetrePrincipale.setScene(scenePrincipale);
		fenetrePrincipale.setTitle("Othello - Menu principal");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle des paramètres
	 */
	public static void activerParametres() {
		fenetrePrincipale.setScene(sceneParametres);
		fenetrePrincipale.setTitle("Othello - Paramètres");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du niveau de l'ordinateur
	 */
	public static void activerNiveauOrdinateur() {
		fenetrePrincipale.setScene(sceneNiveauOrdinateur);
		fenetrePrincipale.setTitle("Othello - Niveau des ordinateurs");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du choix des pseudos joueur contre joueur
	 */
	public static void activerChoixPseudosContreJoueur() {
		fenetrePrincipale.setScene(sceneChoixPseudosContreJoueur);
		fenetrePrincipale.setTitle("Othello - Choix des pseudos");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du choix du pseudo joueur contre IA
	 */
	public static void activerChoixPseudoContreIA() {
		// TODO faire la vue choix du pseudos contre IA
		fenetrePrincipale.setScene(sceneChoixPseudosContreIA);
		fenetrePrincipale.setTitle("Othello - Choix du pseudo");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du Jeu principal
	 */
	public static void activerJeu() {
		// TODO faire la vue du Jeu (ca promet d'etre fun ca)
		fenetrePrincipale.setScene(sceneJeu);
		fenetrePrincipale.setTitle("Othello - En partie");
	}
	
	@Override
 	public void start(Stage primaryStage) {
		try {
			/*
			 * chargement de la vue de la scène principale dans le conteneur
			 * de type Parent
			 */
			FXMLLoader chargeurFXML = new FXMLLoader();
			chargeurFXML.setLocation(EnsembleDesVues.class.getResource("VueMenuPrincipal.fxml"));
			Parent conteneur = chargeurFXML.load();
			
			/*
			 * Création de la scène principale
			 */
			scenePrincipale = new Scene(conteneur, 975, 579);
			
			/*
			 * Chargement de la vue des paramètres et
			 * création de la scène associée à cette vue
			 */
			FXMLLoader chargeurFXMLParametres = new FXMLLoader();
			chargeurFXMLParametres.setLocation(EnsembleDesVues.class.getResource("VueParametres.fxml"));
			conteneur = chargeurFXMLParametres.load();
			sceneParametres = new Scene(conteneur, 975, 579);
			
			/*
			 * Chargement de la vue du niveau de l'ordinateur et
			 * création de la scène associée à cette vue
			 */
			FXMLLoader chargeurFXMLNiveauOrdinateur = new FXMLLoader();
			chargeurFXMLNiveauOrdinateur.setLocation(EnsembleDesVues.class.getResource("VueNiveauOrdinateur.fxml"));
			conteneur = chargeurFXMLNiveauOrdinateur.load();
			sceneNiveauOrdinateur = new Scene(conteneur, 975, 579);
			
			/*
			 * Chargement de la vue du niveau de l'ordinateur et
			 * création de la scène associée à cette vue
			 */
			FXMLLoader chargeurFXMLChoixPseudosContreJoueur = new FXMLLoader();
			chargeurFXMLChoixPseudosContreJoueur.setLocation(EnsembleDesVues.class.getResource("VueChoixPseudosContreJoueur.fxml"));
			conteneur = chargeurFXMLChoixPseudosContreJoueur.load();
			sceneChoixPseudosContreJoueur = new Scene(conteneur, 975, 579);
			
			// on définit le titre, la hauteur et la largeur de la fenêtre principale
			primaryStage.setTitle("Othello - Menu principal");
			primaryStage.setHeight(579);
			primaryStage.setWidth(975);
			
			primaryStage.getIcons().add(new Image("application/vues/images/Othello.png"));

			/*
			 * on associe la scène principale à la fenêtre principale
			 * Cette dernière est stockée en tant qu'attribut afin d'être accessible
			 * dans les méthodes activer... Celles qui permettent de rendre active
			 * l'une des 3 scènes
			 */
			primaryStage.setScene(scenePrincipale);
			fenetrePrincipale = primaryStage;
			fenetrePrincipale.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Programme principal
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		launch(args);
	}
}