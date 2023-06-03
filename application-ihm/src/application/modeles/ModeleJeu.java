/*
 * ModeleJeu.java											    26 mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.modeles;

import java.util.Arrays;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

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
	 * Liste vide servant à reinitialiser la liste des pions à retourner dans la vue.
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
	private boolean partieCommence;

	/** Points du premier joueur. */
	private int scoreJoueur1;
	
	/** Points du second joueur (ou du bot). */
	private int scoreJoueur2;
	
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
	
	/**
	 * Liste des coordonnées des pions à retourner en haut dans la vue.
	 */
	private int[][] pionsARetournerHaut = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner en bas dans la vue.
	 */
	private int[][] pionsARetournerBas = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner à gauche dans la vue.
	 */
	private int[][] pionsARetournerGauche = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner à droite dans la vue.
	 */
	private int[][] pionsARetournerDroite = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner en haut à gauche dans la vue.
	 */
	private int[][] pionsARetournerHautGauche = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner en haut à droite dans la vue.
	 */
	private int[][] pionsARetournerHautDroite = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner en bas à droite dans la vue.
	 */
	private int[][] pionsARetournerBasDroite = {}; // Valeur par défaut
	
	/**
	 * Liste des coordonnées des pions à retourner en bas à gauche dans la vue.
	 */
	private int[][] pionsARetournerBasGauche = {}; // Valeur par défaut
	
	
	
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
	
    /** @return si la partieCommence */
    public boolean isPartieCommence() {
    	return partieCommence;
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
	
	/** @return la liste des pions à retourner après le placement d'un nouveau pion */
    public int[][] getPionsARetourner() {
        return this.pionsARetourner;
    }
	
	/**
	 * @return les pionsARetournerHaut
	 */
	public int[][] getPionsARetournerHaut() {
		return pionsARetournerHaut;
	}

	/**
	 * @return les pionsARetournerBas
	 */
	public int[][] getPionsARetournerBas() {
		return pionsARetournerBas;
	}

	/**
	 * @return les pionsARetournerGauche
	 */
	public int[][] getPionsARetournerGauche() {
		return pionsARetournerGauche;
	}

	/**
	 * @return les pionsARetournerDroite
	 */
	public int[][] getPionsARetournerDroite() {
		return pionsARetournerDroite;
	}

	/**
	 * @return les pionsARetournerHautGauche
	 */
	public int[][] getPionsARetournerHautGauche() {
		return pionsARetournerHautGauche;
	}

	/**
	 * @return les pionsARetournerHautDroite
	 */
	public int[][] getPionsARetournerHautDroite() {
		return pionsARetournerHautDroite;
	}

	/**
	 * @return les pionsARetournerBasDroite
	 */
	public int[][] getPionsARetournerBasDroite() {
		return pionsARetournerBasDroite;
	}

	/**
	 * @return les pionsARetournerBasGauche
	 */
	public int[][] getPionsARetournerBasGauche() {
		return pionsARetournerBasGauche;
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
    
    /** @param partieCommence the partieCommence to set */
    public void setPartieCommence(boolean partieCommence) {
    	this.partieCommence = partieCommence;
    }
    
    /** @param score Nouveau score du joueur 1*/
    public void setScoreJoueur1(int score) {
        this.scoreJoueur1 = score;
    }
    
    /** @param score Nouveau score du joueur 2 */
    public void setScoreJoueur2(int score) {
        this.scoreJoueur2 = score;
    }

	/**
	 * Incrémentation du nombre de tours consécutifs passés.
	 */
    public void passerTour() {
        this.toursPasses++;     
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
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetourner() {
        this.pionsARetourner = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerDroite() {
        this.pionsARetournerDroite = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerGauche() {
        this.pionsARetournerGauche = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerHaut() {
        this.pionsARetournerHaut = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerBas() {
        this.pionsARetournerBas = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerHautGauche() {
        this.pionsARetournerHautGauche = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerHautDroite() {
        this.pionsARetournerHautDroite = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerBasGauche() {
        this.pionsARetournerBasGauche = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void reinitialiserPionsARetournerBasDroite() {
        this.pionsARetournerBasDroite = PIONS_A_RETOURNER_PAR_DEFAUT;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void concatenerListesPionsARetourner() {
		
		int[][][] listeListesPionsARetourner = {pionsARetournerDroite,
												 	pionsARetournerGauche,
												  	pionsARetournerBas, 
												  	pionsARetournerHaut, 
												  	pionsARetournerHautGauche, 
												  	pionsARetournerHautDroite, 
												  	pionsARetournerBasGauche, 
												  	pionsARetournerBasDroite};
		
		for (int indiceListeListe = 0;
			 indiceListeListe < listeListesPionsARetourner.length; 
			 indiceListeListe++) {
			for (int indiceSousListe = 0; 
				 indiceSousListe < listeListesPionsARetourner[indiceListeListe].length;
				 indiceSousListe++) { 
				
				int[][] listePions = this.getPionsARetourner();
				
				this.pionsARetourner = Arrays.copyOf(listePions, listePions.length + 1);
				
				this.pionsARetourner[listePions.length] 
				= listeListesPionsARetourner[indiceListeListe][indiceSousListe];
			}
		}
        reinitialiserSousListes();
    }
    
    private void reinitialiserSousListes() {
		
		reinitialiserPionsARetournerDroite();
		reinitialiserPionsARetournerGauche();
		reinitialiserPionsARetournerBas();
		reinitialiserPionsARetournerHaut();
		reinitialiserPionsARetournerHautGauche();
		reinitialiserPionsARetournerHautDroite();
		reinitialiserPionsARetournerBasGauche();
		reinitialiserPionsARetournerBasDroite();
        
	}
    
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerDroite(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerDroite();
		
        this.pionsARetournerDroite = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerDroite[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerGauche(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerGauche();
		
        this.pionsARetournerGauche = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerGauche[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerHaut(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerHaut();
		
        this.pionsARetournerHaut = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerHaut[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerBas(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerBas();
		
        this.pionsARetournerBas = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerBas[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerHautGauche(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerHautGauche();
		
        this.pionsARetournerHautGauche = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerHautGauche[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerHautDroite(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerHautDroite();
		
        this.pionsARetournerHautDroite = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerHautDroite[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerBasGauche(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerBasGauche();
		
        this.pionsARetournerBasGauche = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerBasGauche[listePions.length] = coordonneesPion;
    }
    
    /** 
     * Réinitialise/vide la liste des pions à retourner une fois
     * ceux-ci retournés par la vue.
     */
    public void ajouterPionsARetournerBasDroite(int[] coordonneesPion) {
		int[][] listePions = this.getPionsARetournerBasDroite();
		
        this.pionsARetournerBasDroite = Arrays.copyOf(listePions, listePions.length + 1);
        
        this.pionsARetournerBasDroite[listePions.length] = coordonneesPion;
    }
    
    /**
     * Gestion du clic de l'utilisateur sur une case.
     * Si c'est possible, un pion de la couleur du joueur sera placé.
     * 
     * @param x Coordonnée X (colonne) de la case où poser le pion.
     * @param y Coordonnée Y (ligne) de la case où poser le pion.
     */
    public int[][] clicCase(int x, int y) {
        int[][] casesARetourner = {{-1, -1}}; // -1 = impossible placer pion
        
        if (placementPossible(x, y)) {
            poserPion(x, y);
            concatenerListesPionsARetourner();
            casesARetourner = getPionsARetourner();
            for (int indice = 0; indice < casesARetourner.length; indice++) {
				poserPion(casesARetourner[indice][0],casesARetourner[indice][1]);                       // TODO c'est la pour le gif qui retourne les pions
			}
			reinitialiserPionsARetourner();
            if (tourJoueur1) {
				tourJoueur1 = false;
			} else {
				tourJoueur1 = true;
			}
        } else if (tourJoueur1) {
            nombreErreursPlacementJoueur1++;
        } else {
            nombreErreursPlacementJoueur2++;                
        }
        return casesARetourner;
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
	 * Modifie le pion de la case dont les coordonnées sont passées
	 * en paramètres.
	 * 
	 * @param x Coordonnée X (colonne) de la case à modifier.
     * @param y Coordonnée Y (ligne) de la case à modifier.
	 */
	public void poserPion(int x, int y) {
		plateau[y][x] = tourJoueur1 ? NOIR : BLANC;
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
		
		boolean gauche = false;
		boolean droite = false;
		
		if (x == COLONNE_GAUCHE
			|| x == COLONNE_GAUCHE + 1) {
			droite = placementPossibleDroite(x, y);
			
		} else if (x == COLONNE_DROITE
				   || x == COLONNE_DROITE - 1) {
			gauche = placementPossibleGauche(x, y);
		} else {
			droite = placementPossibleDroite(x, y);
			gauche = placementPossibleGauche(x, y);
		}
		return droite || gauche;
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
		int [] coordonneesCaseDroite = {x + 1, y};

		boolean resultat = false;
		
		if (caseVerifiee == CASE_DROITE || CASE_DROITE == CASE_VIDE) {
		    return resultat;
		}
		
		ajouterPionsARetournerDroite(coordonneesCaseDroite);
		int indiceX = x + 2;
		
		/* Parcours de la ligne à droite de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
		while (indiceX < plateau[y].length
		       && !caseVide(indiceX, y)
		       && !resultat) {
			int caseCourante = plateau[y][indiceX];
			int [] coordonneesCaseCourante = {y, indiceX};
			

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
				resultat = true;
			} else {
				ajouterPionsARetournerDroite(coordonneesCaseCourante);
			}
			indiceX++;
		}
		if (!resultat) {
			reinitialiserPionsARetournerDroite();
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
        int [] coordonneesCaseGauche = {x - 1, y};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_GAUCHE || CASE_GAUCHE == CASE_VIDE) {
            return resultat;
        }
    	ajouterPionsARetournerGauche(coordonneesCaseGauche);
        int indiceX = x - 2;
        
        /* Parcours de la ligne à gauche de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX >= 0
               && !caseVide(indiceX, y)
               && !resultat) {
            int caseCourante = plateau[y][indiceX];
			int [] coordonneesCaseCourante = {y, indiceX};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerGauche(coordonneesCaseCourante);
			}
            indiceX--;
        }
        if (!resultat) {
			reinitialiserPionsARetournerGauche();
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
        
        boolean haut = false;
		boolean bas = false;
        if (y == LIGNE_HAUTE
            || y == LIGNE_HAUTE + 1) {
            bas = placementPossibleBas(x, y);
            
        } else if (y == LIGNE_BASSE
                   || y == LIGNE_BASSE - 1) {
            haut = placementPossibleHaut(x, y);
        } else {
			haut = placementPossibleHaut(x, y);
			bas = placementPossibleBas(x, y);
		}
        return haut || bas;
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
        int [] coordonneesCaseBas = {x, y + 1};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_DESSOUS || CASE_DESSOUS == CASE_VIDE) {
            return resultat;
        }
        ajouterPionsARetournerBas(coordonneesCaseBas);
        int indiceY = y + 2;
        
        /* Parcours de la colonne en-dessous de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY < plateau.length
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];
            int[] coordonneesCaseCourante = {indiceY, x};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerBas(coordonneesCaseCourante);
			}
            indiceY++;
        }
        if (!resultat) {
			reinitialiserPionsARetournerBas();
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
        int[] coordonneesCaseDessus = {x, y - 1};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_DESSUS || CASE_DESSUS == CASE_VIDE) {
            return resultat;
        }
        ajouterPionsARetournerHaut(coordonneesCaseDessus);
        int indiceY = y - 2;
        
        /* Parcours de la colonne au-dessus de la case initiale
           afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceY >= 0
               && !caseVide(x, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][x];
        	int[] coordonneesCaseCourante = {y - 1, x};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerHaut(coordonneesCaseCourante);
			}
            indiceY--;
        }
        if (!resultat) {
			reinitialiserPionsARetournerHaut();
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
        int[] coordonneesCaseHautGauche = {x - 1, y - 1};

        
        boolean resultat = false;
        
        if (caseVerifiee == CASE_HAUT_GAUCHE || CASE_HAUT_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        ajouterPionsARetournerHautGauche(coordonneesCaseHautGauche);
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
        	int[] coordonneesCaseCourante = {indiceY, indiceX};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerHautGauche(coordonneesCaseCourante);
			}
            indiceX--;
            indiceY--;
        }
        if (!resultat) {
			reinitialiserPionsARetournerHautGauche();
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
        int[] coordonneesHautDroite = {x + 1, y - 1};
		
        boolean resultat = false;
        
        if (caseVerifiee == CASE_HAUT_DROITE || CASE_HAUT_DROITE == CASE_VIDE) {
            return resultat;
        }
        
        ajouterPionsARetournerHautDroite(coordonneesHautDroite);
        int indiceX = x + 2;
        int indiceY = y - 2;
        
        /* Parcours de la diagonale au-dessus à gauche de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX < plateau[indiceY].length
        	   && indiceY >= 0
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
       		int[] coordonneesCaseCourante = {indiceY, indiceX};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerHautDroite(coordonneesCaseCourante);
			}
            indiceX++;
            indiceY--;
        }
        if (!resultat) {
			reinitialiserPionsARetournerHautDroite();
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
        int[] coordonneesCaseBasGauche = {x - 1, y + 1};
		
		boolean resultat = false;
        
        if (caseVerifiee == CASE_BAS_GAUCHE || CASE_BAS_GAUCHE == CASE_VIDE) {
            return resultat;
        }
        
        ajouterPionsARetournerBasGauche(coordonneesCaseBasGauche);
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
            int[] coordonneesCaseCourante = {indiceY, indiceX};

            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerBasGauche(coordonneesCaseCourante);
			}
            indiceX--;
            indiceY++;
        }
        if (!resultat) {
			reinitialiserPionsARetournerBasGauche();
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
        int[] coordonneesCaseBasDroite = {x + 1, y + 1};

        boolean resultat = false;
        
        if (caseVerifiee == CASE_BAS_DROITE || CASE_BAS_DROITE == CASE_VIDE) {
            return resultat;
        }
        
        ajouterPionsARetournerBasDroite(coordonneesCaseBasDroite);
        int indiceX = x + 2;
        int indiceY = y + 2;
        
        /* Parcours de la diagonale en-dessous à droite de la case
           initiale afin de déterminer la présence d'un pion allié
           permettant un placement */
        while (indiceX < plateau[indiceY].length
        	   && indiceY < plateau.length
               && !caseVide(indiceX, indiceY)
               && !resultat) {
            int caseCourante = plateau[indiceY][indiceX];
    	    int[] coordonneesCaseCourante = {indiceY, indiceX};
	
            // Pion de la même couleur que la case vérifiée
            if (caseCourante == caseVerifiee) {
                resultat = true;
            } else {
				ajouterPionsARetournerBasDroite(coordonneesCaseCourante);
			}
            indiceX++;
            indiceY++;
        }
        if (!resultat) {
			reinitialiserPionsARetournerBasDroite();
		}
        return resultat;
	}
    	
}

