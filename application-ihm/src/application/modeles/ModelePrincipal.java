/*
 * ModelePrincipal.java											    26 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.modeles;

/**
 * Modèle principal de l'application Othello.
 * Celui-ci contient les principaux paramètres et va
 * faire appel aux autres modèles en fonction des actions
 * des utilisateurs.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ModelePrincipal {
	// TODO coder
	
	/* Type de partie : 1 contre un ordinateur, 2 contre un autre joueur et 0 par défaut*/
	private int typePartie = 0;
	
	/* Pseudo du premier joueur*/
	private String nomJoueur1;
	
	/* Pseudo du deuxieme joueur, si il existe*/
	private String nomJoueur2;
	
	private int difficulteOrdinateur;
	
	private boolean partieCommencee;
	
	private boolean etatSauvegarde;
	
	private boolean musique;
	
	private boolean son;
	
	private boolean voirPositionsPossibles;
	
	private boolean voirPionsEnlevables;	
	
	public void validerParametres() {
		
	}
	
}
