import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SpecialRegion extends XY {

    public String name;
    int charityMoney = 0;
    public int salary = 100;

    public SpecialRegion() {
    }

    public SpecialRegion(int x, int y, String name, int index) {
        super(x, y, index);
        this.name = name;
    }


    public void startFunction(Player player) {

        System.out.println("\n"+player.playerNumber+"번 플레이어는  \"시작\" 칸으로 이동하였습니다.\n");
        player.money += salary ;
        System.out.println("\n\n시작칸을 밟을시 월급을 추가로 더 받습니다.\t\t\t +" + (salary ) + "만원\n\n");
        System.out.println(player.playerNumber + "번 플레이어의 현재 돈 : " + player.money + "만원\n");
    }

    public void airplaneFunction(Map map, Player player, Ground ground[], GoldenKey goldenKey[], SpecialRegion start, SpecialRegion charity, SpecialRegion island,Dice dice,Player[] allPlayer,Thread blackHoleThread,Function function,Boolean isDemo) {

        Thread airPlaneSound= new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\008_바림소리_다가와서사라지는_.wav");
                try{
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(c.getMicrosecondLength()/1000);
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                }catch(Exception e){

                }

            }
        });
        //Function function = new Function();
        Scanner scan = new Scanner(System.in);
        String name;
        boolean isBuyGround = false;
        int index = 0, choice = 0;
        boolean change = false;
        airPlaneSound.start();
        while (true) {
            System.out.println("이동하고 싶은 지점의 이름을 정확하게 써주세요");
            name = scan.next();
            //일단 이름 입력 받고,
            if (name.equals("시작점")) {

                map.changePlayerPosition(player, start.getIndex(),function,allPlayer);
                if(start.getIndex()<7){
                    function.playerLabPlusProcess(player,blackHoleThread);
                }
                startFunction(player);
                change = true;
            } else if (name.equals("기부재단")) {

                map.changePlayerPosition(player, charity.getIndex(),function,allPlayer);
                if(charity.getIndex()<7){
                    function.playerLabPlusProcess(player,blackHoleThread);
                }
                charity.charityFunction(player,map,ground);
                //charityFunction(player,map);
                change = true;
            } else if (name.equals("무인도")) {

                map.changePlayerPosition(player, island.getIndex(),function,allPlayer);
                if(island.getIndex()<7){
                    function.playerLabPlusProcess(player,blackHoleThread);
                }
                island.islandFunction(player);
                //islandFunction(player,dice);
                change = true;

            } else if (name.equals("열쇠1") || name.equals("열쇠2") || name.equals("열쇠3") || name.equals("열쇠4")) {

                for (int i = 0; i < 4; i++) {
                    if (name.equals(goldenKey[i].name)) {
                        map.changePlayerPosition(player, goldenKey[i].getIndex(),function,allPlayer);
                        if(goldenKey[i].getIndex()<7){
                            function.playerLabPlusProcess(player,blackHoleThread);
                        }
                        goldenKey[0].randomGoldenKey(player,allPlayer,ground,map,isDemo);
                        change = true;
                    }
                }
            } else {

                for (int i = 0; i < 20; i++) {
                    if (name.equals(ground[i].name)) {
                        map.changePlayerPosition(player, ground[i].getIndex(),function,allPlayer);
                        if(ground[i].getIndex()<7){
                            function.playerLabPlusProcess(player,blackHoleThread);
                        }
                        change = true;

                        System.out.println(" ");
                        System.out.println(player.playerNumber + "번째 플레이어는 현재  \"" + ground[i].name + "\" 땅 으로 이동하셨습니다. \n");


                        if (ground[i].isHost() == false) {
                            ground[i].groundInfo();
                            System.out.println("다음을 선택하세요 \t\t\t1.현재 내 보유 땅과 돈 \t\t2. 땅과 건물 구입하기 \t\t3. 턴을 끝내기  \n");
                            while (true) {
                                try {
                                    choice = scan.nextInt();
                                    break;
                                } catch (InputMismatchException ime) {
                                    scan = new Scanner(System.in);
                                    System.out.println("숫자를 다시 입력해주세요");
                                }
                            }
                            if (choice == 1) {
                                player.playerBuyInfo();
                                System.out.println("이어서 땅을 구매하시겠습니까?\n1.예  2.아니오 턴을 끝내겠습니다. \n");
                                while (true) {
                                    try {
                                        choice = scan.nextInt();
                                        break;
                                    } catch (InputMismatchException ime) {
                                        scan = new Scanner(System.in);
                                        System.out.println("숫자를 다시 입력해주세요");
                                    }
                                }
                                if (choice == 1) {
                                    choice = 2;
                                } else {
                                    break;
                                }
                            }
                            if (choice == 2) {
                                // 땅을 사는 프로세스 진입

                                isBuyGround = function.buyGroundProcess(player, ground[i], i);
                                if (isBuyGround == true) {

                                    map.changeGroundHostPoint(ground[i], player.playerNumber);
                                    // 땅을 샀으면, 건물을 올릴지 물어봐야지.?
                                    System.out.println(" 건물을 추가로 올리시겠습니까 (1. 예\t 2.아니오)? \n");
                                    while (true) {
                                        try {
                                            choice = scan.nextInt();
                                            break;
                                        } catch (InputMismatchException ime) {
                                            scan = new Scanner(System.in);
                                            System.out.println("숫자를 다시 입력해주세요");
                                        }
                                    }
                                    if (choice == 1) {
                                        boolean buildSuccess = false;
                                        ground[i].StructureInfo();
                                        while (true) {
                                            try {
                                                choice = scan.nextInt();
                                                break;
                                            } catch (InputMismatchException ime) {
                                                scan = new Scanner(System.in);
                                                System.out.println("숫자를 다시 입력해주세요");
                                            }
                                        }
                                        buildSuccess = function.buildStructureProcess(player, ground[i], choice);
                                        //  어떤 건물을 지을 것인지, 돈은 충분한지, 충분하다면 차감하고 건물짓기..
                                        //ground[i].passPayInfo();
                                        if (player.playerCharacterNumber == 1 && ground[i].structure.equals("호텔")) {
                                            player.architech_character.passiveFunction(map, ground[i]);
                                        } else {
                                            map.changeGroundStructure(ground[i], choice);
                                        }
                                        // 건물 올리는거 지도에 변한거 표시하기 위해서 map 메소드 사용
                                    } else {
                                        ground[i].passPayInfo();
                                        break;
                                    }


                                } else {

                                }

                            }
                            if (choice == 3) {
                                break;
                            }
                        } else {
                            if (ground[i].getHostNumber() != player.playerNumber) {
                                ground[i].groundInfo();
                                System.out.println("\n\n비행 여행을 이용하여 도착한 다른 플레이어의 땅에서는 \"통행료를\" 지불하지 않고 인수 가능합니다.");
                                function.TakeOverGround(allPlayer[ground[i].getHostNumber()-1],player,ground[i],map,i);

                            } else {
                                function.upgradePlayerStructure(player,ground[i],map);
                                //System.out.println("업그레이드 하는지 확인하고 건물 업그레이드 시키기\n\n");
                            }
                            // 남의 땅으로 들어가는 바보가 어딧냐
                        }

                    }
                }
            }

            if (change == false) {
                System.out.println("입력 오류입니다. 다시입력하세요\n\n");
            } else {
                break;
            }
        }

    }

    public void charityFunction(Player player, Map map,Ground[] allGround) {
        Function function=new Function();
        Scanner scan = new Scanner(System.in);
        String check;
        System.out.println("\n====================================================================");
        System.out.println("\n"+player.playerNumber+"번 플레이어는 \"기부재단\" 칸으로 이동하였습니다.\n");
        System.out.println("기부재단 땅을 밟을시 플레이어의 현금에 따라 기능이 달라집니다.\n\n");
        System.out.println("만약 돈을 300만원 이상 보유 혹은 땅을 1개이상 보유하고 있다면 , 기부재단에 50만원을 기부해야 합니다.\n만약 돈을 300만원 미만이고, 땅을 1개미만으로 보유하고 있다면 , 기부재단에 쌓여있는 돈을 가져갈 수 있습니다.\n\n");
        System.out.println(player.playerNumber + "번 플레이어의 보유 돈 : " + player.money + "만원\n\n");
        System.out.println("기부재단 칸에 대한 정산을 시작하려면 아무키나 눌러주세요");

        check = scan.next();
        if (player.money >= 300||player.groundNum>=1) {
            if(player.money<50){
                System.out.println("player 님은 기부할 돈이 부족합니다. 보유한 땅을 매각하여 기부를 진행합니다.");
                function.sellGroundProcess(player,allGround,map,50-player.money);
            }
            player.money -= 50;
            charityMoney += 50;
            map.changeCharityMoney(charityMoney, this);
            System.out.println("\n====================================================================");
            System.out.println(player.playerNumber + "번 플레이어가 50만원을 기부합니다.\t\t 남은돈 : " + player.money + "만원\n\n");
            System.out.println("\n====================================================================");
        } else {

            player.money += charityMoney;
            if (charityMoney > 0) {
                System.out.println("\n====================================================================");
                System.out.println(player.playerNumber + "번 플레이어의 재정이 어려워 기부재단에서 후원 하였습니다.\t 후원금액 : +" + charityMoney + "만원" + "\t\t 현재 돈 : " + player.money + "\n\n");
                System.out.println("\n====================================================================");
            } else {
                System.out.println("\n====================================================================");
                System.out.println("기부재단에 돈이 없습니다. 후원을 받지 못하였습니다 \n\n");
                System.out.println("\n====================================================================");
            }
            charityMoney = 0;
            map.changeCharityMoney(charityMoney, this);

        }

    }

    public void islandFunction(Player player) {
        Thread happySound=new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\119_축복-배경음.wav");
                try{
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(c.getMicrosecondLength()/1000);
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                }catch(Exception e){

                }

            }
        });

        if(player.stayIslandCnt==0&&player.isPlayerInIsland==false) {
            System.out.println("\n====================================================================");
            System.out.println("\n"+player.playerNumber+"번 플레이어는 \"무인도\" 칸으로 이동하였습니다.\n");
            System.out.println("무인도 땅을 밟을 시, 3회동안 휴식을 취합니다. \n\n3회 이전에 무인도에서 탈출하기 위해서는 주사위 2개가 같은 숫자가 나와야 합니다.\n\n");
            System.out.println("\n====================================================================");
            player.stayIslandCnt = 3;
            player.isPlayerInIsland=true;
        }
        else if(player.stayIslandCnt==0&& player.isPlayerInIsland==true){
            System.out.println("\n====================================================================");
            System.out.println("다음 차례에 "+player.playerNumber+"번 플레이어님은 무인도를 탈출합니다.\n\n");
            System.out.println("\n====================================================================");
            player.isPlayerInIsland=false;
            happySound.start();
            try{
              Thread.sleep(1500);
            }catch (Exception e){

            }
            happySound.interrupt();
        }
    }

}
