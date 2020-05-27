package com.egp.vues;

import com.egp.constants.Images;
import com.egp.constants.enums.PlayerType;
import com.egp.modeles.Modele;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private ArrayList<PlayerType> players;

    public StartVue(Pane root, Stage stage){
        super(root, 250, 300);

        this.players = new ArrayList<>();
        this.players.add(PlayerType.Explorateur);

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
        rowField.setMaxWidth(30);
        rowField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rowField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            rows = Integer.parseInt(newValue);
        });

        TextField colField = new TextField();
        colField.setText("10");
        colField.setMaxWidth(30);
        colField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                colField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            cols = Integer.parseInt(newValue);
        });

        TextField playerField = new TextField();
        playerField.setText("1");
        playerField.setMaxWidth(40);
        playerField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                playerField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            int nbPlayer = Integer.parseInt(newValue);
            this.players.clear();
            for(int i = 0; i<nbPlayer; i++)
                this.players.add(PlayerType.Explorateur);
        });

        Text sizeText = new Text("Taille de la grille: ");
        HBox grilleSizeBox = new HBox();
        grilleSizeBox.setAlignment(Pos.CENTER_LEFT);
        grilleSizeBox.getChildren().addAll(rowField, new Text("X"), colField);

        HBox playerNumberBox = new HBox();
        playerNumberBox.setAlignment(Pos.CENTER_LEFT);
        playerNumberBox.getChildren().addAll(new Text("Nombre de joueurs: "), playerField);

        Text title = new Text("Ile Interdite");

        BackgroundImage backgroundImage = new BackgroundImage(Images.background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        GridPane grilleSizeBoxCont = new GridPane();
        grilleSizeBoxCont.add(sizeText,0,0);
        grilleSizeBoxCont.add(grilleSizeBox,0,1);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setStyle("-fx-font-size: 10pt;");
        gridPane.setMinWidth(250);
        gridPane.setMinHeight(300);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.add(title,0,0);
        gridPane.add(grilleSizeBoxCont,0,1);
        gridPane.add(playerNumberBox, 0, 2);
        gridPane.add(jouerButton,0,4);
        gridPane.add(new PlayerSelectVue(), 0, 3);

        gridPane.setBackground(new Background(backgroundImage));

        root.getChildren().add(gridPane);
    }

    private void setMainScene() {
        Modele modele = new Modele(cols, rows, players, 1./3.);
        root.getChildren().clear();

        FlowPane mainRoot = new FlowPane(this.root);
        MainVue mainVue = new MainVue(modele, mainRoot, this.stage);

        this.stage.setScene(mainVue);
    }

}
