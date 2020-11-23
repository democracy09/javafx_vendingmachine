package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageController {


    @FXML private Button beforeBtn;
    @FXML private Button stockMenu;


    public void gotoPre(){
        try {
            Parent adminLogin = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
            Scene scene = new Scene(adminLogin);
            Stage primaryStage = (Stage)beforeBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pressStock(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Stock.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)stockMenu.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
