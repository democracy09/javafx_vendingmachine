package view.adminview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminPageController {


    @FXML private Button beforeBtn;
    @FXML private Button stockMenu;
    @FXML private Button coinMenu;
    @FXML private Button salesMenu;
    @FXML private Button priceMenu;

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
            Parent root = FXMLLoader.load(getClass().getResource("/view/adminview/Stock.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)stockMenu.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pressCoin(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/adminview/Coin.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)coinMenu.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pressSales(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/adminview/Sales.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)salesMenu.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pressPrice(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/adminview/Price.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)priceMenu.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
