package application.modeles.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import application.modeles.ModeleJeu;

class TestModeleJeu {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void testPlacementPossibleHorizontal() {
		
		ModeleJeu jeu = new ModeleJeu();
		
		int[][] plateauTest = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
		
		jeu.setPlateau(plateauTest);
		
		assertFalse(jeu.placementPossible(0, 6));
		
	}

}
