package view;

import drinkoption.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import moneyoption.Coin;
import moneyoption.PaperMoney;
import structure.MyStack;
import util.AppUtil;

import java.io.IOException;

public class MainController{

    private Water water;
    private Coffee coffee;
    private IonDrink ionDrink;
    private Soda soda;
    private FineCoffee fineCoffee;
    private Coin coin;
    private Coin machineCoin;
    private PaperMoney paperMoney;
    private MyStack<Integer> taskStack;

    @FXML
    private Button adminBtn;
    @FXML
    private Label waterLabel;
    @FXML
    private Label coffeeLabel;
    @FXML
    private Label ionLabel;
    @FXML
    private Label sodaLabel;
    @FXML
    private Label fineLabel;
    @FXML
    private Label total;

    private int totalWon;

    @FXML
    private void initialize()
    {
        water = new Water();
        ionDrink = new IonDrink();
        soda = new Soda();
        coffee = new Coffee();
        fineCoffee = new FineCoffee();
        coin = new Coin();
        paperMoney = new PaperMoney();
        machineCoin = new Coin(5,5,5,5);
        taskStack = new MyStack<Integer>();

        waterLabel.setText(water.getQuantity()+"개");
        ionLabel.setText(ionDrink.getQuantity()+"개");
        coffeeLabel.setText(coffee.getQuantity()+"개");
        fineLabel.setText(fineCoffee.getQuantity()+"개");
        sodaLabel.setText(soda.getQuantity()+"개");

        totalWon = 0;
        total.setText(totalWon+"원");
    }

    public void exchange(){
        int totalChange = totalWon;
        int change500 = 0;
        int change100 = 0;
        int change50 = 0;
        int change10 = 0;
        if(totalChange/500>0) {
            change500 = totalChange/500;
            if(change500>machineCoin.getWon500()) {
                change500 = machineCoin.getWon500();
                totalChange -= machineCoin.getWon500()*500;
            }else totalChange %= 500;
        }


        if(totalChange/100>0) {
            change100 = totalChange/100;
            if(change100>machineCoin.getWon100()) {
                change100 = machineCoin.getWon100();
                totalChange -= machineCoin.getWon100()*100;
            }else totalChange %= 100;
        }


        if(totalChange/50>0) {
            change50 = totalChange/50;
            if(change50>machineCoin.getWon50()) {
                change50 = machineCoin.getWon50();
                totalChange -= machineCoin.getWon50()*50;
            }else totalChange %= 50;
        }

        if(totalChange/10>0) {
            change10 = totalChange/10;
            if(change10>machineCoin.getWon10()) {
                change10 = machineCoin.getWon10();
                totalChange -= machineCoin.getWon10()*10;
            }else totalChange %= 10;
        }

        if(totalChange>0){
            AppUtil.Alert("잔돈이 모자랍니다",null);
        }else{
            AppUtil.Alert("500원 : "+change500+"개\n100원 : "+change100+"개\n50원 : "+change50+"개\n10원 : "+change10+"개",null);
            totalWon = totalChange;
            total.setText(totalWon+"원");
            paperMoney.setWon1000(0);
        }



    }

    public void undo(){
        int undoWon;
        try{
            undoWon=taskStack.peek();

            if(undoWon==10&&coin.getWon10()>0){
              coin.setWon10(coin.getWon10()-1);
               machineCoin.setWon10(machineCoin.getWon10()-1);
                totalWon-=10;
                AppUtil.Alert("10원 취소",null);
                taskStack.pop();
            }else if(undoWon==50&&coin.getWon50()>0){
               coin.setWon50(coin.getWon50()-1);
               machineCoin.setWon50(machineCoin.Won50()-1);
               totalWon-=50;
                taskStack.pop();
             AppUtil.Alert("50원 취소",null);
            }else if(undoWon==100&&coin.getWon100()>0) {
                coin.setWon100(coin.getWon100()-1);
                machineCoin.setWon100(-1);
                totalWon -= 100;
                taskStack.pop();
             AppUtil.Alert("100원 취소", null);
            }else if(undoWon==500&&coin.getWon500()>0) {
                coin.setWon500(-1);
             machineCoin.setWon500(-1);
              totalWon -= 500;
                taskStack.pop();
              AppUtil.Alert("500원 취소", null);
            }else if(undoWon==1000&&paperMoney.getWon1000()>0) {
                paperMoney.setWon1000(-1);
                totalWon -= 1000;
                taskStack.pop();
                AppUtil.Alert("1000원 취소", null);
            }else if(undoWon < 0){
                AppUtil.Alert("취소 불가 : 음료 구매",null);
        }}catch(NullPointerException e){
            AppUtil.Alert("입력된 금액이 없습니다.",null);
        }finally{
            total.setText(totalWon+"원");
        }

    }

