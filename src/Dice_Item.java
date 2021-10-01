import java.util.Scanner;

public class Dice_Item extends Items{
    public int randomNum1;
    public int randomNum2;
    public double random1 = 0;
    public double random2 = 0;
    public boolean isDoubleNumber=false;

    public Dice_Item() {
        name="일반주사위" ;

    }

    @Override
    public int function() {
        isDoubleNumber=false;
        String check;
        int diceNumControl = 0;
        Scanner scan = new Scanner(System.in);
        this.random1 = Math.random();
        this.random2 = Math.random();
        this.randomNum1 = (int) (random1 * 6 + 1);
        this.randomNum2 = (int) (random2 * 6 + 1);
        if(randomNum1==randomNum2){
            isDoubleNumber=true;
        }
        return (randomNum1 + randomNum2);
    }
}
