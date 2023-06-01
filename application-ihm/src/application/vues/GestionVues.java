/*
 * GestionVues.java 			                     31 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.vues;
	
import java.io.IOException;

import application.Othello;

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
public class GestionVues extends Othello {
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du menu principal
	 */
	public static void activerMenuPrincipal() {
		fenetrePrincipale.setScene(scenes[0]);
		fenetrePrincipale.setTitle("Othello - Menu principal");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle des paramètres
	 */
	public static void activerParametres() {
		fenetrePrincipale.setScene(scenes[1]);
		fenetrePrincipale.setTitle("Othello - Paramètres");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du niveau de l'ordinateur
	 */
	public static void activerNiveauOrdinateur() {
		fenetrePrincipale.setScene(scenes[2]);
		fenetrePrincipale.setTitle("Othello - Niveau des ordinateurs");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du choix des pseudos joueur contre joueur
	 */
	public static void activerChoixPseudosContreJoueur() {
		fenetrePrincipale.setScene(scenes[3]);
		fenetrePrincipale.setTitle("Othello - Choix des pseudos");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du choix du pseudo joueur contre IA
	 */
	public static void activerChoixPseudoContreIA() {
		fenetrePrincipale.setScene(scenes[4]);
		fenetrePrincipale.setTitle("Othello - Choix du pseudo");
	}
	
	/**
	 * Permet de modifier la scène de la fenêtre principale
	 * pour qu'elle devienne celle du Jeu principal
	 */
	public static void activerJeu() {
		fenetrePrincipale.setScene(scenes[5]);
		fenetrePrincipale.setTitle("Othello - En partie");
	}
	
}