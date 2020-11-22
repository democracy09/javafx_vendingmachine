package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField txtPassword;
    @FXML
    private Button loginBtn;
    @FXML
    private Button mainBtn;

    @FXML
    private void initialize() {

    }

    public void gotoMain(){
       try {
           Parent main = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
           Scene scene = new Scene(main);
           Stage primaryStage = (Stage)mainBtn.getScene().getWindow();
           primaryStage.setScene(scene);
       }catch(Exception e){
           e.printStackTrace();
       }
    }


}
