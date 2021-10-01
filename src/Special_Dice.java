import java.util.InputMismatchException;
import java.util.Scanner;

public class Special_Dice extends Dice_Item{



    boolean in_Succession=false;

    public Special_Dice(){

    }
    public int diaRandomNumber(){

        int wantNum=0;
        Scanner scan=new Scanner(System.in);
        while(true) {
            System.out.println("움직이고 싶은 칸 수를 입력하세요 \t\t( 1~12 입력 )");
            while (true) {
                try {
                    wantNum = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력해주세요");
                }
            }
            if (wantNum >= 1 && wantNum <= 12) {
                break;
            }
            else{
                System.out.println("숫자범위를 확인하세요");
            }
        }
        return wantNum;

    }
    public int goldenRandomNumber(){
        int wantNum=0;
        Scanner scan=new Scanner(System.in);

        while(true) {
            System.out.println("움직이고 싶은 칸 수를 입력하세요 \t\t( 1~12 입력 )");
            while (true) {
                try {
                    wantNum = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력해주세요");
                }
            }
            if(wantNum>=1&&wantNum<=12){
                break;
            }
            else{
                System.out.println("입력오류");
            }
        }
        this.function();
        if(this.randomNum1==1||this.randomNum1==2){
            System.out.println("\n====================================================================");
            System.out.println("황금주사위 아이템 사용 성공!\n");
            System.out.println("====================================================================\n");
            return wantNum;
        }
        else {
            System.out.println("\n====================================================================");
            System.out.println("황금주사위 아이템 사용 실패!\n");
            System.out.println("====================================================================\n");
            while(randomNum1+randomNum2==wantNum) {
                this.function();
            }
            System.out.println("첫번째 주사위 결과  : " + randomNum1 + "\n두번째 주사위 결과  : " + randomNum2 + "\n\n앞으로 " + (randomNum1 + randomNum2) + "칸 전진합니다.\n");
            if(randomNum1==randomNum2){
                System.out.println("\n====================================================================");
                System.out.println("주사위 아이템 사용 실패시, 더블 주사위 효과는 없습니다.");
                this.isDoubleNumber=false;
            }
            return this.randomNum1+randomNum2;
        }

    }

}
