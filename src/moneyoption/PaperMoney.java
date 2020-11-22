package moneyoption;

public class PaperMoney {

    private int won1000;

    public PaperMoney(int won1000) {
        this.won1000 = won1000;
    }

    public PaperMoney() {
        this.won1000=0;
    }

    public int Won1000(){
        return 1000;
    }

    public int getWon1000() {
        return won1000;
    }

    public void setWon1000(int won1000) {
        this.won1000 = won1000;
    }
}
