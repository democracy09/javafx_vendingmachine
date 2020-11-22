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
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class SetPwController {

    @FXML private Button undoBtn;
    @FXML private TextField prePassword;
    @FXML private TextField newPassword;

    private String password;

    @FXML
    private void initialize(){
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

        newPassword.setPromptText("8자 이상 16자 이하");
    }

    public void confirm(){
        String pre = prePassword.getText();
        String newPw = newPassword.getText();
        Pattern num = Pattern.compile("[1234567890]");
        Pattern specialChar = Pattern.compile("[~!@#$%^&*()_+|<>?]");

        if(pre.equals(password)){
            try{
                if((num.matcher(newPw).find())&&specialChar.matcher(newPw).find()){
                    if((newPw.length()>=8)&&(newPw.length()<=16)){
                        try (FileWriter fileWriter = new FileWriter("password.txt")) {
                            fileWriter.write(newPw);
                            fileWriter.flush();
                            AppUtil.Alert("저장되었습니다",null);
                            before();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {throw new PwLengthException();}
                }else{ throw new PwNotValidException();}
            }catch(PwNotValidException e){
                AppUtil.Alert("비밀번호는 숫자 및 특수문자를 적어도 하나 포함해야 합니다",null);
            }catch(PwLengthException e){
                AppUtil.Alert("비밀번호는 8자이상 16자이하여야 합니다",null);
            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            AppUtil.Alert("이전 비밀번호가 틀렸습니다",null);
        }
    }

    static class PwNotValidException extends RuntimeException{ }
    static class PwLengthException extends RuntimeException{ }



    public void before(){
        try{
            Parent login = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage = (Stage)undoBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
