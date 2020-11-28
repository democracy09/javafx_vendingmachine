package view.adminview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AppUtil;
import util.FileInOut;

public class PriceController {

    private int[] price;

    @FXML private TextField waterTXT;
    @FXML private TextField coffeeTXT;
    @FXML private TextField ionTXT;
    @FXML private TextField fineTXT;
    @FXML private TextField sodaTXT;

    @FXML private Button stockMenu;
    @FXML private Button coinMenu;
    @FXML private Button beforeBtn;
    @FXML private Button salesMenu;


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


    @FXML private void initialize(){
        price = FileInOut.fromFile(5,"price.txt");
        waterTXT.setText(price[0]+"원");
        coffeeTXT.setText(price[1]+"원");
        ionTXT.setText(price[2]+"원");
        fineTXT.setText(price[3]+"원");
        sodaTXT.setText(price[4]+"원");
    }

    public void prsWater(){
        String text = waterTXT.getText();
        try{
            int newPrice = Integer.parseInt(text);
            price[0]=newPrice;
            waterTXT.setText(price[0]+"원");
            FileInOut.toFile("price.txt",price);
        }catch(NumberFormatException e){
            AppUtil.Alert("숫자를 입력하세요",null);
        }
    }

    public void prsCoffee(){
        String text = coffeeTXT.getText();
        try{
            int newPrice = Integer.parseInt(text);
            price[0]=newPrice;
            coffeeTXT.setText(price[0]+"원");
            FileInOut.toFile("price.txt",price);
        }catch(NumberFormatException e){
            AppUtil.Alert("숫자를 입력하세요",null);
        }
    }

    public void prsIon(){
        String text = ionTXT.getText();
        try{
            int newPrice = Integer.parseInt(text);
            price[0]=newPrice;
            ionTXT.setText(price[0]+"원");
            FileInOut.toFile("price.txt",price);
        }catch(NumberFormatException e){
            AppUtil.Alert("숫자를 입력하세요",null);
        }
    }

    public void prsFine(){
        String text = fineTXT.getText();
        try{
            int newPrice = Integer.parseInt(text);
            price[0]=newPrice;
            fineTXT.setText(price[0]+"원");
            FileInOut.toFile("price.txt",price);
        }catch(NumberFormatException e){
            AppUtil.Alert("숫자를 입력하세요",null);
        }
    }

    public void prsSoda(){
        String text = sodaTXT.getText();
        try{
            int newPrice = Integer.parseInt(text);
            price[0]=newPrice;
            sodaTXT.setText(price[0]+"원");
            FileInOut.toFile("price.txt",price);
        }catch(NumberFormatException e){
            AppUtil.Alert("숫자를 입력하세요",null);
        }
    }

}