    public void press10Won(){
        totalWon += coin.Won10();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 10;
            return;
        }
        coin.setWon10(coin.getWon10()+1);
        taskStack.push(10);
        machineCoin.setWon10(coin.getWon10()+1);
        total.setText(totalWon+"원");
    }

    public void press50Won(){
        totalWon += coin.Won50();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");

            totalWon -= 50;
            return;
        }
        coin.setWon50(coin.getWon50()+1);
        machineCoin.setWon50(coin.getWon50()+1);
        taskStack.push(50);
        total.setText(totalWon+"원");
    }

    public void press100Won(){
        totalWon += coin.Won100();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 100;
            return;
        }
        coin.setWon100(coin.getWon100()+1);
        machineCoin.setWon100(coin.getWon100()+1);
        taskStack.push(100);
        total.setText(totalWon+"원");
    }

    public void press500Won(){
        totalWon += coin.Won500();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 500;
            return;
        }
        coin.setWon500(coin.getWon500()+1);
        machineCoin.setWon500(coin.getWon500()+1);
        taskStack.push(500);
        total.setText(totalWon+"원");
    }

    public void press1000Won(){
        paperMoney.setWon1000(paperMoney.getWon1000()+1);
        totalWon += paperMoney.Won1000();
        if(paperMoney.getWon1000()>3){
            AppUtil.Alert("지폐를 3000원 초과하여 입력할 수 없습니다.", null);
            paperMoney.setWon1000(paperMoney.getWon1000()-1);
            totalWon -= 1000;
            return;
        }else if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", null);
            paperMoney.setWon1000(paperMoney.getWon1000()-1);
            totalWon -= 1000;
            return;
        }
        taskStack.push(1000);
        total.setText(totalWon+"원");
    }

    public void pressWater(){
        if(water.getQuantity()==0){
            AppUtil.Alert("!품절!",null);
            return;
        }
        if(water.getPrice()>totalWon){
            AppUtil.Alert("잔액이 부족합니다.",null);
            return;
        }else{
            totalWon-=water.getPrice();
            taskStack.push(-450);
            water.setQuantity(water.getQuantity()-1);
            waterLabel.setText(water.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void pressCoffee(){
        if(coffee.getQuantity()==0){
            AppUtil.Alert("!품절!",null);
            return;
        }
        if(coffee.getPrice()>totalWon){
            AppUtil.Alert("잔액이 부족합니다.",null);
            return;
        }else{
            totalWon-=coffee.getPrice();
            taskStack.push(-500);
            coffee.setQuantity(coffee.getQuantity()-1);
            coffeeLabel.setText(coffee.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void pressIon(){
        if(ionDrink.getQuantity()==0){
            AppUtil.Alert("!품절!",null);
            return;
        }
        if(ionDrink.getPrice()>totalWon){
            AppUtil.Alert("잔액이 부족합니다.",null);
            return;
        }else{
            totalWon-=ionDrink.getPrice();
            taskStack.push(-550);
            ionDrink.setQuantity(ionDrink.getQuantity()-1);
            ionLabel.setText(ionDrink.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void pressFine(){
        if(fineCoffee.getQuantity()==0){
            AppUtil.Alert("!품절!",null);
            return;
        }
        if(fineCoffee.getPrice()>totalWon){
            AppUtil.Alert("잔액이 부족합니다.",null);
            return;
        }else{
            totalWon-=fineCoffee.getPrice();
            taskStack.push(-700);
            fineCoffee.setQuantity(fineCoffee.getQuantity()-1);
            fineLabel.setText(fineCoffee.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void pressSoda(){
        if(soda.getQuantity()==0){
            AppUtil.Alert("!품절!",null);
            return;
        }
        if(soda.getPrice()>totalWon){
            AppUtil.Alert("잔액이 부족합니다.",null);
            return;
        }else{
            totalWon-=soda.getPrice();
            taskStack.push(-750);
            soda.setQuantity(soda.getQuantity()-1);
            sodaLabel.setText(soda.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void adminLogin(){
        try{
            Parent login = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage = (Stage)adminBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
