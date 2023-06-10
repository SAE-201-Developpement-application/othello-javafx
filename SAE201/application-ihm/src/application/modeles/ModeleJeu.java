/*
 * ModeleJeu.java											    26 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.modeles;

import java.util.Arrays;
import java.util.Random;

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
	
	private final static int COLONNE_GAUCHE = 0;
    private final static int COLONNE_DROITE = 7;
    
    private final static int LIGNE_HAUTE = 0;
    private final static int LIGNE_BASSE = 7;
    
    /**
     * Nombre maximal d'erreurs de placement impossible d'un pion
     * avant qu'une alerte informe le joueur des règles.
     */
    public final static int NOMBRE_MAX_ERREURS_PLACEMENT = 5;
    
    /**
	 * Liste vide servant à réinitialiser la liste des pions à retourner dans la vue.
	 */
	private int[][] PIONS_A_RETOURNER_PAR_DEFAUT = {}; // Valeur par défaut
	
	/**
	 * Plateau de jeu composé d'un tableau 
	 * contenant 8 tableaux contenant chacun 8 cases.
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

	/** Case verifiée lors d'un clic */
	private int caseVerifiee;
							   
	/** Définit le type de partie, contre l'odinateur ou joueur */
	private boolean partieOrdinateur;
	
	/** Définit la difficulté de la partie, facile ou difficile */
	private boolean ordinateurFacile;

	/** Définit si le joueur1 joue. */
	private boolean tourJoueur1 = true;
	
	/** Définit si une partie est commencée */
	private boolean partieCommencee;
	
	/** Définit si une partie est en cours */
	private boolean partieEnCours;

	/** Points du premier joueur. */
	private int scoreJoueur1 = 2;
	
	/** Points du second joueur (ou du bot). */
	private int scoreJoueur2 = 2;
	
	/** 
	 * Commence à 0 et s'incrémente si un joueur passe le tour.
	 * Si deux tours sont passés d'affilée, la partie s'arrete,
	 * sinon le nombre de tours passés repasse à 0.
	 */
	private int toursPasses = 0;
	
	/**
	 * Lorsque ce nombre sera égal à NOMBRE_MAX_ERREURS_PLACEMENT,
	 * une fenêtre d'alerte expliquera au joueur 1 les règles.
	 */
	private int nombreErreursPlacementJoueur1 = 0;
	
	/**
	 * Lorsque ce nombre sera égal à NOMBRE_MAX_ERREURS_PLACEMENT,
	 * une fenêtre d'alerte expliquera au joueur 2 les règles.
	 */
	private int nombreErreursPlacementJoueur2 = 0;
	
	/**
	 * Liste des coordonnées des pions à retourner dans la vue.
	 */
	private int[][] pionsARetourner = {}; // Valeur par défaut
	
	/** @return le plateau de jeu. */						   
	public int[][] getPlateau() {
		return this.plateau;
	}
	
	/** @return si la partie est contre un ordinateur */
    public boolean isPartieOrdinateur() {
        return partieOrdinateur;
    }
    
    /** @return si la partie est facile ou difficile */
    public boolean isOrdinateurFacile() {
        return ordinateurFacile;
    }
    
    /** @return si c'est au joueur 1 de jouer' */
    public boolean isTourJoueur1() {
        return tourJoueur1;
    }
	
    /** @return si la partie est commencée (avant clic sur "Play") */
    public boolean isPartieCommencee() {
    	return partieCommencee;
    }
    
    /** @return si la partie est en cours */
    public boolean isPartieEnCours() {
    	return partieEnCours;
    }
    
	/** @return le score du joueur 1. */						   
	public int getScoreJoueur1() {
		return this.scoreJoueur1;
	}

	/** @return le score du joueur 2 */						   
	public int getScoreJoueur2() {
		return this.scoreJoueur2;
	}
	
	/** @return le nombre de tours consécutif passés. */                          
    public int getToursPasses() {
        return this.toursPasses;
    }
	
	/** @return le nombre d'erreurs de placement du joueur 1 */					   
	public int getNombreErreursPlacementJoueur1() {
		return this.nombreErreursPlacementJoueur1;
	}
	
	/** @return le nombre d'erreurs de placement du joueur 2 */						   
	public int getNombreErreursPlacementJoueur2() {
		return this.nombreErreursPlacementJoueur2;
	}
	
	/** @return la liste des pions à retourner */
    public int[][] getPionsARetourner() {
        return this.pionsARetourner;
    }

	/** @param nouveauPlateau Plateau remplaçant l'attribut plateau de this. */
	public void setPlateau(int[][] nouveauPlateau) {
		this.plateau = nouveauPlateau;
	}
	
	/** @param partieOrdinateur Si la partie est contre un ordinateur */
	public void setPartieOrdinateur(boolean partieOrdinateur) {
		this.partieOrdinateur = partieOrdinateur;
	}

	/** @param ordinateurFacile Si la partie est facile (false si difficile) */
	public void setOrdinateurFacile(boolean ordinateurFacile) {
		this.ordinateurFacile = ordinateurFacile;
	}
	
	/** @param tour Si le tour est celui du joueur 1 */
    public void setTourJoueur1(boolean tour) {
        this.tourJoueur1 = tour;
    }
    
    /** @param partieCommence si la partie est commencée */
    public void setPartieCommencee(boolean partieCommence) {
    	this.partieCommencee = partieCommence;
    }
    
    /** @param partieEnCours si la partie est en cours */
    public void setPartieEnCours(boolean partieEnCours) {
    	this.partieEnCours = partieEnCours;
    }
    
    /** @param score Nouveau score du joueur 1*/
    public void setScoreJoueur1(int score) {
        this.scoreJoueur1 = score;
    }
    
    /** @param score Nouveau score du joueur 2 */
    public void setScoreJoueur2(int score) {
        this.scoreJoueur2 = score;
    }
    
    /** @param score Nouveau score du joueur 1 */
    public void augmenterScoreJoueur1(int score) {
        this.scoreJoueur1 += score;
    }
    
    /** @param score Nouveau score du joueur 1 */
    public void reduireScoreJoueur1(int score) {
        this.scoreJoueur1 -= score;
    }
    
    /** @param score Nouveau score du joueur 2 */
    public void augmenterScoreJoueur2(int score) {
        this.scoreJoueur2 += score;
    }
    
    /** @param score Nouveau score du joueur 2 */
    public void reduireScoreJoueur2(int score) {
        this.scoreJoueur2 -= score;
    }

	/**
	 * Incrémentation du nombre de tours consécutifs passés.
	 */
    public void passerTour() {
        this.toursPasses++;  
        tourJoueur1 = tourJoueur1 ? false : true;  
    }
    
    /**
     * Réinitialisation à 0 du nombre courant de
     * tours consécutifs passés.
     */
    public void reinitialiserTourPasses() {
        this.toursPasses = 0;       
    }
    
    /** Incrémente le nombre d'erreurs de placement du joueur 1 */                    
    public void ajouterErreurPlacementJoueur1() {
    	this.nombreErreursPlacementJoueur1++;
    }
    
    /** Incrémente le nombre d'erreurs de placement du joueur 2 */                        
    public void ajouterErreurPlacementJoueur2() {
        this.nombreErreursPlacementJoueur2++;
    }
    
    /**
     * Réinitialise le nombre d'erreurs du joueur 1 si tourJ1 est true
     * sinon le nombre d'erreurs du joueur 2.
     * @param tourJ1 true si c'est le tour du joueur 1.
     */
    public void reinitialiserNombreErreurs(boolean tourJ1) {
    	if (tourJ1) {
    		this.nombreErreursPlacementJoueur1 = 0;
    	} else {
    		this.nombreErreursPlacementJoueur2 = 0;	
    	}
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetourner() {
        this.pionsARetourner = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Ajoute à la liste des pions à retourner, les pions de la liste en paramètre.
     * 
     * @param liste La liste contenant les pions à ajouter
     * 		  à la liste des pions à retourner de this.
     */
    public void ajouterPionsARetourner(int[][] liste) {
		
		for (int indicePion = 0;
			 indicePion < liste.length; 
			 indicePion++) {
			
			this.pionsARetourner = Arrays.copyOf(pionsARetourner,
												 pionsARetourner.length + 1);
				
			this.pionsARetourner[pionsARetourner.length - 1]
			= liste[indicePion];
		}                
	}
	
    /**
     * Ajout d'un pion (à travers ses coordonnées) à la liste
     * de listes d'entiers en paramètre.
     * 
     * @param coordonneesPion Les coordonnées du pion à ajouter
     *                        à la liste temporaire.
     * @param liste La liste à laquelle ajouter les coordonnées.
     */
    public int[][] ajouterPionDansListe(int[] coordonneesPion,
    								    int[][] liste) {
    	
		if (liste.length == 0
			|| liste[liste.length - 1] != null) {
    		// Agrandir la taille de la liste.
    		liste = Arrays.copyOf(liste, liste.length + 1);
		}
		
    	// Ajouter au dernier élément le nouveau pion.
		liste[liste.length - 1] = coordonneesPion;
		
		return liste;
	}
    
    /**
     * Gestion du clic de l'utilisateur sur une case.
     * Si le clic est possible et respecte les règles d'Othello,
     * le plateau de jeu sera modifié et la liste des pions à retourner
     * dans la vue sera retournée.
     * Sinon {} est retourné.
     * 
     * @param x Coordonnée X (colonne) de la case où poser le pion.
     * @param y Coordonnée Y (ligne) de la case où poser le pion.
     * @return la liste des pions à retourner après le clic.
     */
    public int[][] clicCase(int x, int y) {
    	
        int[][] pionsARetournerActuels = {}; // {} = impossible placer pion
        
        if (placementPossible(x, y)) {
            poserPionDansPlateau(x, y);
            
            pionsARetournerActuels = getPionsARetourner();
            
            for (int indice = 0; indice < pionsARetournerActuels.length; indice++) {
				poserPionDansPlateau(pionsARetournerActuels[indice][0],
									 pionsARetournerActuels[indice][1]);
			}
            
            // Gestion du score
			if (tourJoueur1) {
				augmenterScoreJoueur1(pionsARetournerActuels.length + 1);
				reduireScoreJoueur2(pionsARetournerActuels.length);
			} else {
				augmenterScoreJoueur2(pionsARetournerActuels.length + 1);
				reduireScoreJoueur1(pionsARetournerActuels.length);
			}
			
			reinitialiserPionsARetourner();
			
			setTourJoueur1(tourJoueur1 ? false : true);
			
        } else if (tourJoueur1) {
        	ajouterErreurPlacementJoueur1();
        } else {
        	ajouterErreurPlacementJoueur2();                
        }
        return pionsARetournerActuels;
    }
	
	/**
	 * Vérification que la case dont les coordonnées sont en
	 * paramètres est vide.
	 *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
	 * @return si la case est vide (si aucun pion n'est placé).
	 */
	public boolean caseVide(int x, int y) {
		return plateau[y][x] == 0;
	}
	
	/**
	 * Modifie le pion de la case du plateau dont
	 * les coordonnées sont passées en paramètres.
	 * 
	 * @param x Coordonnée X (colonne) de la case à modifier.
     * @param y Coordonnée Y (ligne) de la case à modifier.
	 */
	public void poserPionDansPlateau(int x, int y) {
		plateau[y][x] = tourJoueur1 ? NOIR : BLANC;
	}
	
	/**
	 * Vérifie la possibilité de placer un pion sur une case de
	 * coordonnées x et y en fonction des règles d'Othello et
	 * de la couleur de pion du joueur à qui est le tour.
	 * <p>
	 * Met à jour la liste des pions à retourner si
	 * le placement est finalement possible.</p>
	 *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
	 * @return true si le pion peut être placé, false sinon.
	 */
	public boolean placementPossible(int x, int y) {
		if (!caseVide(x, y)) {
			return false;
		}
		caseVerifiee = tourJoueur1 ? NOIR : BLANC;
		
		/* 
		 * Afin d'exécuter toutes les fonctions de recherche
		 * des placements possibles et de mettre à jour la liste
		 * des pions à retourner, des variables stockent les résultats.
		 */
		boolean placementHorizontal = placementPossibleHorizontal(x, y);
		
		boolean placementVertical = placementPossibleVertical(x, y);
		
		boolean placementDiagonal = placementPossibleDiagonales(x, y);
		
		return placementHorizontal
		       || placementVertical
		       || placementDiagonal;
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
		
		boolean resultatPlacementGauche = false;
		boolean resultatPlacementDroit = false;
		
		if (x == COLONNE_GAUCHE
			|| x == COLONNE_GAUCHE + 1) {
			resultatPlacementDroit = placementPossibleDroite(x, y);
			
		} else if (x == COLONNE_DROITE
				   || x == COLONNE_DROITE - 1) {
			resultatPlacementGauche = placementPossibleGauche(x, y);
			
		} else {
			resultatPlacementDroit = placementPossibleDroite(x, y);
			resultatPlacementGauche = placementPossibleGauche(x, y);
		}
		return resultatPlacementDroit || resultatPlacementGauche;
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
		
		final int CASE_DROITE = plateau[y][x + 1];
		
		final int[] COORDONNEES_CASE_DROITE = {x + 1, y};
		
		int[][] listeTemporaire = {};

		boolean resultat = false;
		
		if (caseVerifiee == CASE_DROITE || CASE_DROITE == CASE_VIDE) {
		    return resultat;
		}
		
		listeTemporaire
		= ajouterPionDansListe(COORDONNEES_CASE_DROITE, listeTemporaire);
		
		int indiceX = x + 2;
		
		/* Parcours de la ligne à droite de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
		while (indiceX < plateau[y].length
		       && !caseVide(indiceX, y)
		       && !resultat) {
			int caseCourante = plateau[y][indiceX];
			
			int[] coordonneesCaseCourante = {indiceX, y};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
				resultat = true;
			} else {
				listeTemporaire
				= ajouterPionDansListe(coordonneesCaseCourante, listeTemporaire);
			}
			indiceX++;
		}
		
		if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
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
        
        final int CASE_GAUCHE = plateau[y][x - 1];
        final int [] COORDONNEES_CASE_GAUCHE = {x - 1, y};

        int[][] listeTemporaire = {};
        
        boolean resultat = false;
        
        if (caseVerifiee == CASE_GAUCHE || CASE_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        listeTemporaire
		= ajouterPionDansListe(COORDONNEES_CASE_GAUCHE, listeTemporaire);
        
        int indiceX = x - 2;
        
        /* Parcours de la ligne à gauche de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX >= 0
               && !caseVide(indiceX, y)
               && !resultat) {
            int caseCourante = plateau[y][indiceX];
			int [] coordonneesCaseCourante = {indiceX, y};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
        		= ajouterPionDansListe(coordonneesCaseCourante, listeTemporaire);
			}
            indiceX--;
        }
        
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
		}
        return resultat;
    }
	
	/**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses au-dessus ou en-dessous.
     * <p>
     * Lorsqu'un pion est à l'extrémité d'une colonne, il n'est possible
     * de le retourner que de façon horizontale ; c'est-à-dire qu'il est 
     * possible de le retourner seulement en étant dans la même ligne.</p>
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleVertical(int x, int y) {
        
    	boolean resultatPlacementHaut = false;
		boolean resultatPlacementBas = false;
		
        if (y == LIGNE_HAUTE
            || y == LIGNE_HAUTE + 1) {
        	resultatPlacementBas = placementPossibleBas(x, y);
            
        } else if (y == LIGNE_BASSE
                   || y == LIGNE_BASSE - 1) {
        	resultatPlacementHaut = placementPossibleHaut(x, y);
        	
        } else {
        	resultatPlacementHaut = placementPossibleHaut(x, y);
        	resultatPlacementBas = placementPossibleBas(x, y);
		}
        return resultatPlacementHaut || resultatPlacementBas;
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
        
        final int CASE_DESSOUS = plateau[y + 1][x];
        int[] COORDONNEES_CASE_DESSOUS = {x, y + 1};
        
        int[][] listeTemporaire = {};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_DESSOUS || CASE_DESSOUS == CASE_VIDE) {
            return resultat;
        }
        
        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_DESSOUS,
        					   listeTemporaire);
        
        int indiceY = y + 2;
        
        /* Parcours de la colonne en-dessous de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY < plateau.length
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];
            int[] coordonneesCaseCourante = {x, indiceY};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante, listeTemporaire);
			}
            indiceY++;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
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
        
        final int CASE_DESSUS = plateau[y - 1][x];
        int[] COORDONNEES_CASE_DESSUS = {x, y - 1};
        
        int[][] listeTemporaire = {};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_DESSUS || CASE_DESSUS == CASE_VIDE) {
            return resultat;
        }

        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_DESSUS,
        					   listeTemporaire);
        
        int indiceY = y - 2;
        
        /* Parcours de la colonne au-dessus de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY >= 0
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];
        	int[] coordonneesCaseCourante = {x, indiceY};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante, listeTemporaire);
			}
            indiceY--;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
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
        
        boolean basDroite = false;
        boolean hautDroite = false;
        boolean basGauche = false;
        boolean hautGauche = false;
        
        // X = 0 ou X = 1
        if (x <= COLONNE_GAUCHE + 1) {
			
			/* ---------------------------------
               Pas de vérification sur la gauche
               --------------------------------- */
			
			// Y = 0 ou Y = 1
			if (y <= LIGNE_HAUTE + 1) {
				basDroite = placementPossibleBasDroite(x, y);
			}
			// Y = 6 ou Y = 7
			else if (y >= LIGNE_BASSE - 1) {
				hautDroite = placementPossibleHautDroite(x, y);
			}
			// Y >= 2 et Y <= 5
			else {
				basDroite = placementPossibleBasDroite(x, y);
				hautDroite = placementPossibleHautDroite(x, y);
			}
		}
		
		// X = 6 ou X = 7
		else if (x >= COLONNE_DROITE - 1) {
			
			/* ---------------------------------
			   Pas de vérification sur la droite
			   --------------------------------- */
			
			// Y = 0 ou Y = 1
            if (y <= LIGNE_HAUTE + 1) {
				basGauche = placementPossibleBasGauche(x, y);
            }
            // Y = 6 ou Y = 7
            else if (y >= LIGNE_BASSE - 1) {
				hautGauche = placementPossibleHautGauche(x, y);
            }
            // Y >= 2 ou Y <= 5
            else {
				basGauche = placementPossibleBasGauche(x, y);
                hautGauche = placementPossibleHautGauche(x, y);
            }
		}
		
		// Y = 0 ou Y = 1
		else if (y <= LIGNE_HAUTE + 1) {
			
			basGauche = placementPossibleBasGauche(x, y);
			basDroite = placementPossibleBasDroite(x, y);			
		}
		
		// Y = 6 ou Y = 7
		else if (y >= LIGNE_BASSE - 1) {
			hautGauche = placementPossibleHautGauche(x, y);
			hautDroite = placementPossibleHautDroite(x, y);	
		} else {
		
			//    2 <= X <= 5
			// et 2 <= Y <= 5
        
        	hautGauche = placementPossibleHautGauche(x, y);
        	hautDroite = placementPossibleHautDroite(x, y);
        	basGauche = placementPossibleBasGauche(x, y);
			basDroite = placementPossibleBasDroite(x, y);
		}
		
		return hautGauche || hautDroite 
			   || basGauche || basDroite;
    }
    
    /*
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans sa diagonale partant du bas et allant
     * vers le haut et la gauche.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
    private boolean placementPossibleHautGauche(int x, int y) {
    	
        final int CASE_HAUT_GAUCHE = plateau[y - 1][x - 1];
        final int[] COORDONNEES_CASE_HAUT_GAUCHE = {x - 1, y - 1};

        int[][] listeTemporaire = {};
        
        boolean resultat = false;
        
        if (caseVerifiee == CASE_HAUT_GAUCHE || CASE_HAUT_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_HAUT_GAUCHE,
        					   listeTemporaire);
        
        int indiceX = x - 2;
        int indiceY = y - 2;
        
        /* Parcours de la diagonale au-dessus à gauche de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX >= 0
        	   && indiceY >= 0
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
        	int[] coordonneesCaseCourante = {indiceX, indiceY};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante,
                					   listeTemporaire);
			}
            indiceX--;
            indiceY--;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
		}
        return resultat;
	}
	
    /**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans sa diagonale partant du bas et allant
     * vers le haut et la droite.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
	private boolean placementPossibleHautDroite(int x, int y) {
		
        final int CASE_HAUT_DROITE = plateau[y - 1][x + 1];
        final int[] COORDONNEES_CASE_HAUT_DROITE = {x + 1, y - 1};
		
        int[][] listeTemporaire = {};
        
        boolean resultat = false;
        
        if (caseVerifiee == CASE_HAUT_DROITE || CASE_HAUT_DROITE == CASE_VIDE) {
            return resultat;
        }
        
        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_HAUT_DROITE,
        					   listeTemporaire);
        
        int indiceX = x + 2;
        int indiceY = y - 2;
        
        /* Parcours de la diagonale au-dessus à gauche de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY >= 0
        	   && indiceX < plateau[indiceY].length
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
       		int[] coordonneesCaseCourante = {indiceX, indiceY};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante,
                					   listeTemporaire);
			}
            indiceX++;
            indiceY--;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
		}
        return resultat;
	}
	
    /**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans sa diagonale partant du haut et allant
     * vers le bas et la gauche.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
	private boolean placementPossibleBasGauche(int x, int y) {
		
        final int CASE_BAS_GAUCHE = plateau[y + 1][x - 1];
        final int[] COORDONNEES_CASE_BAS_GAUCHE = {x - 1, y + 1};
		
        int[][] listeTemporaire = {};
        
		boolean resultat = false;
        
        if (caseVerifiee == CASE_BAS_GAUCHE || CASE_BAS_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_BAS_GAUCHE,
        					   listeTemporaire);
        
        int indiceX = x - 2;
        int indiceY = y + 2;
        
        /* Parcours de la diagonale en-dessous à gauche de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX >= 0
        	   && indiceY < plateau.length
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
            int[] coordonneesCaseCourante = {indiceX, indiceY};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante,
                					   listeTemporaire);
			}
            indiceX--;
            indiceY++;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
		}
        return resultat;
	}
	
    /**
     * Vérification de la possibilité de placer un pion sur une case
     * en fonction de la possibilité de retourner des pions
     * adverses dans sa diagonale partant du haut et allant
     * vers le bas et la droite.
     *
     * @param x Coordonnée X (colonne) de la case à vérifier.
     * @param y Coordonnée Y (ligne) de la case à vérifier.
     * @return true si le pion peut être placé, false sinon.
     */
	private boolean placementPossibleBasDroite(int x, int y) {

        final int CASE_BAS_DROITE = plateau[y + 1][x + 1];
        final int[] COORDONNEES_CASE_BAS_DROITE = {x + 1, y + 1};
        
        int[][] listeTemporaire = {};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_BAS_DROITE || CASE_BAS_DROITE == CASE_VIDE) {
            return resultat;
        }

        listeTemporaire
        = ajouterPionDansListe(COORDONNEES_CASE_BAS_DROITE,
        					   listeTemporaire);
        
        int indiceX = x + 2;
        int indiceY = y + 2;
        
        /* Parcours de la diagonale en-dessous à droite de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY < plateau.length
        	   && indiceX < plateau[indiceY].length
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
    	    int[] coordonneesCaseCourante = {indiceX, indiceY};
	
            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
            	listeTemporaire
                = ajouterPionDansListe(coordonneesCaseCourante,
                					   listeTemporaire);
			}
            indiceX++;
            indiceY++;
        }
        if (resultat) {
			ajouterPionsARetourner(listeTemporaire);
		}
        return resultat;
	}
	
	/**
	 * Algorithme de calcul du choix de l'ordinateur en fonction
	 * de sa difficulté.
	 * 
	 * @return Le coup choisi par l'ordinateur.
	 */
    public int[] choixOrdinateur() {
		
	   int[][] coupsPossibles = rechercheCasesClicPossible();
	   int[] nombrePionsRetournes = new int[coupsPossibles.length];
		
	   int[][] coupsFaciles = new int[1][];
	   int[][] coupsDifficiles = new int[1][];
		
	   for (int indiceCoup = 0; indiceCoup < coupsPossibles.length; indiceCoup++) {
			nombrePionsRetournes[indiceCoup] = 
	       calculResultatClicCase(coupsPossibles[indiceCoup][0], coupsPossibles[indiceCoup][1]);
	   }
		
	   int minimum = nombrePionsRetournes[0];
	   int maximum = nombrePionsRetournes[0];
		
	   for (int indice = 0;
			indice < nombrePionsRetournes.length;
			indice++) {
			
	       if (nombrePionsRetournes[indice] <= minimum) {
			   if (nombrePionsRetournes[indice] < minimum) {
				   minimum = nombrePionsRetournes[indice];
				   coupsFaciles = new int[1][];
			   }
			   
			   coupsFaciles
               = ajouterPionDansListe(coupsPossibles[indice], coupsFaciles);
		   }
		  
		   if (nombrePionsRetournes[indice] >= maximum) {
		      if (nombrePionsRetournes[indice] > maximum) {
			     maximum = nombrePionsRetournes[indice];
			     coupsDifficiles = new int[1][];
		      }
		      coupsDifficiles
		      = ajouterPionDansListe(coupsPossibles[indice], coupsDifficiles);
		   }
	   }
	   return ordinateurFacile
			  ? coupsFaciles[new Random()
	                         .nextInt(coupsFaciles.length)]
			  : coupsDifficiles[new Random()
	                            .nextInt(coupsDifficiles.length)];
	}
    
	/** 
	 * Accesseur des cases sur lesquelles l'utilisateur peut cliquer.
	 * 
	 * @return casesClicPossible La liste des cases où un clic est possible.
	 */
   	public int[][] rechercheCasesClicPossible() {
   		
	    int[][] casesClicPossible = {};
	    
	    // {{x, y}, {x2, y2}, {x3, y3}, ...}
	   
	  	for (int indiceLigne = 0; indiceLigne < plateau.length;
	  		 indiceLigne++) {
		  	for (int indiceColonne = 0; indiceColonne < plateau[indiceLigne].length;
				indiceColonne++) {
				if (placementPossible(indiceColonne, indiceLigne)) {
					int[] coordonneesCourantes = {indiceColonne, indiceLigne};
					
					casesClicPossible =
					ajouterPionDansListe(coordonneesCourantes,
										 casesClicPossible);
					
					/*
					 * Pour chaque case, la méthode placementPossible
					 * va mettre à jour la liste des pions qui
					 * devraient être retournés en cas de clic sur
					 * cette case. Étant donné que nous avons seulement
					 * besoin de connaître les cases sur lesquelles
					 * un clic est possible, nous réinitialisons
					 * la liste des pions à retourner pour la case vérifiée.
					 */
					reinitialiserPionsARetourner();
				}
			}
		}
		return casesClicPossible;
   	}
   	
   	/**
     * Simulation du clic de l'utilisateur sur une case. 
     * On calcule quel nombre de pions serait retourné
     * suite à un clic afin de permettre à l'ordinateur 
     * d'effectuer un coup différent en fonction de la difficulté.
     * 
     * @param x Coordonnée X (colonne) de la case où le pion serait posé.
     * @param y Coordonnée Y (ligne) de la case où le pion serait posé.
     * @return la liste des pions retournés après le clic hypothétique.
     */
    public int calculResultatClicCase(int x, int y) {
    	
        int nombrePionsRetournes = 0;
        
        if (placementPossible(x, y)) {
            nombrePionsRetournes = getPionsARetourner().length;
			reinitialiserPionsARetourner();
		}
		return nombrePionsRetournes;		
    }
   	
   	/**
   	 * Méthode (non utilisée par le jeu) permettant d'afficher
   	 * dans la console texte le plateau avec tous ses pions.
   	 */
   	public void afficherPlateauConsole() {
   		for (int indiceLigne = 0;
   			 indiceLigne < this.plateau.length;
   			 indiceLigne++) {
   			for (int indiceColonne = 0;
   				 indiceColonne < this.plateau.length;
   				 indiceColonne++) {
   				System.out.print(this.plateau[indiceLigne][indiceColonne]
   								 + "  ");
   			}
   			System.out.print("\n");
   		}
   	}
   	
   	/** @return true si 2 tours consécutifs sont passés. */
    public boolean deuxToursPasses() {
		return getToursPasses() >= 2;
	}
	 
    /** @return si le plateau est rempli. */
	public boolean plateauRempli() {
		boolean casesToutesRemplies = true;
		
		for (int i = 0;
			 i < plateau.length && casesToutesRemplies;
			 i++) {
			for (int j = 0;
				 j < plateau[i].length && casesToutesRemplies;
				 j++) {
				casesToutesRemplies = plateau[i][j] != CASE_VIDE;
			}
		}
		return casesToutesRemplies;
	}
	
	/** @return si le plateau est dominé par une couleur de pion. */
    public boolean plateauDomine() {

    	boolean aucunPionNoir = true;
        boolean aucunPionBlanc = true;
        
        for (int i = 0;
             i < plateau.length && aucunPionNoir;
             i++) {
            for (int j = 0;
                 j < plateau[i].length
                 && (aucunPionNoir);
                 j++) {
                aucunPionNoir = plateau[i][j] != NOIR;
            }
        }
        
        if (!aucunPionNoir) {
       	 for (int i = 0;
       	      i < plateau.length && aucunPionBlanc;
                 i++) {
               for (int j = 0;
                    j < plateau[i].length
                    && (aucunPionBlanc);
                    j++) {
                   aucunPionBlanc = plateau[i][j] != BLANC;
               }
           }
       }
       return aucunPionNoir || aucunPionBlanc;
    }
    
	 
	/** @return true si la partie est finie. */
	public boolean partieFinie() {
		return deuxToursPasses() || plateauRempli()
			   || plateauDomine() /*|| aucunPlacementPossible() */;
	}
}

