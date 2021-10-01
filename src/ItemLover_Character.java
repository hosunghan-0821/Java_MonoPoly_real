import java.util.Scanner;

public class ItemLover_Character extends Character {

    String[] item = new String[]{"황금 주사위", "다이아 주사위", "통행료 면제권","무인도 탈출권"};
    boolean[] itemState = new boolean[]{false, false, false,false};

    public ItemLover_Character() {
        this.name = "보석상";
        this.characterNumber = 2;
    }


    public void specialFunction(Player player) {
        int cnt=0;
        for(int i=0;i<itemState.length;i++) {
            if (itemState[i]==true){
                cnt++;
            }
        }
        if(cnt== item.length){
            //System.out.println("\n====================================================================");
            //System.out.println("모든 아이템을 1회씩 획득하여서 특수능력 사용이 불가능 합니다\n");
            //System.out.println("\n====================================================================");
            return;
        }
        System.out.println("\n보석상 캐릭터 특수능력을 사용하시겠습니까? \t\t(1. 예 2. 아니오)");
        Scanner scan = new Scanner(System.in);
        int choice;
        while (true) {

            try {
                choice = scan.nextInt();
                break;
            } catch (Exception e) {
                scan = new Scanner(System.in);
                System.out.println("입력오류");
            }
        }
        if(choice==1) {
            System.out.println("\n====================================================================");
            System.out.println(" 아이템 항목 \t\t\t 획득가능여부\n");

            for (int i = 0; i < item.length; i++) {
                System.out.print(i+1+". "+item[i]+"\t\t");

                if(itemState[i]==false) {
                    System.out.println(" O");
                }
                else{
                    System.out.println(" X");
                }
            }
            System.out.println("\n획득할 아이템 번호를 입력해주세요");
            while (true) {

                try {
                    choice = scan.nextInt();

                    if(choice>=1&&choice<=item.length&&itemState[choice-1]==false) {
                        break;
                    }
                   else{
                       System.out.println("입력오류");
                    }
                } catch (Exception e) {
                    scan = new Scanner(System.in);
                    System.out.println("입력오류");
                }
            }

        }
        else{
            return;
        }

            itemState[choice-1]=true;
            System.out.println("\n====================================================================");
            if(choice==1){
                System.out.println("황금 주사위를 1개를 획득하였습니다.\n");
                player.golden_dice++;
            }
            else if(choice==2){
                System.out.println("다이아 주사위 1개를 획득하였습니다.\n");
                player.dia_dice++;
            }
            else if(choice==3){
                System.out.println("통행료 면제권 1개를 획득하였습니다.\n");
                player.freePassPay++;
            }
            else if(choice==4){
                System.out.println("무인도 탈출권 1개를 획득하였습니다.\n");
                player.islandEscape++;
            }



    }

}
