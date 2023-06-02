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
	
	/**
	 * Plateau de jeu composé d'un tableau 
	 * contenant 8 tableaux contenant chaqun 8 cases.
	 * On pourra en déduire des coordonnées x et y.
	 */
	private int[][] plateau = {
		{0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 2, 1, 0, 0, 0},
		{0, 0, 0, 1, 2, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0}
	};
							   
	/** Définit le type de partie, contre l'odinateur ou joueur */
	private boolean partieOrdinateur;
	
	/** Définit la difficultée de la partie, facile ou difficile */
	private boolean ordinateurFacile;

	/** Définit si le joueur1 joue */
	private boolean tourJoueur1 = true;
	
	/** Nom du premier joueur. */
	private String nomJoueur1;
	
	/** Nom du second joueur */
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

	private final static int COLONNE_GAUCHE = 0;
    private final static int COLONNE_DROITE = 7;
    
    private final static int LIGNE_HAUTE = 0;
    private final static int LIGNE_BASSE = 7;
	
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
	
	/** @param nouveauPlateau Plateau remplaçant l'attribut plateau de this. */
	public void setPlateau(int[][] nouveauPlateau) {
		this.plateau = nouveauPlateau;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return si la case est vide
	 */
	public boolean caseVide(int x, int y) {
		return plateau[y][x] == 0;
	}
	
	/**
	 * Appelée lors d'un clic sur la grille
	 */
	public void poserPionSiCaseVide(int x, int y) {
		if (caseVide(x, y)) {	
			plateau[y][x] = tourJoueur1 ? NOIR : BLANC;
		}
		
	}
	
	/**
	 * Vérifie la possibilité de placer un pion sur une case de
	 * coordonnées x et y en fonction des règles d'Othello.
	 *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
	 * @return true si le pion peut être placé, false sinon.
	 */
	public boolean placementPossible(int x, int y) {
		return placementPossibleHorizontal(x, y)
		       || placementPossibleVertical(x, y);
		         /*|| placementPossibleDiagonales(x, y)*/
	}
	
	/**
	 * Vérification de la possibilité de placer un pion sur une case
	 * en fonction de la possibilité de retourner des pions
	 * adverses à sa gauche ou sa droite.
	 * <p>
	 * Lorsqu'un pion est à l'extrémité d'une ligne, il n'est possible
     * de le retourner que de façon verticale ; c'est-à-dire qu'il est possible
     * de le retourner seulement en étant dans la même colonne.</p>
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
	 */
	private boolean placementPossibleHorizontal(int x, int y) {
		
		if (x == COLONNE_GAUCHE
			|| x == COLONNE_GAUCHE + 1) {
			return placementPossibleDroite(x, y);
			
		} else if (x == COLONNE_DROITE
				   || x == COLONNE_DROITE - 1) {
			return placementPossibleGauche(x, y);
		}
		// else
		return placementPossibleDroite(x, y)
			   || placementPossibleGauche(x, y);
	}
	
	/**
	 * Vérification de la possibilité de placer un pion sur une case
	 * en fonction de la possibilité de retourner des pions
	 * adverses à sa droite.
	 * Appelée par placementPossibleHorizontal
	 *
	 * @param x Coordonnée X (colonne) de la case à vérifier.
	 * @param y Coordonnée Y (ligne) de la case à vérifier.
	 * @return true si le pion peut être placé, false sinon.
	 */
	private boolean placementPossibleDroite(int x, int y) {
		
		final int CASE_VERIFIEE = plateau[y][x];
		final int CASE_DROITE = plateau[y][x + 1];

		boolean resultat = false;
		
		if (CASE_VERIFIEE == CASE_DROITE || CASE_DROITE == CASE_VIDE) {
		    return resultat;
		}
		
		int indiceX = x + 2;
		
		/* Parcours de la ligne à droite de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
		while (indiceX < plateau[y].length
		       && !caseVide(indiceX, y)
		       && !resultat) {
			int caseCourante = plateau[y][indiceX];

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == CASE_VERIFIEE) {
				resultat = true;
			}
			indiceX++;
		}
		return resultat;
	}
	
	/**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses à sa gauche.
     * Appelée par placementPossibleHorizontal
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier
     * @return true si le pion peut être placé, false sinon
     */
    private boolean placementPossibleGauche(int x, int y) {
        
        final int CASE_VERIFIEE = plateau[y][x];
        final int CASE_GAUCHE = plateau[y][x - 1];

        boolean resultat = false;
        
        if (CASE_VERIFIEE == CASE_GAUCHE || CASE_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        int indiceX = x - 2;
        
        /* Parcours de la ligne à gauche de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX > 0
               && !caseVide(indiceX, y)
               && !resultat) {
            int caseCourante = plateau[y][indiceX];

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == CASE_VERIFIEE) {
                resultat = true;
            }
            indiceX--;
        }
        return resultat;
    }
	
	/**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses au-dessus ou en-dessous.
     * <p>
     * Lorsqu'un pion est à l'extrémité d'une colonne, il n'est possible
     * de le retourner que de façon horizontale ; c'est-à-dire qu'il est possible
     * de le retourner seulement en étant dans la même ligne.</p>
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleVertical(int x, int y) {
        
        if (y == LIGNE_HAUTE
            || y == LIGNE_HAUTE + 1) {
            return placementPossibleBas(x, y);
            
        } else if (y == LIGNE_BASSE
                   || y == LIGNE_BASSE - 1) {
            return placementPossibleHaut(x, y);
        }
        // else
        return placementPossibleBas(x, y)
               || placementPossibleHaut(x, y);
    }
    
    /**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses en-dessous.
     * Appelée par placementPossibleVertical.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleBas(int x, int y) {
        
        final int CASE_VERIFIEE = plateau[y][x];
        final int CASE_DESSOUS = plateau[y + 1][x];

        boolean resultat = false;
        
        if (CASE_VERIFIEE == CASE_DESSOUS || CASE_DESSOUS == CASE_VIDE) {
            return resultat;
        }
        
        int indiceY = y + 2;
        
        /* Parcours de la colonne en-dessous de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY < plateau.length
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == CASE_VERIFIEE) {
                resultat = true;
            }
            indiceY++;
        }
        return resultat;
    }
    
    /**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses au-dessous.
     * Appelée par placementPossibleVertical.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleHaut(int x, int y) {
        
        final int CASE_VERIFIEE = plateau[y][x];
        final int CASE_DESSUS = plateau[y - 1][x];

        boolean resultat = false;
        
        if (CASE_VERIFIEE == CASE_DESSUS || CASE_DESSUS == CASE_VIDE) {
            return resultat;
        }
        
        int indiceY = y - 2;
        
        /* Parcours de la colonne au-dessus de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY > 0
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == CASE_VERIFIEE) {
                resultat = true;
            }
            indiceY--;
        }
        return resultat;
    }
	
	/**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans une de ses diagonales.
     * <p>
     * Lorsqu'un pion est à l'extrémité d'une ligne ou d'une colonne (sur
     * un bord du plateau), il n'est pas possible de le retourner
     * depuis un pion placé en diagonale.</p>
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleDiagonales(int x, int y) {
        
        // X = 0 ou X = 1
        if (x <= COLONNE_GAUCHE + 1) {
			
			/* ---------------------------------
               Pas de vérification sur la gauche
               --------------------------------- */
			
			// Y = 0 ou Y = 1
			if (y <= LIGNE_HAUTE + 1) {
				return placementPossibleBasDroite(x, y);
			}
			// Y = 6 ou Y = 7
			else if (y >= LIGNE_BASSE - 1) {
				return placementPossibleHautDroite(x, y);
			}
			// Y >= 2 et Y <= 5
			else {
				return placementPossibleBasDroite(x, y)
				       || placementPossibleHautDroite(x, y);
			}
		}
		
		// X = 6 ou X = 7
		else if (x >= COLONNE_DROITE - 1) {
			
			/* ---------------------------------
			   Pas de vérification sur la droite
			   --------------------------------- */
			
			// Y = 0 ou Y = 1
            if (y <= LIGNE_HAUTE + 1) {
				return placementPossibleBasGauche(x, y);
            }
            // Y = 6 ou Y = 7
            else if (y >= LIGNE_BASSE - 1) {
				return placementPossibleHautGauche(x, y);
            }
            // Y >= 2 ou Y <= 5
            else {
				return placementPossibleBasGauche(x, y)
                       || placementPossibleHautGauche(x, y);
            }
		}
		
		// Y = 0 ou Y = 1
		else if (y <= LIGNE_HAUTE + 1) {
			
			return placementPossibleBasGauche(x, y)
				   || placementPossibleBasDroite(x, y);			
		}
		
		// Y = 6 ou Y = 7
		else if (y >= LIGNE_BASSE - 1) {
			return placementPossibleHautGauche(x, y)
				   || placementPossibleHautDroite(x, y);	
		}
		
		//    2 <= X <= 5
		// et 2 <= Y <= 5
        // else
        return placementPossibleHautGauche(x, y)
               || placementPossibleHautDroite(x, y)
               || placementPossibleBasGauche(x, y)
			   || placementPossibleBasDroite(x, y);
    }
    
    /*
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans une de ses diagonales.
     * <p>
     * Lorsqu'un pion est à l'extrémité d'une ligne ou d'une colonne (sur
     * un bord du plateau), il n'est pas possible de le retourner
     * depuis un pion placé en diagonale.</p>
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleHautGauche(int x, int y) {
		return true;
	}
	
	private boolean placementPossibleHautDroite(int x, int y) {
		return true;
	}
	
	private boolean placementPossibleBasGauche(int x, int y) {
		return true;
	}
	
	private boolean placementPossibleBasDroite(int x, int y) {
		return true;
	}
    	
}

