cd SAE201\application-ihm\bin

::Si votre installation de JDK-20 le nécessite et/ou est à la racine du projet, définissez le path ci-dessous
set path=..\..\..\jdk-20\bin

::JavaFX (SDK20) doit être à la racine du projet avec le chemin ci-dessous
java --module-path ..\..\..\javafx-sdk-20\lib --add-modules javafx.controls,javafx.fxml application.Othello