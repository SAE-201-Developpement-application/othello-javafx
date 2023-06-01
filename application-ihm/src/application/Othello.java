/*
 * Othello.java 				                     31 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application;

import application.controleurs.*;
import application.vues.GestionVues;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Classe principale de l'application permettant d'instancier
 * les contrôleurs, les vues et modèles.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class Othello extends Application {
	
	/** Diverses scènes de l'application contenant les vues. */
	private static Scene scenePrincipale;
	private static Scene sceneParametres;
	private static Scene sceneNiveauOrdinateur;
	private static Scene sceneChoixPseudosContreJoueur;
	private static Scene sceneChoixPseudosContreIA;
	//private static Scene sceneJeu;	
	
	public static Scene[] scenes = {
		scenePrincipale, sceneParametres, sceneNiveauOrdinateur,
		sceneChoixPseudosContreJoueur, sceneChoixPseudosContreIA, //sceneJeu
	};
	
	private static String[] ressources = {
		"VueMenuPrincipal.fxml", "VueParametres.fxml", "VueNiveauOrdinateur.fxml",
		"VueChoixPseudosContreJoueur.fxml", "VueChoixPseudoContreAI.fxml"
	};
	
	/**
	 * Fenêtre principale de l'application
	 * La scène qui lui est associée sera modifiée
	 * par la classe héritée GestionVues en fonction
	 * des clics de l'utilisateur.
	 */
	public static Stage fenetrePrincipale;
	
	@Override
 	public void start(Stage primaryStage) {
		try {
			
			for (int indiceScene = 0; indiceScene < scenes.length; indiceScene++) {
				/*
				 * Chargement de la vue et création
				 * de la scène associée à cette vue
				 */
				FXMLLoader chargeurFXMLCourant = new FXMLLoader();
				chargeurFXMLCourant.setLocation(
						GestionVues.class.getResource(ressources[indiceScene]));
				Parent conteneur = chargeurFXMLCourant.load();
				
				/* Création de la scène correspondante à la vue chargée */
				scenes[indiceScene] = new Scene(conteneur, 975, 579);
			}
			
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
			primaryStage.setScene(scenes[0]);
			fenetrePrincipale = primaryStage;
			fenetrePrincipale.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Programme principal
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		
		launch(args);
		new ControleurPrincipal();
		
	}
}