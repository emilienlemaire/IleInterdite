package com.egp.vues;

import com.egp.modeles.Modele;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class StartVue extends Scene {
    private final Pane root;
    private final Stage stage;

    public StartVue(Pane root, Stage stage) throws FileNotFoundException {
        super(root, 250, 300);

        this.root = root;
        this.stage = stage;

        Button jouerButton = new Button("Jouer");
        jouerButton.setOnMouseClicked(mouseEvent -> setMainScene());

        Button paramButton = new Button("Paramètres");

        Text title = new Text("Ile Interdite");

        GridPane gridPane = new GridPane();
        gridPane.setMinWidth(250);
        gridPane.setMinHeight(300);
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.add(title,0,0);
        gridPane.add(jouerButton,0,1);
        gridPane.add(paramButton,0,2);

        String path = "resources/background/background.png";
        FileInputStream imageStream = new FileInputStream(path);
        Image image = new Image(imageStream);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //BackgroundFill myBF = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));

        gridPane.setBackground(new Background(backgroundImage));

        root.getChildren().add(gridPane);
    }

    private void setMainScene() {
        Modele modele = new Modele(10, 10, 5);
        root.getChildren().clear();

        FlowPane mainRoot = new FlowPane(this.root);
        MainVue mainVue = new MainVue(modele, mainRoot, this.stage);

        LostVue lostVue = new LostVue(mainVue);

        this.stage.setScene(mainVue);
    }
}
