import java.util.Scanner;

public class Traveler_Character extends Character{

    int characterSpecialFunctionCnt=3;

    public Traveler_Character() {
        this.name="여행가";
        this.characterNumber=3;

    }

    public void specialFunction(Map map,Ground[] allGround,Player player) {
        if(characterSpecialFunctionCnt==0){
            return;
        }
        if(player.isPlayerInIsland) {
            System.out.println("\n현재 플레이어는 무인도에 있습니다. 남은 무인도 턴 횟수 : "+player.stayIslandCnt);
        }

        System.out.println("\n여행가 캐릭터 특수능력을 사용하시겠습니까? \t\t(1. 예 2. 아니오) \t\t 사용 가능 횟수 : "+characterSpecialFunctionCnt);

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
        if(choice==1){

            boolean change=false;
            String check;
            System.out.println("자신의 턴 동안 다른플레이어의 땅 1개를 통행료 0원으로 만듭니다.");
            System.out.print("다른 플레이어가 소유한 땅의 이름을 입력해주세요  ");
            System.out.println("\t**(만약 다른플레이어의 소유한 땅이 없다면 \"없음\"을 쳐주세요 )");
            check = scan.next();
            if (check.equals("없음")) {
                return;
            }
            while (true) {

                for (int i = 0; i < 20; i++) {
                    if (check.equals(allGround[i].name) && allGround[i].getHostNumber() != 0 && allGround[i].getHostNumber() != player.playerNumber&&allGround[i].isBlackHole==false&&allGround[i].isEvnet2X==false) {
                        System.out.println("\n====================================================================");
                        System.out.println(check + "땅의 통행료를 1턴 동안 0원으로 만듭니다.\n");
                        System.out.println("\n====================================================================");
                        change = true;
                        allGround[i].freePassPayCnt+=1;
                        map.changeGroundStatePoint(allGround[i].groundStatePointX, allGround[i].groundStatePointY, "X");
                        player.traveler_character.characterSpecialFunctionCnt--;
                        break;
                    }
                }
                if (check.equals("없음")) {
                    return;
                }
                if (change == true) {
                    break;
                } else {
                    System.out.println("입력오류 다른 플레이어가 소유한 땅을 다시입력해주세요 \t(* 2배 이벤트 혹은 블랙홀이  발생한 땅에는 사용 불가능합니다.)");
                    check = scan.next();
                }
            }
        }
        else{
            return;
        }

    }
    public void passiveFunction(Player player){
        player.money+=15;
        System.out.println("\n====================================================================");
        System.out.println("\n여행가 캐릭터의 패시브가 발동합니다.\t(여행비용 15만원을 획득합니다.)\t\t 보유 돈 : "+player.money+"만원");

    }


}
