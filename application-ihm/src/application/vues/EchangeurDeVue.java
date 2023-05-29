/*
 * EchangeurDeVues.java										           mai 2023
 * IUT de Rodez, pas de copyright ni de "copyleft"
 */
package application.vues;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Classe outil permettant de gérer le changement de la vue affichée
 * par la scene de l'application.
 *
 * Afin d'optimiser, un cache est mis en place : l'objectif est que le code
 * fxml soit chargé une seule fois, donc lors du 1er chargement de cette vue.
 * Pour ce faire, une HashMap (table de hachage) fait la correspondance entre
 * le code de la vue (une constante de type entier définie dans la classe
 * EnsembleDesVues) et l'élément racine de la vue (de type Parent qui est la classe
 * Parente de toutes les classes conteneurs comme HBox, VBox, BorderPane ...).
 * 
 * @author Tom DOUAUD
 * @author Loïc FAUGIERES
 * @author Simon GUIRAUD
 */
public class EchangeurDeVue {

    /**
     * HashMap ou table de hachage qui contient des paires formées de :
     * - un code de vue sous la forme d'une constante de type entier définie
     * dans la classe EnsembleDesVues
     * Ce code est la clé de la paire
     * - un conteneur correspondant à la vue définie dans le fichier fxml
     * celui qui a pour code la valeur de la clé
     * Cette table est renseignée au 1er chargement d'une vue.
     * Au 2ème chargement, la vue recherchée n'est pas obtenue à partir du fichier
     * fxml, mais à partir de l'élément de type Parent associée à la clé (la clé
     * étant le code de la vue)
     */
    private static Map<Integer, Parent> cache;

    // création de la table cache
    static {
        cache = new HashMap<>();
    }

    /** Scene courante, ou scène qui est associée à la fenêtre principale */
    private static Scene sceneCourante;
    
    
    /**
     * Affecte à la sceneCourante la scène créée dans la méthode start, donc
     * celle associée à la fenêtre principale
     * @param nouvelleScene Scene à affecter
     */
    public static void setSceneCourante(Scene nouvelleScene) {
        sceneCourante = nouvelleScene;
    }


    /**
     * Modifie la vue associée à la scène courante, pour qu'elle devienne celle dont
     * le code est donné en argument
     * La scène courante doit avoir été initialisée
     * @param codeVue code de la vue à placer sur la scène courante
     */
    public static void echangerAvec(int codeVue) {
    	if (sceneCourante == null) {
    		
    		// pas de scène courante : impossible de modifier sa vue
    		throw new IllegalStateException("Echange de vue impossible. Pas de scène courante.");
    	}

    	try {
    		Parent racine; // recevra le conteneur racine de la vue à afficher
    		
    		if (cache.containsKey(codeVue)) {

    			/*
    			 * la vue associée au codeVue est présente dans la table cache,
    			 * ce qui signifie qu'elle a déjà été chargée.
    			 * Pour optimiser, on ne la recharge pas. Au contraire, on la récupère
    			 * dans la table cache, à partir de sa clé (codeVue)
    			 */
    			racine = cache.get(codeVue);
    		} else {

    			/*
    			 * La vue associée au codeVue n'est pas présente dans la table cache.
    			 * Elle n'a donc pas encore été chargée. Il faut donc la charger et
    			 * ensuite on la stocke dans la table (pour éviter de la recharger
    			 * à nouveau si l'utilisateur souhaite revenir sur cette vue)
    			 */
    			racine = FXMLLoader.load(
    					 EchangeurDeVue.class.getResource(EnsembleDesVues.getNomVue(codeVue)));
    			
    			// ajout de la vue à la table cache
    			cache.put(codeVue, racine);
    		}
    		sceneCourante.setRoot(racine);
    	} catch(IOException erreur) {
    		
    		// problème lors de l'accès au fichier décrivant la vue
    		System.out.println("Echec du chargement de la vue de code " + codeVue);
    	}
    }
}