package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainLayout.fxml"));
            AnchorPane ap = (AnchorPane)loader.load();
            Scene scene = new Scene(ap,600,400);

            stage.resizableProperty().setValue(false);
            stage.setScene(scene);
            stage.setTitle("Vending Machine");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){ launch(args); }
}
