import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GoldenKey extends XY {
    public String name;

    public GoldenKey(int x, int y, int index, String name) {
        super(x, y, index);
        this.name = name;
    }

    public void randomGoldenKey(Player player, Player[] allPlayer, Ground[] ground, Map map,boolean isDemo) {
        String check;
        Scanner scan = new Scanner(System.in);
        int randomNum;
        double random;
        random = Math.random();
        randomNum = (int) (random * 11 + 1);
        //randomNum = 6;

        System.out.println("\n" + player.playerNumber + "번 플레이어는  \"황금열쇠\" 칸으로 이동하였습니다. 황금열쇠를 열어보기 위해 아무키를 눌러주세요\n");
        check = scan.next();
        if(isDemo==false) {
            if (randomNum == 1 || randomNum == 2) {
                goldenKeyFunction1(player);
            } else if (randomNum == 3) {
                goldKeyFunction2(player);
            } else if (randomNum == 4 || randomNum == 5) {
                goldKeyFunction3(player, allPlayer);
            } else if (randomNum == 6 || randomNum == 7) {
                goldKeyFunction4(player, ground, map);
            } else if (randomNum == 8) {
                goldKeyFunction5(player);
            } else if (randomNum == 9) {
                goldKeyFunction6(player, ground, map);
            } else if (randomNum == 10) {
                goldKeyFunction7(player);
            } else if (randomNum == 11) {
                goldKeyFunction8(player);
            }
        }
        else{
            System.out.println("\n====================================================================");
            System.out.println("황금열쇠의 기능 번호 눌러서 확인\n");
            System.out.println("1. 주사위 아이템 받기 \n");
            System.out.println("2. 통행료 면제권 아이템 받기 \n");
            System.out.println("3. 다른 플레이어들로부터 30만원 씩 받기 \n");
            System.out.println("4. 다른 플레이어의 땅 3턴동안 통행료 0원 만들기 \n");
            System.out.println("5. 로또 -> 돈  500만원 받기 \n");
            System.out.println("6. 보유세 지불하기 -> 큰 악재 \n");
            System.out.println("7. 무인도 탈출권 획득하기\n");
            System.out.println("8. 캐릭터별 특수능력 2회 && 보석상은 통행료 면제권  얻기");
            System.out.println("\n====================================================================");
            while(true) {
                try {
                    randomNum = scan.nextInt();
                    if(randomNum>=1&&randomNum<=8) {
                        break;
                    }
                    else{
                        System.out.println("입력오류");
                    }
                }
                catch(InputMismatchException ime){
                    scan=new Scanner(System.in);
                    System.out.println("입력오류");
                }
            }
            if (randomNum == 1 ) {
                goldenKeyFunction1(player);
            } else if (randomNum == 2) {
                goldKeyFunction2(player);
            } else if (randomNum == 3 ) {
                goldKeyFunction3(player, allPlayer);
            } else if (randomNum == 4 ) {
                goldKeyFunction4(player, ground, map);
            } else if (randomNum == 5) {
                goldKeyFunction5(player);
            }else if(randomNum==6){
                goldKeyFunction6(player,ground,map);
            }else if(randomNum==7){
                goldKeyFunction7(player);
            }else if(randomNum==8){
                goldKeyFunction8(player);
            }
        }
    }

    public void goldenKeyFunction1(Player player) {
        //주사위 아이템 지급해주는 기능
        System.out.println("\n====================================================================");
        System.out.println("황금열쇠에서 주사위 아이템을 지급하였습니다.\t\t황금주사위 2개\t\t다이아 주사위 1개");
        System.out.println("\n====================================================================");
        player.golden_dice += 2;
        player.dia_dice++;
    }

    public void goldKeyFunction2(Player player) {
        System.out.println("\n====================================================================");
        System.out.println("통행료 면제권 1장을 받았습니다. 이후에 다른 플레이어 땅 밟을시 사용 가능합니다.");
        System.out.println("\n====================================================================");
        player.freePassPay++;
    }

    public void goldKeyFunction3(Player player, Player[] allPlayer) {
        int cnt = 0;
        for (int i = 0; i < allPlayer.length; i++) {
            if (player.playerNumber != allPlayer[i].playerNumber && allPlayer[i].playerNumber != 0) {
                if (allPlayer[i].money >= 30) {
                    allPlayer[i].money -= 30;
                    cnt++;
                }
            }
        }
        player.money += cnt * 30;
        System.out.println("\n====================================================================");
        System.out.println(player.playerNumber + "번 플레이어 님은 다른 플레이어들에게 30만원 씩 돈을 받습니다. (* 보유 돈이 30만원 미만인 플레이어는 돈을 주지 않아도 됩니다.)\n\n");
        System.out.println("" + player.playerNumber + "플레이어의 돈이 +" + (cnt * 30) + "만원 증가합니다. 현재 보유 돈 : " + player.money);
        System.out.println("\n====================================================================");
    }

    public void goldKeyFunction4(Player player, Ground[] ground, Map map) {
        Thread attackSound=new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\034_천둥소리.wav");
                try{
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                       Thread.sleep(2000);
                        c.stop();
                        c.close();
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                }catch(Exception e){

                }

            }
        });
        Scanner scan = new Scanner(System.in);
        String check;
        boolean change = false;

        System.out.println("\n====================================================================");
        System.out.println("다른 플레이어의 땅의 통행료를 3턴 동안 0원으로 만듭니다.\n");
        System.out.print("다른 플레이어가 소유한 땅의 이름을 입력해주세요 ");
        System.out.println("\t**(만약 다른플레이어의 소유한 땅이 없다면 \"없음\"을 쳐주세요 )");
        check = scan.next();
        if (check.equals("없음")) {
            return;
        }
        while (true) {

            for (int i = 0; i < 20; i++) {
                if (check.equals(ground[i].name) && ground[i].getHostNumber() != 0 && ground[i].getHostNumber() != player.playerNumber&&ground[i].isEvnet2X==false&&ground[i].isBlackHole==false) {
                    System.out.println("\n====================================================================");
                    System.out.println(check + "땅의 통행료를 3턴 동안 0원으로 만듭니다.\n");
                    System.out.println("\n====================================================================");
                    //ground[i].tempPassPay = 0;
                    ground[i].freePassPayCnt += 4;
                    change = true;
                    map.changeGroundStatePoint(ground[i].groundStatePointX, ground[i].groundStatePointY, "X");
                    attackSound.start();
                    break;
                }
            }
            if (check.equals("없음")) {
                return;
            }
            if (change == true) {
                break;
            } else {
                System.out.println("입력오류 다른 플레이어가 소유한 땅을 다시입력해주세요. (블랙홀 또는 2배 통행료 이벤트 없는 땅을 입력하세요) \n");
                check = scan.next();
            }
        }

    }

    public void goldKeyFunction5(Player player) {
        player.money += 500;
        System.out.println("\n====================================================================");
        System.out.println("축하합니다 로또에 당첨되었습니다 !!!\n");
        System.out.println("+500만원\t\t" + player.playerNumber + "번 플레이어의 현재 돈 : " + player.money + "만원");
        System.out.println("\n====================================================================");
    }

    public void goldKeyFunction6(Player player,Ground[] allGround,Map map){
        int sum=0;
        Function function=new Function();
        String check;
        Scanner scan = new Scanner(System.in);
        System.out.println("\n====================================================================");
        System.out.println("정부에서 세금을 걷으러 나왔습니다 !        땅에 대한 보유세를 내셔야 합니다 !!\n");
        System.out.println(player.playerNumber+"번 플레이어가 보유한 땅에 대한 값을 지불해야 합니다.\n");
        System.out.println("보유한 땅을 확인하시려면 아무키를 눌러주세요");
        System.out.println("\n====================================================================");
        check = scan.next();
        if(player.groundNum!=0) {
            System.out.print(player.playerNumber + "번 플레이어가 보유한 땅 : \t");
            for (int i = 0; i < 20; i++) {
                if (player.Ground[i]==null) {

                } else {
                    System.out.print(player.Ground[i]);
                    System.out.print("  ");
                    sum += (int) (allGround[i].passPay * 0.3);
                }
            }
            System.out.println("\n");
            System.out.println("\n보유세를 책정하려면 아무키를 눌러주세요\n");
            check = scan.next();
            System.out.println("\n====================================================================");
            System.out.println(player.playerNumber+"번 플레이어가 보유한 땅에 대한 보유세 책정됩니다.(**보유세 책정 방식 : 보유한 땅 통행료의 30%)\n");
            System.out.println("책정된 보유세 : " + sum + "만원");
            System.out.println("\n====================================================================");
            System.out.println("\n보유세를 정산하시려면 아무키나 눌러주세요");
            check = scan.next();
            if(sum>player.money){
                System.out.println(player.playerNumber+"번 플레이어의 돈이 부족하여 보유세를 지불할 수 없습니다." +
                        " \n\n보유한 땅을 매각하셔야 합니다.\t\t 플레이어 보유 돈 : "+player.money+"만원\n\n 부족한 보유세 :"+(sum-player.money)+"만원\n");
                function.sellGroundProcess(player,allGround,map,sum-player.money);
            }
            System.out.println("\n====================================================================");
            player.money-=sum;
            //여기서 파산절차 진행
            if(player.money<0){
                function.BankRunProcess(player,allGround,map);
            }
            System.out.println("보유세 : -"+sum+"만원 차감됩니다.\t\t"+player.playerNumber+"번 플레이어의 현재 돈 : "+player.money+"만원\n");

        }
        else{
            System.out.println("\n====================================================================");
            System.out.println("보유한 땅이 없어서 보유세 면제 입니다.");
            System.out.println("\n====================================================================");
        }
    }

    public void goldKeyFunction7(Player player){
        System.out.println("\n====================================================================");
        System.out.println("무인도 탈출권을 1개 획득하였습니다.");
        System.out.println("\n====================================================================");
        player.islandEscape++;
    }

    public void goldKeyFunction8(Player player){
        System.out.println("\n====================================================================");
        System.out.println("캐릭터별 특수능력 2회를 얻습니다!\t\t(cf) 보석상은 통행료 면제권 아이템 1개를 얻습니다.)");
        System.out.println(" ");

        if(player.playerCharacterNumber==1){
            player.architech_character.characterSpecialFunctionCnt+=2;
            System.out.println("당신의 캐릭터는 건설가 입니다. 건설가 특수능력 2회를 획득하였습니다.\t  특수능력 사용 가능 횟수 : "+player.architech_character.characterSpecialFunctionCnt);
            System.out.println("\n====================================================================");

        }
        else if(player.playerCharacterNumber==3){
            player.traveler_character.characterSpecialFunctionCnt+=2;
            System.out.println("당신의 캐릭터는 여행가 입니다. 여행가 특수능력 2회를 획득하였습니다.\t  특수능력 사용 가능 횟수 : "+player.traveler_character.characterSpecialFunctionCnt);
            System.out.println("\n====================================================================");
        }
        else if(player.playerCharacterNumber==2){
            player.freePassPay++;
            System.out.println("당신의 캐릭터는 보석상 입니다. 면제권 1회를 얻습니다\n");
            System.out.println("\n====================================================================");

        }


    }


}
