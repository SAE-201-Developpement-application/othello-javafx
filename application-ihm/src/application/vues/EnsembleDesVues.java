/*
 * Gére la correspondance entre le code d'une vue (un entier) et le nom
 * du fichier fxml décrivant cette vue 05/23
 */
package application.vues;

/**
 * Classe outil qui établit la correspondance entre un code de vue (sous la
 * forme d'un entier) et le nom du fichier fxml contenant la vue associée
 * à ce code.
 * @author C. Servières
 * @version 1.0
 *
 */
public class EnsembleDesVues {
	
	/** Code de la vue principale */
	public static final int VUE_PRINCIPALE = 0;
		
	/** Code de la vue des paramètres */
	public static final int VUE_PARAMETRES = 1;
		
	/** Code de la vue du niveau */
	public static final int VUE_NIVEAU = 2;
		
	/** Tableau contenant les noms des fichiers fxml des différentes vues
	 * de l'application. Il y a une correspondance entre l'indice de la case
	 * du tableau et le code de la vue défini en tant que constante
	 */
	private static final String[] NOM_DES_VUES =
		{"VueMenuPrincipal.fxml", "VueNiveauOrdinateur.fxml", "VueParametres.fxml"};
		
		
		
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