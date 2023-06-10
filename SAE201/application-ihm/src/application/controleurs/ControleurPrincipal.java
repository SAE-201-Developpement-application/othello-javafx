/*
 * ControleurPrincipal.java                    				            31 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.modeles.*;

/**
 * Contrôleur principal de l'application Othello.
 * Celui-ci instancie les modèles et permet aux contrôleurs enfants
 * d'y avoir accès.
 * 
 * @author Loïc FAUGIERES
 */
public class ControleurPrincipal {
	
	public static ModelePrincipal modelePrincipal = new ModelePrincipal();
	public static ModeleSauvegardes modeleSauvegardes = new ModeleSauvegardes();
	public static ModeleJeu modeleJeu = new ModeleJeu();

}