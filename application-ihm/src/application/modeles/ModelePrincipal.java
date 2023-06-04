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
	
	/* Pseudo du premier joueur */
	private String pseudoJoueur1;
	
	/* Pseudo du deuxième joueur, si il existe*/
	private String pseudoJoueur2;
	
	/** Paramètre indiquant si une sauvegarde existe */
	private boolean sauvegardeExiste = false;
	
	/* Paramètres désactivés par défaut */
	private boolean musique = false;
	private boolean son = false;
	private boolean voirPionsEnlevables = false;
	
	/* Paramètre activé par défaut */
	private boolean voirPositionsPossibles = true;
	
	/**
	 * Vérification de la différence entre les paramètres passés en
	 * paramètres et les attributs de this correspondants.
	 * 
	 * @param musique Booléen à comparer avec l'attribut musique de this.
	 * @param son Booléen à comparer avec l'attribut son de this.
	 * @param voirPositionsPossibles Booléen à comparer avec l'attribut
	 * 								 voirPositionsPossibles de this.
	 * @param voirPionsEnlevables Booléen à comparer avec l'attribut
	 * 							  voirPionsEnlevables de this.
	 * @return true si un des attributs de this est différent de l'attribut
	 * 		   passé en paramètre correspondant.
	 */
	public boolean parametresModifies(boolean musique, boolean son,
									  boolean voirPositionsPossibles,
									  boolean voirPionsEnlevables) {
		
		return musique != this.musique
			   || son != this.son
			   || voirPositionsPossibles != this.voirPositionsPossibles
			   || voirPionsEnlevables != this.voirPionsEnlevables;
	}
	
	/** @return Nom du premier joueur. */
	public String getPseudoJoueur1() {
		return pseudoJoueur1;
	}
	
	/** @return Nom du second joueur. */
	public String getPseudoJoueur2() {
		return pseudoJoueur2;
	}
	
	/** @return Si il y a une sauvegarde d'une partie de jeu. */
	public boolean getSauvegardeExiste() {
		return sauvegardeExiste;
	}
	
	/** @return Paramètre musique de this. */
	public boolean getMusique() {
		return musique;
	}
	
	/** @return Paramètre son de this. */
	public boolean getSon() {
		return son;
	}
	
	/** @return Paramètre voirPositionsPossibles de this. */
	public boolean getVoirPositionsPossibles() {
		return voirPositionsPossibles;
	}
	
	/** @return Paramètre voirPionsEnlevables de this. */
	public boolean getVoirPionsEnlevables() {
		return voirPionsEnlevables;
	}
	
	/** Modifie le nom du joueur 1 */
	public void setNomJoueur1(String nom) {
		pseudoJoueur1 = nom;
	}
	
	/** Modifie le nom du joueur 2 */
	public void setNomJoueur2(String nom) {
		pseudoJoueur2 = nom;
	}
	
	/** Modifie le statut du paramètre musique */
	private void setMusique(boolean statut) {
		musique = statut;
	}
	
	/** Modifie le statut du paramètre son */
	private void setSon(boolean statut) {
		son = statut;
	}
	
	/** Modifie le statut du paramètre voirPositionsPossibles */
	private void setVoirPositionsPossibles(boolean statut) {
		voirPositionsPossibles = statut;
	}
	
	/** Modifie le statut du paramètre voirPionsEnlevables */
	private void setVoirPionsEnlevables(boolean statut) {
		voirPionsEnlevables = statut;
	}
	
	/**
	 * Modifie tous les paramètres de la partie.
	 * 
	 * @param statutMusique Le nouveau statut d'activation de la musique.
	 * @param statutSon Le nouveau statut d'activation du son.
	 * @param statutVoirPositionsPossibles Le nouveau statut du paramètre de
	 * 									   vision des positions possibles.
	 * @param statutVoirPionsEnlevables Le nouveau statut du paramètres de
	 * 									vision des pions enlevables.
	 */
	public void setParametres(boolean statutMusique, boolean statutSon,
							  boolean statutVoirPositionsPossibles,
							  boolean statutVoirPionsEnlevables) {
		setMusique(statutMusique);
		setSon(statutSon);
		setVoirPositionsPossibles(statutVoirPositionsPossibles);
		setVoirPionsEnlevables(statutVoirPionsEnlevables);
		
		System.out.printf("\nModelePrincipal > Nouveau statut des paramètres :\n"
						  + "- musique = %b\n- son = %b\n"
						  + "- voirPositionsPossibles = %b\n"
						  + "- voirPionsEnlevables = %b\n",
						  musique, son, voirPositionsPossibles,
						  voirPionsEnlevables);
	}
}
