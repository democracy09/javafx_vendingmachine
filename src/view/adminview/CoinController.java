package view.adminview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AppUtil;
import util.FileInOut;

import java.io.File;
import java.time.LocalDate;

public class CoinController {

    private int[] stock;

    @FXML private Button beforeBtn;
    @FXML private Button stockMenu;
    @FXML private Button salesMenu;
    @FXML private Button priceMenu;
    @FXML private Label won10;
    @FXML private Label won50;
    @FXML private Label won100;
    @FXML private Label won500;
    @FXML private Label won1000;

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

    @FXML private void initialize(){
        stock = FileInOut.fromFile(5,"coin.txt");

        won10.setText(stock[0]+"개");
        won50.setText(stock[1]+"개");
        won100.setText(stock[2]+"개");
        won500.setText(stock[3]+"개");
        won1000.setText(stock[4]+"개");
    }

    public void collect(){
        int[] colCoin = new int[5];
        for(int i=0;i<colCoin.length-1;i++){
            while((stock[i]-colCoin[i])>5){
                colCoin[i]++;
            }
            stock[i]-=colCoin[i];
        }

        colCoin[4] = stock[4];
        stock[4]=0;

        won10.setText(stock[0]+"개");
        won50.setText(stock[1]+"개");
        won100.setText(stock[2]+"개");
        won500.setText(stock[3]+"개");
        won1000.setText(stock[4]+"개");

        FileInOut.toFile("coin.txt",stock);

        int total = colCoin[0]*10+colCoin[1]*50+colCoin[2]*100+colCoin[3]*500+colCoin[4]*1000;
        AppUtil.Alert("10원: "+colCoin[0]+"개"+"\n"+"50원: "+colCoin[1]+"개"+"\n"+"100원: "+colCoin[2]+"개"+"\n"+"500원: "+colCoin[3]+"개"
                +"\n1000원: "+ colCoin[4]+"개"+"\n\n총 "+total+"원",null);

        int[] temp = FileInOut.fromFile(12,"sales.txt");

        temp[0]+=total;
        FileInOut.toFile("sales.txt",temp);
    }
}
