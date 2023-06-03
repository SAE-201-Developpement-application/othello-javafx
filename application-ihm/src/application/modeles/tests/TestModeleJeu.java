/*
 * TestModeleJeu.java                                                3 juin 2023
 * IUT de Rodez, pas de copyright ni de "copyleft".
 */
package application.modeles.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import application.modeles.ModeleJeu;

/**
 * Tests unitaires JUnit de la classe ModeleJeu.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
class TestModeleJeu {
	
	private ModeleJeu jeuTestInitial = new ModeleJeu();
	
	/**
	 * Réinitilisation du jeu de test initial (plateau initial au début
	 * de la partie).
	 */
	@BeforeEach
	public void reinitialiserJeuTest() {
		jeuTestInitial = new ModeleJeu();
	}
	
	/**
     * Méthode de test de {@link application.modeles.ModeleJeu#getPlateau()}.
     */
    @Test
    void testGetPlateau() {
        int[][] plateauTest = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        jeuTestInitial.setPlateau(plateauTest);
        
        assertArrayEquals(plateauTest, jeuTestInitial.getPlateau());
	}
	
	/*
	 * -----------------
	 * METHODES A TESTER
	 * -----------------
	 *
	 * -- GETTERS
	 * Faits
	 * -- SETTERS
	 * setPlateau(int[][] nouveauPlateau)
	 * setPartieOrdinateur(boolean partieOrdinateur)
	 * setOrdinateurFacile(boolean ordinateurFacile)
	 * setTourJoueur1(boolean tour)
	 * setScoreJoueur1(int score)
	 * setScoreJoueur2(int score)
	 * passerTour()
	 * reinitialiserTourPasses()
	 * ajouterErreurPlacementJoueur1()
	 * ajouterErreurPlacementJoueur2()
	 * clicCase(int x, int y)
	 */
	 
	/**
     * Méthode de test de {@link application.modeles.ModeleJeu#isPartieOrdinateur()}.
     */
    @Test
	void testIsPartieOrdinateur() {
		 
		jeuTestInitial.setPartieOrdinateur(true);
		
		assertTrue(jeuTestInitial.isPartieOrdinateur());
		
		jeuTestInitial.setPartieOrdinateur(false);
		
		assertFalse(jeuTestInitial.isPartieOrdinateur());
	}
	 
	/**
     * Méthode de test de {@link application.modeles.ModeleJeu#isOrdinateurFacile()}.
     */
    @Test
	void testIsOrdinateurFacile() {
			 
		jeuTestInitial.setOrdinateurFacile(true);
		
		assertTrue(jeuTestInitial.isOrdinateurFacile());
		
		jeuTestInitial.setOrdinateurFacile(false);
		
		assertFalse(jeuTestInitial.isOrdinateurFacile());
	}

    /**
     * Méthode de test de {@link application.modeles.ModeleJeu#isTourJoueur1()}.
     */
	@Test
	void testIsTourJoueur1() {
		 
		jeuTestInitial.setTourJoueur1(true);
		 
		assertTrue(jeuTestInitial.isTourJoueur1());
		 
		jeuTestInitial.setTourJoueur1(false);
		 
		assertFalse(jeuTestInitial.isTourJoueur1());
	}
	
    /**
     * Méthode de test de {@link application.modeles.ModeleJeu#getScoreJoueur1()}.
     */
	@Test
	void testGetScoreJoueur1() {
		
		jeuTestInitial.setScoreJoueur1(5);
		
		assertEquals(jeuTestInitial.getScoreJoueur1(), 5);
	}
	
    /**
     * Méthode de test de {@link application.modeles.ModeleJeu#getScoreJoueur2()}.
     */
    @Test
	void testGetScoreJoueur2() {
		jeuTestInitial.setScoreJoueur2(5);
		
		assertEquals(jeuTestInitial.getScoreJoueur2(), 5);
	}
	
    /**
     * Méthode de test de {@link application.modeles.ModeleJeu#getToursPasses()}.
     */
    @Test
	void testGetToursPasses() {
		jeuTestInitial.passerTour();
		assertEquals(jeuTestInitial.getToursPasses(), 1);
		
		jeuTestInitial.passerTour();
		assertEquals(jeuTestInitial.getToursPasses(), 2);
	}
	
    /**
     * Méthode de test de
     * {@link application.modeles.ModeleJeu#getNombreErreursPlacementJoueur1()}.
     */
    @Test
	void testGetNombreErreursPlacementJoueur1() {
		
		jeuTestInitial.ajouterErreurPlacementJoueur1();
		assertEquals(jeuTestInitial.getNombreErreursPlacementJoueur1(), 1);
		
		jeuTestInitial.ajouterErreurPlacementJoueur1();
		assertEquals(jeuTestInitial.getNombreErreursPlacementJoueur1(), 2);
	}
	
	/**
     * Méthode de test de
     * {@link application.modeles.ModeleJeu#getNombreErreursPlacementJoueur2()}.
     */
	void testGetNombreErreursPlacementJoueur2() {
		
		jeuTestInitial.ajouterErreurPlacementJoueur2();
		assertEquals(jeuTestInitial.getNombreErreursPlacementJoueur2(), 1);
		
		jeuTestInitial.ajouterErreurPlacementJoueur2();
		assertEquals(jeuTestInitial.getNombreErreursPlacementJoueur2(), 2);
	}
	
	/**
	 * Méthode de test de
	 * {@link application.modeles.ModeleJeu#placementPossible()}.
	 */
	@Test
	void testPlacementPossible() {
		
		ModeleJeu jeu = new ModeleJeu();
		
		jeu.setTourJoueur1(true);
		
		int[][] plateauTest1 = {
	      /* 0  1  2  3  4  5  6  7 */
    /* 0 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 1 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 2 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 3 */ {0, 0, 0, 2, 1, 0, 0, 0},
    /* 4 */ {0, 0, 0, 1, 2, 0, 0, 0},
    /* 5 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 6 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 7 */ {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
		
		int[][] coordonneesInvalidesJoueur1 = {
			// Lignes de gauche 
			{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
			{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
			// Lignes de droite
			{6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7},
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
			
			// Lignes du haut
			{2, 0}, {3, 0}, {4, 0}, {5, 0},
			{2, 1}, {3, 1}, {4, 1}, {5, 1}, 
			// Lignes du bas
			{2, 6}, {3, 6}, {4, 6}, {5, 6}, 
			{2, 7}, {3, 7}, {4, 7}, {5, 7},
			
			// Centre
			{2, 2}, {2, 4}, {2, 5},
			{3, 5},
			{4, 2},
			{5, 2}, {5, 3}, {5, 5}
		};
		
		int[][] coordonneesInvalidesJoueur2 = {
			// Lignes de gauche 
			{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
			{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
			// Lignes de droite
			{6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7},
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
			
			// Lignes du haut
			{2, 0}, {3, 0}, {4, 0}, {5, 0},
			{2, 1}, {3, 1}, {4, 1}, {5, 1}, 
			// Lignes du bas
			{2, 6}, {3, 6}, {4, 6}, {5, 6}, 
			{2, 7}, {3, 7}, {4, 7}, {5, 7},
			
			// Centre
			{2, 2}, {2, 3}, {2, 5},
			{3, 2},
			{4, 5},
			{5, 2}, {5, 4}, {5, 5}
		};
		
		int[][] coordonneesValidesJoueur1 = {
			{3, 2}, {4, 5}, {2, 3}, {5, 4}/*,
			{3, 5}, {4, 5}, {2, 4}, {2, 3},
			/*{5, 0}, {4, 0}, {3, 0}, {6, 1}*/
		};
		
		int[][] coordonneesValidesJoueur2 = {
			
			{3, 5}, {4, 2}, {5, 3}, {2, 4}
			// {2, 4} passe pas ce n'est pas normal
		};
		
		jeu.setPlateau(plateauTest1);
		
		/* Parcours de toutes les coordonnées invalides correspondantes au
		   joueur 1 afin de s'assurer que le résultat d'exécution soit faux. */
		for (int[] coordonneesCourantes : coordonneesInvalidesJoueur1) {
			assertFalse(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
		
		/* Parcours de toutes les coordonnées valides correspondantes au
           joueur 1 afin de s'assurer que le résultat d'exécution soit vrai. */
		for (int[] coordonneesCourantes : coordonneesValidesJoueur1) {
			assertTrue(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
		
		//jeu.setPlateau(plateauTest2);
		jeu.setTourJoueur1(false);
		
		
        /* Parcours de toutes les coordonnées valides correspondantes au
           joueur 2 afin de s'assurer que le résultat d'exécution soit vrai. */
		for (int[] coordonneesCourantes : coordonneesValidesJoueur2) {
			assertTrue(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
	}
	
	@Test
	void testPoserPion() {
		ModeleJeu jeu = new ModeleJeu();
		
		jeu.setTourJoueur1(true);
		
		int[][] plateauTest = {
	      /* 0  1  2  3  4  5  6  7 */
    /* 0 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 1 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 2 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 3 */ {0, 0, 0, 1, 1, 0, 0, 0},
    /* 4 */ {0, 0, 0, 1, 1, 0, 0, 0},
    /* 5 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 6 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 7 */ {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        int[][] plateauRempli = {
	      /* 0  1  2  3  4  5  6  7 */
    /* 0 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 1 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 2 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 3 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 4 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 5 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 6 */ {1, 1, 1, 1, 1, 1, 1, 1},
    /* 7 */ {1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        int[][] coordonneesCasesVides = {
			// Lignes de gauche 
			{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
			{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
			// Lignes de droite
			{6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7},
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
			
			// Lignes du haut
			{2, 0}, {3, 0}, {4, 0}, {5, 0},
			{2, 1}, {3, 1}, {4, 1}, {5, 1}, 
			// Lignes du bas
			{2, 6}, {3, 6}, {4, 6}, {5, 6}, 
			{2, 7}, {3, 7}, {4, 7}, {5, 7},
			
			// Centre
			{2, 2}, {2, 3}, {2, 4}, {2, 5},
			{3, 2}, {3, 5},
			{4, 2}, {4, 5},
			{5, 2}, {5, 3}, {5, 4}, {5, 5}
		};
		
		jeu.setPlateau(plateauTest);
		
		for (int[] coordonneesCourantes : coordonneesCasesVides) {
			jeu.poserPion(coordonneesCourantes[0], coordonneesCourantes[1]);
		}
		
		assertArrayEquals(plateauTest, plateauRempli);
		
	}
	
	@Test
	void testCaseVide() {
		ModeleJeu jeu = new ModeleJeu();
		
		jeu.setTourJoueur1(true);
		
		int[][] plateauTest1 = {
	      /* 0  1  2  3  4  5  6  7 */
    /* 0 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 1 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 2 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 3 */ {0, 0, 0, 2, 1, 0, 0, 0},
    /* 4 */ {0, 0, 0, 1, 2, 0, 0, 0},
    /* 5 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 6 */ {0, 0, 0, 0, 0, 0, 0, 0},
    /* 7 */ {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        int[][] coordonneesCasesVides = {
			// Lignes de gauche 
			{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
			{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
			// Lignes de droite
			{6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 7},
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
			
			// Lignes du haut
			{2, 0}, {3, 0}, {4, 0}, {5, 0},
			{2, 1}, {3, 1}, {4, 1}, {5, 1}, 
			// Lignes du bas
			{2, 6}, {3, 6}, {4, 6}, {5, 6}, 
			{2, 7}, {3, 7}, {4, 7}, {5, 7},
			
			// Centre
			{2, 2}, {2, 4}, {2, 5},
			{3, 2}, {3, 5},
			{4, 2}, {4, 5},
			{5, 2}, {5, 3}, {5, 4}, {5, 5}
		};
        
		jeu.setPlateau(plateauTest1);
		
		/* Parcours de toutes les coordonnées invalides correspondantes au
		   joueur 1 afin de s'assurer que le résultat d'exécution soit faux. */
		for (int[] coordonneesCourantes : coordonneesCasesVides) {
			assertTrue(jeu.caseVide(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
         
	}

}
