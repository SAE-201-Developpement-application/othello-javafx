/*
 * EnsembleDesVues.java												   mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.vues;

/**
 * Classe outil qui établit la correspondance entre un code de vue (sous la
 * forme d'un entier) et le nom du fichier fxml contenant la vue associée
 * à ce code.
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class EnsembleDesVues {
	
	/** Code de la vue principale */
	public static final int VUE_PRINCIPALE = 0;
		
	/** Code de la vue des paramètres */
	public static final int VUE_PARAMETRES = 1;
		
	/** Code de la vue du niveau de difficultée de l'ordinateur */
	public static final int VUE_NIVEAU = 2;
	
	/** Code de la vue des pseudos joueur contre joueur */
	public static final int VUE_CHOIX_PSEUDOS_CONTRE_JOUEUR = 3;
	
		
	/** Tableau contenant les noms des fichiers fxml des différentes vues
	 * de l'application. Il y a une correspondance entre l'indice de la case
	 * du tableau et le code de la vue défini en tant que constante
	 */
	private static final String[] NOM_DES_VUES =
		{"VueMenuPrincipal.fxml", "VueNiveauOrdinateur.fxml", "VueParametres.fxml", "VueChoixPseudosContreJoueur.fxml"};
		
		
		
	/**
	 * Renvoie le nom du fichier fxml contenant la vue dont le code est donné
	 * en paramètre
	 * @param codeVue code de la vue dont le fichier fxml doit être renvoyé
	 * @return une chaîne contenant le nom du fichier fxml
	 * @throw IllegalArgumentException levée si le code argument n'est pas valide
	 */
	public static String getNomVue(int codeVue) {
		if (codeVue < 0 || codeVue >= NOM_DES_VUES.length) {
			throw new IllegalArgumentException("Code vue " + codeVue + " invalide");
		}
		return NOM_DES_VUES[codeVue];
	}
}