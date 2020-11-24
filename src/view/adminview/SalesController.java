package view.adminview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.FileInOut;

import java.time.LocalDate;
import java.util.Arrays;

public class SalesController {

    @FXML
    private Button beforeBtn;
    @FXML private Button stockMenu;
    @FXML private Button coinMenu;
    @FXML private CategoryAxis xAxis;
    @FXML private BarChart barChart;

    private ObservableList<String> months = FXCollections.observableArrayList();


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

    @FXML private void initialize(){
        LocalDate localDate = LocalDate.now();
        String[] monthNames = new String[12];
        for(int i = 0;i< monthNames.length;i++){
            monthNames[i] = localDate.getMonthValue()+"월";
            localDate = localDate.minusMonths(1);
        }
        months.addAll(Arrays.asList(monthNames));
        xAxis.setCategories(months);

        int[] sales = FileInOut.fromFile(12,"sales.txt");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(int i=0;i<sales.length;i++){
            series.getData().add(new XYChart.Data<>(monthNames[i],sales[i]));
        }
        barChart.setLegendVisible(false);
        barChart.getData().add(series);
    }


}
