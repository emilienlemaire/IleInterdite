package com.egp.vues.start;

import com.egp.constants.Images;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Modele;
import com.egp.vues.end.WinView;
import com.egp.vues.game.MainVue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.egp.constants.utils.*;


public class StartVue extends Scene {
    private final Pane root;
    private final Stage stage;
    private int rows = 10;
    private int cols = 10;
    private final ArrayList<PlayerType> players;

    public StartVue(Pane root, Stage stage){
        super(root, 250, 350);

        try {
            this.getStylesheets().add(getURL("resources/styles/stylesheet.css").toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.root = root;
        this.stage = stage;

        Button jouerButton = new Button("Jouer");
        jouerButton.setOnMouseClicked(mouseEvent -> setMainScene());

        TextField rowField = new TextField();
        rowField.setText("10");
        rowField.setMaxWidth(40);
        rowField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rowField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                try {
                    rows = Integer.parseInt(newValue);
                } catch(NumberFormatException ignored) {}
            }
        });

        TextField colField = new TextField();
        colField.setText("10");
        colField.setMaxWidth(40);
        colField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                colField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                try {
                    cols = Integer.parseInt(newValue);
                } catch(NumberFormatException ignored) {}
            }
        });


        Text sizeText = new Text("Taille de la grille: ");
        HBox grilleSizeBox = new HBox();
        grilleSizeBox.setAlignment(Pos.CENTER_LEFT);
        grilleSizeBox.getChildren().addAll(rowField, new Text("X"), colField);

        Text title = new Text("Ile Interdite");

        BackgroundImage backgroundImage = new BackgroundImage(Images.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        GridPane grilleSizeBoxCont = new GridPane();
        grilleSizeBoxCont.add(sizeText,0,0);
        grilleSizeBoxCont.add(grilleSizeBox,0,1);


        PlayerSelectVue playerSelectVue = new PlayerSelectVue();
        this.players = playerSelectVue.getPlayerTypes();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setStyle("-fx-font-size: 10pt;");
        gridPane.setMinWidth(250);
        gridPane.setMinHeight(350);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.add(title,0,0);
        gridPane.add(grilleSizeBoxCont,0,1);
        gridPane.add(playerSelectVue, 0, 2);
        gridPane.add(jouerButton,0,3);

        gridPane.setBackground(new Background(backgroundImage));

        root.getChildren().add(gridPane);
    }

    private void setMainScene() {
        boolean allPlayerSelect = true;

        for (PlayerType playerType :
                players) {
            if (playerType == null) {
                allPlayerSelect = false;
                break;
            }
        }

        if(rows < 5 || cols < 5 || rows > 20 || cols > 20) {
           Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("La taille de la grille ne va pas :(");
            alert.setHeaderText("Mauvaise taille de la grille");
            alert.setContentText("Veuillez selectionner une grille de 5x5 à 20x20 :)");

            alert.showAndWait();
        } else if (allPlayerSelect){
            Modele modele = new Modele(cols, rows, players, 1./3.);
            root.getChildren().clear();

            FlowPane mainRoot = new FlowPane(this.root);
            MainVue mainVue = new MainVue(modele, mainRoot, this.stage);

            this.stage.setScene(mainVue);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Selectionnez tous les types de joueurs");
            alert.setHeaderText("Joueurs non sélectionnés");
            alert.setContentText("Il semblerait que vous ayez oublié de selectionner un ou plusieurs type de" +
                    "joueurs, veuillez tous les selectionner ou supprimer ceux aue vous ne voulez pas.");

            alert.showAndWait();
        }

    }

}
