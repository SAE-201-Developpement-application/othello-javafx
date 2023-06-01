/*
 * ControleurPrincipal.java                    				            31 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.controleurs;

import application.modeles.*;
import application.vues.*;

/**
 * Contrôleur principal de l'application Othello.
 * Celui-ci instancie les modèles et permet aux contrôleurs enfants 
 * d'y avoir accès.
 * 
 * @author Loïc FAUGIERES
 */
public class ControleurPrincipal {
	
	public ModelePrincipal modelePrincipal = new ModelePrincipal();
	public ModeleSauvegardes modeleSauvegardes = new ModeleSauvegardes();
	public ModeleJeu modeleJeu = new ModeleJeu();

}