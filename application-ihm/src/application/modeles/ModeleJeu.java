/*
 * ModeleJeu.java											    26 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.modeles;

/**
 * Modèle gérant le jeu d'Othello.
 * Celui-ci s'occupe de toute la partie gestion
 * du jeu de l'application.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class ModeleJeu extends ModelePrincipal {

	/** Représentation d'une case vide. */
	private final int CASE_VIDE = 0;
	
	/** Représentation d'une case contenant un pion noir. */
	private final int NOIR = 1;
	
	/** Représentation d'une case contenant un pion blanc. */
	private final int BLANC = 2;
	
	/** Définit le type de partie, contre l'odinateur ou joueur */
	private boolean partieOrdinateur;
	
	/** Définit la difficultée de la partie, facile ou difficile */
	private boolean ordinateurFacile;

	/** Définit si le joueur1 joue */
	private boolean joueur1Tour = true;
	
	/** Nom du premier joueur. */
	private String nomJoueur1;
	
	/** Nom du second joueur (si il joue). */
	private String nomJoueur2;
	
	/** Points du premier joueur. */
	private String nbPointsJoueur2;
	
	/** Points du second joueur (si il joue). */
	private String nbPointsJoueur1;
	
	
		
	/** 
	 * Commence à 0 et s'incrémente si un joueur passe le tour.
	 * Si deux tours sont passés d'affilée, la partie s'arrete,
	 * sinon le nombre de tours passés repasse à 0.
	 */
	private int toursPasses = 0;

	/**
	 * Plateau de jeu composé d'un tableau 
	 * contenant 8 tableaux contenant chaqun 8 cases.
	 * On pourra en déduire des coordonnées x et y.
	 */
	private int[][] plateau = {{0,0,0,0,0,0,0,0},
							   {0,0,0,0,0,0,0,0},
							   {0,0,0,0,0,0,0,0},
							   {0,0,0,2,1,0,0,0},
							   {0,0,0,1,2,0,0,0},
							   {0,0,0,0,0,0,0,0},
							   {0,0,0,0,0,0,0,0},
							   {0,0,0,0,0,0,0,0}};
							   
							
	
	/** @return le plateau de jeu */						   
	public int[][] getPlateau() {
		return this.plateau;
	}
	
	/** @return le plateau de jeu */						   
	public int getToursPasses() {
		return this.toursPasses;
	}
	
	/** @return le nom du joueur 1 */						   
	public String  getNomJoueur1() {
		return this.nomJoueur1;
	}
	
	/** @return le score du joueur 1 */						   
	public String getNbPointJoueur1() {
		return this.nbPointsJoueur1;
	}
	
	/** @return le nom du joueur 2 */						   
	public String  getNomJoueur2() {
		return this.nomJoueur2;
	}

	/** @return le score du joueur 2 */						   
	public String getNbPointJoueur2() {
		return this.nbPointsJoueur2;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return si la case est vide
	 */
	public boolean caseVide(int x, int y) {
		if (plateau[y][x] == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	public void poserPion(int x, int y) {
		if (caseVide(x,y)) {
			if (joueur1Tour) {
				
				plateau[y][x] = 1;
			} else {
				plateau[y][x] = 2;
			}
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean placementPossible(int x, int y) {
		return true;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean placementPossibleHorizontal(int x, int y) {
		return true;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean placementPossibleVertical(int x, int y) {
		return true;
	}
	
	/**
	 * 
	 */
	public boolean placementPossibleDiagonal(int x, int y) {
		return true;
	}
}

