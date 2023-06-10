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
public class ModeleSauvegardes extends ModelePrincipal {
	
	private String nomPartieSauvegardee;
	
	private String datePartieSauvegardee;
		
	private String pseudo1PartieSauvegardee;
	
	private String pseudo2PartieSauvegardee;
	
	private int typePartieSauvegardee;

	private int plateauPartieSauvegardee;
	
	/** @return le nom de la partie sauvegardée */
	public String getNomPartie(){
		return nomPartieSauvegardee;
	}
	
	/** @return la date de la partie sauvegardée */
	public String getDatePartie() {
		return datePartieSauvegardee;
	}
	
	/** @return le pseudo du joueur 1 de la partie sauvegardée */
    public String getPseudo1PartieSauvegardee() {
        return pseudo1PartieSauvegardee;
    }
    
    /** @return le pseudo du joueur 2 de la partie sauvegardée */
    public String getPseudo2PartieSauvegardee() {
        return pseudo2PartieSauvegardee;
    }
    
    /** @return le type de la partie sauvegardée */
    public int getTypePartie() {
        return typePartieSauvegardee;
    }
    
    /** @return le plateau de la partie sauvegardée */
    public int getPlateauPartieSauvegardee() {
        return plateauPartieSauvegardee;
    }
    
    /** Modifie le nom de la partie sauvegardée */
    public void setNomPartie(String nomPartie) {
        nomPartieSauvegardee = nomPartie;
    }
    
    /** Modifie la date de la partie sauvegardée */
    public void setDatePartie(String datePartie) {
        datePartieSauvegardee = datePartie;
    }
    
    /** Modifie le pseudo du joueur 1 de la partie sauvegardée */
    public void setPseudo1PartieSauvegardee(String pseudo) {
        pseudo1PartieSauvegardee = pseudo;
    }
    
    /** Modifie le pseudo du joueur 2 de la partie sauvegardée */
    public void setPseudo2PartieSauvegardee(String pseudo) {
        pseudo2PartieSauvegardee = pseudo;
    }
    
    /** Modifie le type de la partie sauvegardée */
    public void setTypePartie(int typePartie) {
        typePartieSauvegardee = typePartie;
    }
    
    /** Modifie le plateau de la partie sauvegardée */
    public void setPlateauPartieSauvegardee(int plateau) {
        plateauPartieSauvegardee = plateau;
    }
	
	/**
	 * Sauvegarde les données de la partie courante.
	 */
	public void sauvegarderPartieCourante() {
		// TODO : Méthode permettant de sauvegarder
		// la partie courante dans un fichier
	}
		
} 
