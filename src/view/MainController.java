package view;

import drinkoption.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import moneyoption.Coin;
import moneyoption.PaperMoney;
import util.AppUtil;

public class MainController {

    private Water water;
    private Coffee coffee;
    private IonDrink ionDrink;
    private Soda soda;
    private FineCoffee fineCoffee;
    private Coin coin;
    private Coin machineCoin;
    private PaperMoney paperMoney;

    @FXML
    private Button waterBtn;
    @FXML
    private Button coffeeBtn;
    @FXML
    private Button ionBtn;
    @FXML
    private Button sodaBtn;
    @FXML
    private Button fineBtn;
    @FXML
    private Button adminBtn;
    @FXML
    private Button exchangeBtn;
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
    private Button won10btn;
    @FXML
    private Button won50btn;
    @FXML
    private Button won100btn;
    @FXML
    private Button won500btn;
    @FXML
    private Button won1000btn;
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

        waterLabel.setText(water.getQuantity()+"개");
        ionLabel.setText(ionDrink.getQuantity()+"개");
        coffeeLabel.setText(coffee.getQuantity()+"개");
        fineLabel.setText(fineCoffee.getQuantity()+"개");
        sodaLabel.setText(soda.getQuantity()+"개");

        totalWon = 0;
        total.setText(totalWon+"원");
    }

    public void press10Won(){
        coin.setWon10(1);
        totalWon += coin.getWon10();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            coin.setWon10(-1);
            totalWon -= 10;
            return;
        }
        machineCoin.setWon10(1);
        total.setText(totalWon+"원");
    }

    public void press50Won(){
        coin.setWon50(1);
        totalWon += coin.getWon50();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            coin.setWon50(-1);
            totalWon -= 50;
            return;
        }
        machineCoin.setWon50(1);
        total.setText(totalWon+"원");
    }

    public void press100Won(){
        coin.setWon100(1);
        totalWon += coin.getWon100();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            coin.setWon100(-1);
            totalWon -= 100;
            return;
        }
        machineCoin.setWon100(1);
        total.setText(totalWon+"원");
    }

    public void press500Won(){
        coin.setWon500(1);
        totalWon += coin.getWon500();
        if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", "Notice");
            coin.setWon500(-1);
            totalWon -= 500;
            return;
        }
        machineCoin.setWon500(1);

        total.setText(totalWon+"원");
    }

    public void press1000Won(){
        paperMoney.setWon1000(1);
        totalWon += paperMoney.getWon1000();
        if(paperMoney.Won1000()>3){
            AppUtil.Alert("지폐를 3000원 초과하여 입력할 수 없습니다.", null);
            paperMoney.setWon1000(-1);
            totalWon -= 1000;
            return;
        }else if(totalWon>5000){
            AppUtil.Alert("5000원을 초과하여 입력할 수 없습니다.", null);
            paperMoney.setWon1000(-1);
            totalWon -= 1000;
            return;
        }
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
            soda.setQuantity(soda.getQuantity()-1);
            sodaLabel.setText(soda.getQuantity()+"개");
            total.setText(totalWon+"원");
        }
    }


}
