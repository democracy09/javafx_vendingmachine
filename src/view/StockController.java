package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.FileInOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StockController {

    private int[] stock;

    @FXML private Button beforeBtn;
    @FXML private Label water;
    @FXML private Label coffee;
    @FXML private Label ion;
    @FXML private Label fine;
    @FXML private Label soda;

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

    @FXML
    private void initialize()
    {
        stock = FileInOut.fromFile(13,5,"stock.txt");
        water.setText(stock[0]+"개");
        coffee.setText(stock[1]+"개");
        ion.setText(stock[2]+"개");
        fine.setText(stock[3]+"개");
        soda.setText(stock[4]+"개");
    }

    public void addWater(){
        stock[0]+=1;
        water.setText(stock[0]+"개");
        FileInOut.toFile("stock.txt",stock);
    }

    public void addCoffee(){
        stock[1]+=1;
        coffee.setText(stock[1]+"개");
        FileInOut.toFile("stock.txt",stock);
    }

    public void addIon(){
        stock[2]+=1;
        ion.setText(stock[2]+"개");
        FileInOut.toFile("stock.txt",stock);
    }

    public void addFine(){
        stock[3]+=1;
        fine.setText(stock[3]+"개");
        FileInOut.toFile("stock.txt",stock);
    }

    public void addSoda(){
        stock[4]+=1;
        soda.setText(stock[4]+"개");
        FileInOut.toFile("stock.txt",stock);
    }
}
