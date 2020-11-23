package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AppUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {


    @FXML
    private TextField txtPassword;
    @FXML
    private Button loginBtn;
    @FXML
    private Button mainBtn;
    @FXML private Button setPassword;

    private String password;

    @FXML
    private void initialize() {
        password = new String();
        try(FileReader fileReader = new FileReader("password.txt")){
            char[] paw = new char[17];
            int i=0;
            while(i!=-1){
                i=fileReader.read(paw);
            }
            int k=0;
            while(paw[k]!=0){
                password += Character.toString(paw[k]);
                k++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pressLogin(){
        String userPw = txtPassword.getText();

        if(userPw.equalsIgnoreCase(password)){
            try {
                Parent adminPage = FXMLLoader.load(getClass().getResource("/view/adminview/AdminPage.fxml"));
                Scene scene = new Scene(adminPage);
                Stage primaryStage = (Stage)loginBtn.getScene().getWindow();
                primaryStage.setScene(scene);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AppUtil.Alert("패스워드가 틀렸습니다",null);
        }
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

    public void goSetPw(){
        try {
            Parent setPw = FXMLLoader.load(getClass().getResource("/view/SetPw.fxml"));
            Scene scene = new Scene(setPw);
            Stage primaryStage = (Stage)setPassword.getScene().getWindow();
            primaryStage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
