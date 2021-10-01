import java.util.Scanner;

public class Architech_Character extends Character{
    int characterSpecialFunctionCnt=3;
    //건설가 캐릭터 클래스
    public static final String FONT_RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String FONT_PURPLE = "\u001B[35m";



    public Architech_Character() {
        this.name="건설가";

        this.characterNumber=1;
    }

    public void specialFunction(Player player,Ground[] allGround, Map map) {
        if(characterSpecialFunctionCnt==0){
            return;
        }
        System.out.println("\n건설가 캐릭터 특수능력을 사용하시겠습니까? \t\t(1. 예 2. 아니오) \t\t 사용 가능 횟수 : "+characterSpecialFunctionCnt);
        Scanner scan = new Scanner(System.in);
        int choice;
        String check;
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
            boolean change= false;
            System.out.println("\n====================================================================");
            System.out.println("업그레이드 할 땅의 이름을 입력해주세요");
            System.out.println("**(만약 업그레이드 할 땅이 없다면 \"없음\"을 쳐주세요 )");
            check = scan.next();
            if (check.equals("없음")) {
                return;
            }
            while(true){
                for(int i=0;i<20;i++){
                    if(check.equals(player.Ground[i])&&allGround[i].getHostNumber()==player.playerNumber){

                        System.out.println("\n====================================================================");
                        System.out.println("현재 땅을 업그레이드 합니다. \n");

                        if(allGround[i].structure==null){
                            System.out.println("현재 땅에 별장을 건설합니다.");
                            allGround[i].structure="별장";
                            allGround[i].passPay = (int) ((allGround[i].price + allGround[i].villaPrice) * 1.2);
                            map.changeGroundStructure(allGround[i],1);
                        }
                        else if(allGround[i].structure.equals("랜드마크")){
                            System.out.println("더 이상 업그레이드 할 건물이 없습니다. \t 특수기능을 마칩니다.\n");
                            return;
                        }
                        else if(allGround[i].structure.equals("호텔")){
                            System.out.println("현재 호텔 건물을 랜드마크로 업그레이드 합니다.");
                            allGround[i].structure="랜드마크";
                            allGround[i].passPay = (int) ((allGround[i].price + allGround[i].landMarkPrice) * 1.2);
                            map.upgradePlayerStructure("호텔",allGround[i]);
                        }
                        else if(allGround[i].structure.equals("빌딩")){
                            System.out.println("현재 빌딩 건물을 호텔로 업그레이드 합니다.");
                            allGround[i].structure="호텔";
                            allGround[i].passPay = (int) ((allGround[i].price + allGround[i].hotelPrice) * 1.2);
                            map.upgradePlayerStructure("빌딩",allGround[i]);
                        }
                        else if(allGround[i].structure.equals("별장")){
                            System.out.println("현재 별장 건물을 빌딩으로 업그레이드 합니다.");
                            allGround[i].structure="빌딩";
                            allGround[i].passPay = (int) ((allGround[i].price + allGround[i].BuildingPrice) * 1.2);
                            map.upgradePlayerStructure("별장",allGround[i]);
                        }
                        characterSpecialFunctionCnt--;
                        change =true;
                        break;
                    }
                }
                if (check.equals("없음")) {
                    return;
                }
                if (change == true) {
                    break;
                } else {
                    System.out.println("입력오류 자신이 소유한 땅을 다시입력해주세요 \n");
                    check = scan.next();
                }

            }

        }
    }
    public void passiveFunction(Map map,Ground ground){

        System.out.println("\n====================================================================");
        System.out.println(FONT_RED+"건설가 패시브가 발동 되었습니다. 당신의 호텔을 랜드마크로 업그레이드 됩니다."+RESET);
        System.out.println("\n====================================================================");
        ground.structure = "랜드마크";
        ground.passPay = (int) ((ground.price + ground.landMarkPrice) * 1.2);
        map.upgradePlayerStructure("호텔", ground);

    }

}
