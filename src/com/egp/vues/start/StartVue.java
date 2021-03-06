package com.egp.vues.start;

import com.egp.constants.Images;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Modele;
import com.egp.vues.game.MainVue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.egp.constants.utils.getURL;


public class StartVue extends Scene {
    private final Pane root;
    private final Stage stage;

    private int rows = 10;
    private int cols = 10;
    private int difficulty = 1;
    private final ArrayList<PlayerType> players;

    public StartVue(Pane root, Stage stage){
        super(root, 250, 380);

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
                    this.rows = Integer.parseInt(newValue);
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
                   this.cols = Integer.parseInt(newValue);
                } catch(NumberFormatException ignored) {}
            }
        });


        Text sizeText = new Text("Taille de la grille: ");
        HBox grilleSizeBox = new HBox();
        grilleSizeBox.setAlignment(Pos.CENTER_LEFT);
        grilleSizeBox.getChildren().addAll(rowField, new Text("X"), colField);

        Text title = new Text("Ile Interdite");

        BackgroundImage backgroundImage = new BackgroundImage(
                Images.background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        GridPane grilleSizeBoxCont = new GridPane();
        grilleSizeBoxCont.add(sizeText,0,0);
        grilleSizeBoxCont.add(grilleSizeBox,0,1);


        HBox difficuktyHBox = new HBox();
        Text difficultyText = new Text("Difficulté");
        Text difficultyValueText = new Text(Integer.toString(this.difficulty));
        Slider difficultySlider = new Slider(1, 5, 1);
        difficultySlider.setMaxWidth(145);

        difficultySlider.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    int formatedValue = BigDecimal.valueOf(newValue.intValue())
                            .setScale(2, RoundingMode.HALF_UP).intValue();
                    difficultyValueText.setText(String.valueOf(formatedValue));
                    this.difficulty = formatedValue;
                }
        );

        difficuktyHBox.getChildren().addAll(difficultyText, difficultySlider, difficultyValueText);

        PlayerSelectVue playerSelectVue = new PlayerSelectVue();
        this.players = playerSelectVue.getPlayersSelected();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setStyle("-fx-font-size: 10pt;");
        gridPane.setMinWidth(250);
        gridPane.setMinHeight(380);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.add(title,0,0);
        gridPane.add(grilleSizeBoxCont,0,1);
        gridPane.add(difficuktyHBox, 0, 2);
        gridPane.add(playerSelectVue, 0, 3);
        gridPane.add(jouerButton,0,4);

        gridPane.setBackground(new Background(backgroundImage));

        this.root.getChildren().add(gridPane);
    }

    private void setMainScene() {
        boolean allPlayerSelect = true;

        for (PlayerType playerType :
                this.players) {
            if (playerType == null) {
                allPlayerSelect = false;
                break;
            }
        }

        if(this.rows < 6 || this.cols < 6 || this.rows > 20 || this.cols > 20) {
           Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("La taille de la grille ne va pas :(");
            alert.setHeaderText("Mauvaise taille de la grille");
            alert.setContentText("Veuillez selectionner une grille de 6x6 à 20x20 :)");

            alert.showAndWait();
        } else if (allPlayerSelect){
            Modele modele = new Modele(
                    this.cols,
                    this.rows,
                    this.players,
                    1. - (this.difficulty / 6.)
            );

            this.root.getChildren().clear();

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
