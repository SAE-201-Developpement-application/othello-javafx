<p align="center">
  <img src="./SAE201/application-ihm/src/application/vues/images/Othello.png" alt="logOthello" width="100" />
</p>

# application-ihm - jeu d'Othello

Bienvenue sur cette version Javafx du célèbre jeu d'Othello

<p align="center">
  <img src="./SAE201/application-ihm/src/application/vues/images/Animation.gif" alt="logOthello" width="500" />
</p>

Jeu totalement fonctionnel avec différentes options, modes de jeu et difficultées de bot

## Installation du JDK

Ce projet nécessite JDK-20 pour être compilé et exécuté correctement. Assurez-vous d'avoir JDK-20 installé sur votre système. Si vous ne l'avez pas encore, vous pouvez le télécharger à partir du site officiel d'Oracle et l'installer avant de poursuivre la configuration du projet.
N'oubliez pas de configurer votre Build Path sous Eclipse afin d'y ajouter jdk-20 si vous souhaitez modifier le code.

## Configuration de la bibliothèque JavaFX

Ce projet utilise la bibliothèque JavaFX pour la création d'interfaces graphiques. Pour configurer la bibliothèque JavaFX dans votre environnement de développement, suivez les étapes ci-dessous :

1. Téléchargez le SDK 20 JavaFX à partir de ce site : https://gluonhq.com/products/javafx/openjfx-20-release-notes/
2. Placez le dossier "javafx-sdk-20" téléchargé à la racine du projet.
3. Si vous souhaitez modifier le code, n'oubliez pas d'ajouter SDK au Build Path du projet (sur Eclipse) dans classpath.

## Configuration du dictionnaire français sous Eclipse

Si vous ne souhaitez pas modifier le code, passez cette étape.

Ce projet utilise un dictionnaire personnalisé français pour la vérification orthographique dans votre IDE. Pour configurer le dictionnaire français, suivez ces étapes :

1. Ouvrez votre IDE et accédez aux préférences ou aux paramètres spécifiques à l'éditeur de texte.
2. Recherchez les options de vérification orthographique ou de dictionnaire (General, Editors, Text editor, Spelling).
3. Configurez le chemin du dictionnaire français en sélectionnant le fichier `dictionnaire_fr_utf8.txt` que vous avez dans le dossier SAE201 à la racine du projet.
4. Enregistrez les modifications.

Une fois que vous avez configuré le dictionnaire français, votre IDE utilisera ce dictionnaire pour la vérification orthographique dans vos fichiers de code source français.

## Contribuer

Si vous souhaitez contribuer à ce projet, vous pouvez suivre ces étapes :

1. Fork (Bifurquer) ce référentiel et clonez-le sur votre machine locale.
2. Assurez-vous de configurer correctement votre environnement de développement avec JDK-20 et JavaFX.
3. Effectuez les modifications souhaitées.
4. Testez votre code pour vous assurer qu'il fonctionne correctement.
5. Soumettez une demande d'extraction (Pull Request) en expliquant les modifications que vous avez apportées.

N'hésitez pas à signaler tout problème ou à proposer des améliorations en créant une issue dans ce référentiel.
