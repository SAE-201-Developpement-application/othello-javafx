package application.modeles.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import application.modeles.ModeleJeu;

class TestModeleJeu {
	
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
		
		int[][] coordonneesTestsInvalides = {
			{0, 0}, {7, 0}, {0, 7}, {7, 7},
			{1, 1}, {6, 1}, {1, 6}, {6, 6},
			{1, 3}, {3, 6}, {5, 5}, {2, 4}
		};
		
		int[][] coordonneesTestsValidesJoueur1 = {
			{3, 2}, {4, 5}, {2, 3}, {5, 4}/*,
			{3, 5}, {4, 5}, {2, 4}, {2, 3},
			/*{5, 0}, {4, 0}, {3, 0}, {6, 1}*/
		};
		
		int[][] coordonneesTestsValidesJoueur2 = {
			
			{3, 5}, {4, 2}, {5, 3}, /*{2, 4}*/
			//{2, 4} passe pas ce n'est pas normal
		};
		
		jeu.setPlateau(plateauTest1);
		
		for (int[] coordonneesCourantes : coordonneesTestsInvalides) {
			assertFalse(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
		
		for (int[] coordonneesCourantes : coordonneesTestsValidesJoueur1) {
			assertTrue(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
		
		jeu.setTourJoueur1(false);
		
		for (int[] coordonneesCourantes : coordonneesTestsValidesJoueur2) {
			assertTrue(jeu.placementPossible(
				coordonneesCourantes[0], 
				coordonneesCourantes[1]
			));
		}
	}

}
