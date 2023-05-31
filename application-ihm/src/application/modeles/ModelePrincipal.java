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
	// TODO coder
	
	/* Type de partie : 1 contre un ordinateur, 2 contre un autre joueur et 0 par défaut*/
	private int typePartie = 0;
	
	/* Pseudo du premier joueur*/
	private String nomJoueur1;
	
	/* Pseudo du deuxième joueur, si il existe*/
	private String nomJoueur2;
	
	private boolean partieFacile = true;
	
	private boolean partieCommencee = false;
	
	private boolean sauvegardeVide = false;
	
	/* Paramètres désactivés par défaut */
	private boolean musique = false;
	private boolean son = false;
	private boolean voirPionsEnlevables = false;
	
	/* Paramètre activé par défaut */
	private boolean voirPositionsPossibles = true;
	
	public void validerParametres(boolean statutMusique, boolean statutSon,
								  boolean statutVoirPositionsPossibles,
								  boolean statutVoirPionsEnlevables) {
		setMusique(statutMusique);
		setSon(statutSon);
		setVoirPositionsPossibles(statutVoirPositionsPossibles);
		setVoirPionsEnlevables(statutVoirPionsEnlevables);
	}
	
	private void setMusique(boolean statut) {
		musique = statut;
	}
	
	private void setSon(boolean statut) {
		son = statut;
	}
	
	private void setVoirPositionsPossibles(boolean statut) {
		voirPositionsPossibles = statut;
	}
	
	private void setVoirPionsEnlevables(boolean statut) {
		voirPionsEnlevables = statut;
	}
	
	/**
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
		setVoirPositionsPossibles(statutVoirPionsEnlevables);
		setVoirPionsEnlevables(statutVoirPionsEnlevables);
	}
	
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
		return musique != this.musique || son != this.son
			|| voirPositionsPossibles != this.voirPositionsPossibles
			|| voirPionsEnlevables != this.voirPionsEnlevables;
	}
	
}
