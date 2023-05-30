/*
 * ModelePrincipal.java											    26 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.modeles;

/**
 * Modèle des sauvegardess de l'application Othello.
 * Celui-ci s'occupe de la partie enregistrement et
 * chargement des sauvegardes du jeu.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ModeleSauvegardes {
	// TODO coder
	private String nomPartie;
	
	private String datePartie;
	
	private int typePartie;
	
	private String pseudo1;
	
	private String pseudo2;
	
	private int etatPlateau;
	
	public void sauvegarderPartieCourante() {
		
	}
	
	/**
	 * Accesseur de l'attribut nomPartie
	 */
	public String getNomPartie(){
		return nomPartie;
	}
	
	/**
	 * Accesseur de l'attribut datePartie
	 */
	public String getDatePartie() {
		return datePartie;
	}
	
	/**
	 * Accesseur de l'attribut typePartie
	 */
	public int getTypePartie() {
		return typePartie;
	}
	
	/**
	 * Modifieur de l'attribut nomPartie
	 */
	public void setNomPartie(String nomPartie) {
		
	}
	
	/**
	 * Modifieur de l'attribut datePartie
	 */
	public void setDatePartie(String date) {
		
	}
	
	/**
	 * Modifieur de l'attribut typePartie
	 */
	public void setTypePartie(int typePartie) {
		
	}
	
} 
