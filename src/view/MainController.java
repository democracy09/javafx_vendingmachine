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
import structure.MyQueue;
import structure.MyStack;
import util.AppUtil;
import util.FileInOut;

import java.io.*;

public class MainController{

    private Water water;
    private Coffee coffee;
    private IonDrink ionDrink;
    private Soda soda;
    private FineCoffee fineCoffee;
    private Coin machineCoin;
    private PaperMoney paperMoney;
    private MyStack<Integer> taskStack;
    private MyQueue<Integer> salesQueue;
    private int totalWon;

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
    @FXML
    private Label waterPrice;
    @FXML
    private Label coffeePrice;
    @FXML
    private Label ionPrice;
    @FXML
    private Label finePrice;
    @FXML
    private Label sodaPrice;

    @FXML
    private void initialize()
    {
        int[] stock = FileInOut.fromFile(5,"stock.txt");
        int[] coinStock = FileInOut.fromFile(5, "coin.txt");
        int[] price = FileInOut.fromFile(5,"price.txt");
        water = new Water(price[0], stock[0]);
        coffee = new Coffee(price[1], stock[1]);
        ionDrink = new IonDrink(price[2], stock[2]);
        fineCoffee = new FineCoffee(price[3], stock[3]);
        soda = new Soda(price[4], stock[4]);
        machineCoin = new Coin(coinStock[0],coinStock[1],coinStock[2],coinStock[3]);
        paperMoney = new PaperMoney(coinStock[4]);
        taskStack = new MyStack<>();

        waterPrice.setText(water.getPrice()+"원");
        coffeePrice.setText(coffee.getPrice()+"원");
        ionPrice.setText(ionDrink.getPrice()+"원");
        finePrice.setText(fineCoffee.getPrice()+"원");
        sodaPrice.setText(soda.getPrice()+"원");

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
            machineCoin.setWon10(machineCoin.getWon10()-change10);
            machineCoin.setWon50(machineCoin.getWon50()-change50);
            machineCoin.setWon100(machineCoin.getWon100()-change100);
            machineCoin.setWon500(machineCoin.getWon500()-change500);
            totalWon = totalChange;
            total.setText(totalWon+"원");
            taskStack.push(-1);
        }
    }

    public void undo() {
        int undoWon;
        try {
            undoWon = taskStack.peek();

            if (undoWon == 10) {
                machineCoin.setWon10(machineCoin.getWon10() - 1);
                totalWon -= 10;
                AppUtil.Alert("10원 취소", null);
                taskStack.pop();
            } else if (undoWon == 50) {
                machineCoin.setWon50(machineCoin.Won50() - 1);
                totalWon -= 50;
                taskStack.pop();
                AppUtil.Alert("50원 취소", null);
            } else if (undoWon == 100) {
                machineCoin.setWon100(machineCoin.getWon100() - 1);
                totalWon -= 100;
                taskStack.pop();
                AppUtil.Alert("100원 취소", null);
            } else if (undoWon == 500) {
                machineCoin.setWon500(machineCoin.getWon500() - 1);
                totalWon -= 500;
                taskStack.pop();
                AppUtil.Alert("500원 취소", null);
            } else if (undoWon == 1000) {
                if(paperMoney.getWon1000() < 0) throw new UnableToUndoException();
                paperMoney.setWon1000(paperMoney.getWon1000() - 1);
                totalWon -= 1000;
                taskStack.pop();
                AppUtil.Alert("1000원 취소", null);
            } else if (undoWon < -1) {
                AppUtil.Alert("취소 불가 : 음료 구매", null);
            } else if(undoWon==-1){throw new UnableToUndoException();}
        } catch (NullPointerException e) {
            AppUtil.Alert("입력된 금액이 없습니다.", null);
        }catch(UnableToUndoException e){
            AppUtil.Alert("취소 불가 : 잔돈 반환됨", null);
        }finally{
            total.setText(totalWon+"원");
        }

    }

    private class UnableToUndoException extends RuntimeException{}

    public void press10Won(){
        totalWon += machineCoin.Won10();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 10;
            return;
        }
        taskStack.push(10);
        machineCoin.setWon10(machineCoin.getWon10()+1);
        total.setText(totalWon+"원");
    }

    public void press50Won(){
        totalWon += machineCoin.Won50();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");

            totalWon -= 50;
            return;
        }
        machineCoin.setWon50(machineCoin.getWon50()+1);
        taskStack.push(50);
        total.setText(totalWon+"원");
    }

    public void press100Won(){
        totalWon += machineCoin.Won100();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 100;
            return;
        }
        machineCoin.setWon100(machineCoin.getWon100()+1);
        taskStack.push(100);
        total.setText(totalWon+"원");
    }

    public void press500Won(){
        totalWon += machineCoin.Won500();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            totalWon -= 500;
            return;
        }
        machineCoin.setWon500(machineCoin.getWon500()+1);
        taskStack.push(500);
        total.setText(totalWon+"원");
    }

    public void press1000Won(){
        paperMoney.setWon1000(paperMoney.getWon1000()+1);
        totalWon += paperMoney.Won1000();
        if(taskStack.search(1000)>=3){
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
        taskStack.push(paperMoney.Won1000());
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
            taskStack.push(-water.getPrice());
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
            taskStack.push(-coffee.getPrice());
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
            taskStack.push(-ionDrink.getPrice());
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
            taskStack.push(-fineCoffee.getPrice());
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
            taskStack.push(-soda.getPrice());
            soda.setQuantity(soda.getQuantity()-1);
            sodaLabel.setText(soda.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }

    public void adminLogin(){
        try(FileWriter fileWriter = new FileWriter("stock.txt")){
            String str = water.getQuantity()+"\n"+coffee.getQuantity()+"\n"+ionDrink.getQuantity()+"\n"+fineCoffee.getQuantity()+"\n"+soda.getQuantity();
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fileWriter = new FileWriter("coin.txt")){
            String str = machineCoin.getWon10()+"\n"+ machineCoin.getWon50()+"\n"+ machineCoin.getWon100()+"\n"+ machineCoin.getWon500()+"\n"+ paperMoney.getWon1000();
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
