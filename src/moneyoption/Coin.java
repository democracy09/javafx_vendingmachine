package moneyoption;

public class Coin {

    private int won10;
    private int won50;
    private int won100;
    private int won500;

    public Coin(int won10,int won50, int won100, int won500) {
        this.won10 = won10;
        this.won50 = won50;
        this.won100 = won100;
        this.won500 = won500;
    }
    public Coin() {
        this.won10=0;
        this.won50 = 0;
        this.won100 = 0;
        this.won500 = 0;
    }

    public int Won10(){return 10;}
    public int Won50(){return 50;}
    public int Won100(){return 100;}
    public int Won500(){return 500;}

    public int getWon10() {
        return won10;
    }

    public void setWon10(int won10) {
        this.won10 = won10;
    }

    public int getWon50() {
        return won50;
    }

    public void setWon50(int won50) {
        this.won50 = won50;
    }

    public int getWon100() {
        return won100;
    }

    public void setWon100(int won100) {
        this.won100 = won100;
    }

    public int getWon500() {
        return won500;
    }

    public void setWon500(int won500) {
        this.won500 = won500;
    }
}
