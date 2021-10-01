import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.lang.reflect.Executable;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws InterruptedException, Exception {

        boolean isDemo=false;
        monitorObject monitorobject=new monitorObject();
        String[] groundName = new String[]{"안양", "김해", "안산", "천안", "전주", "화성", "부천", "청주", "성남", "평택", "용인", "창원", "울산", "수원", "광주", "대전", "대구", "인천", "부산", "서울"};
        int playerNum, cnt = 0;

        int choice = 0;

        boolean gameEnd2, gameEnd1, gameEnd3;

        boolean isBuyGround = false, checkLabCnt = false;
        String check;
        Scanner scan = new Scanner(System.in);

        int[] player1X = new int[28];
        int[] player1Y = new int[28];

        int[] player2X = new int[28];
        int[] player2Y = new int[28];

        int[] player3X = new int[28];
        int[] player3Y = new int[28];

        int[] player4X = new int[28];
        int[] player4Y = new int[28];

        File d = new File("C:\\자바4_5주차\\src\\MusicFiles\\Busybody-Audionautix.wav");
        AudioInputStream b = AudioSystem.getAudioInputStream(d);
        Clip c = AudioSystem.getClip();
        SharedObject sharedObject=new SharedObject();

        Thread musicThread = new Thread(new MusicThread(d, b, c,sharedObject));

        {

            for (int i = 0; i < 8; i++) {

                player1X[i] = 0;
                player1Y[i] = 5 * i;

                player2X[i] = 0;
                player2Y[i] = 5 * i + 1;

                player3X[i] = 0;
                player3Y[i] = 5 * i + 2;

                player4X[i] = 0;
                player4Y[i] = 5 * i + 3;

            }
            cnt = 1;
            for (int i = 8; i < 14; i++) {
                player1X[i] = 5 * cnt;
                player1Y[i] = 38;

                player2X[i] = 5 * cnt + 1;
                player2Y[i] = 38;

                player3X[i] = 5 * cnt + 2;
                player3Y[i] = 38;

                player4X[i] = 5 * cnt + 3;
                player4Y[i] = 38;

                cnt++;
            }
            cnt = 0;
            for (int i = 14; i < 22; i++) {
                player1X[i] = 38;
                player1Y[i] = 35 - (5 * cnt);

                player2X[i] = 38;
                player2Y[i] = 35 - (5 * cnt) + 1;

                player3X[i] = 38;
                player3Y[i] = 35 - (5 * cnt) + 2;

                player4X[i] = 38;
                player4Y[i] = 35 - (5 * cnt) + 3;
                cnt++;
            }
            cnt = 0;
            for (int i = 22; i < 28; i++) {
                player1X[i] = 30 - (5 * cnt);
                player1Y[i] = 0;

                player2X[i] = 30 - (5 * cnt) + 1;
                player2Y[i] = 0;

                player3X[i] = 30 - (5 * cnt) + 2;
                player3Y[i] = 0;

                player4X[i] = 30 - (5 * cnt) + 3;
                player4Y[i] = 0;
                cnt++;
            }
        }
        //게임에 필요한 기본 정보들을 정의하고,  객체에 집어넣기 위한 위의 자료들
        musicThread.start();
        //게임 시작부
        System.out.println("\n====================================================================\n\n\n\n\n");
        System.out.println("MONO_POLY 에 오신것을 환영합니다!");
        System.out.println("\n\n\n\n\n====================================================================");

        System.out.println("게임 설명을 들으시겠습니까? 1 예 2. 아니오");
        while (true) {
            try {
                playerNum = scan.nextInt();
                if (playerNum >= 1 && playerNum <= 2) {
                    break;
                } else {
                    System.out.println("숫자를 다시 입력하세요");
                }
            } catch (InputMismatchException ime) {
                scan = new Scanner(System.in);
                System.out.println("숫자를 다시 입력해주세요");
            }
        }
        if (playerNum == 1) {
            System.out.println("\n====================================================================");
            System.out.println("게임 섦명서 ");
            System.out.println("1. 플레이어는 순서대로 주사위를 굴려 맵에 맞게 이동한다");
            System.out.println("2. 각 선택된 땅에 따라 행동을 할 수 있다 \tex) 일반 땅 : 땅과 건물 사기 가능 ");
            System.out.println("3. 최대한 자신의 땅을 많이 사고, 다른 플레이어로부터 통행료를 받아라.\n\n");
            System.out.println("********************* 승리 조건*************************** ");
            System.out.println("1. 나를 제외한 모든 플레이어를 파산시킨다.");
            System.out.println("2. 정해진 7바퀴를 누군가 돌았을 때, 총 보유자산이 제일 큰 플레이어가 승리한다.");
            System.out.println("\n====================================================================");

        }

        System.out.println("플레이어 명수를 입력하세요( 2~4인용입니다. )");

        while (true) {
            try {
                playerNum = scan.nextInt();
                if (playerNum >= 2 && playerNum <= 4) {
                    break;
                } else {
                    System.out.println("명수를 다시 입력하세요");
                }
            } catch (InputMismatchException ime) {
                scan = new Scanner(System.in);
                System.out.println("숫자를 다시 입력해주세요");
            }
        }
        System.out.println("\n====================================================================\n");
        System.out.println("다음 정보를 갖고 플레이어의 캐릭터를 골라주세요\n");
        System.out.println("1. 건설가 \n");
        System.out.println("특수 능력 : 땅의 건물 건설시, 랜드마크를 한번에 건설 가능\t 게임 당 총 3번 사용가능\n");
        System.out.println("2. 보석상 \n");
        System.out.println("특수 능력 : 게임의 존재하는 아이템 1개씩 보유하고 게임 시작 아이템 ex) 통행료 면제권, 황금 주사위, 다이아 주사위\n");
        System.out.println("3.여행가\n");
        System.out.println("특수 능력 : 다른 플레이어의 땅 통행료를 절반으로 낸다\n");
        System.out.println("\n====================================================================");

        //객체배열을 생성하는코드
        Player[] player = new Player[playerNum];


        for (int i = 0; i < player.length; i++) {
            System.out.println((i + 1) + "번 째 플레이어의 이름을 입력하세요.");
            check = scan.next();
            System.out.println((i+1)+"번 째 플레이어의 선택할 캐릭터 번호를 눌러주세요");
            while (true) {
                try {
                    choice= scan.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("번호를 다시 입력하세요");
                    }
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력해주세요");
                }
            }
            if(choice==1){
                player[i] = new Player(i + 1, check, 1000, 0, i, 0, true, new Special_Dice(),new Architech_Character());//객체배열안에 각 객채를 새롭게 할당해주는 코드.
            }
            else  if (choice==2){
                player[i] = new Player(i + 1, check, 500, 0, i, 0, true, new Special_Dice(),new ItemLover_Character());//객체배열안에 각 객채를 새롭게 할당해주는 코드.
            }
            else if(choice==3){
                player[i] = new Player(i + 1, check, 500, 0, i, 0, true, new Special_Dice(),new Traveler_Character());
            }
            if (i == 0) {
                player[i].setPlayerX(player1X);
                player[i].setPlayerY(player1Y);
            } else if (i == 1) {
                player[i].setPlayerX(player2X);
                player[i].setPlayerY(player2Y);
            } else if (i == 2) {
                player[i].setPlayerX(player3X);
                player[i].setPlayerY(player3Y);
            } else if (i == 3) {
                player[i].setPlayerX(player4X);
                player[i].setPlayerY(player4Y);
            }
            //player[i].PlayerInfo();
        }


        // 땅을 입력하는 코드

        Ground[] ground = new Ground[20];
        // 땅 배열 객체 선언
        cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (i < 2) {
                ground[i] = new Ground(groundName[i], 3, 5 * (cnt + 1), 100 + i * 10, i + 1, false, i + 1, 2, 5 * (cnt + 1), 2, 5 * (cnt + 1) + 1, 50, 2, 5 * (cnt + 1) + 3);
            } else {
                ground[i] = new Ground(groundName[i], 3, 5 * (cnt + 1) + 5, 100 + i * 10, i + 1, false, i + 2, 2, 5 * (cnt + 1) + 5, 2, 5 * (cnt + 1) + 6, 55, 2, 5 * (cnt + 1) + 8);
            }
            cnt++;
        }
        cnt = 0;

        for (int i = 5; i < 10; i++) {
            if (i < 7) {
                ground[i] = new Ground(groundName[i], 5 * (cnt + 1), 35, 100 + i * 10, i + 1, false, i + 3, 5 * (cnt + 1), 36, 5 * (cnt + 1) + 1, 36, 60, 5 * (cnt + 1) + 3, 36);
            } else {
                ground[i] = new Ground(groundName[i], 5 * (cnt + 1) + 5, 35, 100 + i * 10, i + 1, false, i + 4, 5 * (cnt + 1) + 5, 36, 5 * (cnt + 1) + 6, 36, 65, 5 * (cnt + 1) + 8, 36);
            }
            cnt++;

        }
        cnt = 4;
        for (int i = 10; i < 15; i++) {
            if (i < 12) {
                ground[i] = new Ground(groundName[i], 35, 5 * (cnt + 1) + 5, 100 + i * 10, i + 1, false, i + 5, 36, 5 * (cnt + 1) + 5, 36, 5 * (cnt + 1) + 6, 70, 36, 5 * (cnt + 1) + 8);
            } else {
                ground[i] = new Ground(groundName[i], 35, 5 * (cnt + 1), 100 + i * 10, i + 1, false, i + 6, 36, 5 * (cnt + 1), 36, 5 * (cnt + 1) + 1, 75, 36, 5 * (cnt + 1) + 3);
            }
            cnt--;
        }
        cnt = 4;
        for (int i = 15; i < 20; i++) {
            if (i < 17) {
                ground[i] = new Ground(groundName[i], 5 * (cnt + 1) + 5, 3, 100 + i * 10, i + 1, false, i + 7, 5 * (cnt + 1) + 5, 2, 5 * (cnt + 1) + 6, 2, 80, 5 * (cnt + 1) + 8, 2);
            } else {
                ground[i] = new Ground(groundName[i], 5 * (cnt + 1), 3, 100 + i * 10, i + 1, false, i + 8, 5 * (cnt + 1), 2, 5 * (cnt + 1) + 1, 2, 85, 5 * (cnt + 1) + 3, 2);

            }
            cnt--;
        }


        for (int i = 0; i < 20; i++) {
            //ground[i].groundInfo();
        }

        // 땅의 좌표까지 완벽하게 생성자를 입력해서 사용 완료

        Map map = new Map();
        map.mapSetting();

        for (int i = 0; i < 20; i++) {
            map.mapGroundName(ground[i].getX(), ground[i].getY(), ground[i].name);
        }

        //도시 이름 지도에 표시하기.
        for (int i = 0; i < playerNum; i++) {
            map.mapPlayerSetting(player[i].getX(), player[i].getY(), Integer.toString(i + 1));
        }


        GoldenKey[] goldKey = new GoldenKey[4];
        goldKey[0] = new GoldenKey(3, 15, 3, "열쇠1");
        goldKey[1] = new GoldenKey(15, 35, 10, "열쇠2");
        goldKey[2] = new GoldenKey(35, 20, 17, "열쇠3");
        goldKey[3] = new GoldenKey(20, 3, 24, "열쇠4");
        //황금열쇠 객체 생성
        for (int i = 0; i < 4; i++) {
            map.mapGroundName(goldKey[i].getX(), goldKey[i].getY(), goldKey[i].name);
        }

        SpecialRegion start = new SpecialRegion(1, 0, "시작점", 0);
        map.mapGroundName(start.getX(), start.getY(), start.name);
        SpecialRegion airplane = new SpecialRegion(1, 38, "비행여행", 7);
        map.mapGroundName(airplane.getX(), airplane.getY(), airplane.name);
        SpecialRegion charity = new SpecialRegion(35, 38, "기부재단", 14);
        map.mapGroundName(charity.getX(), charity.getY(), charity.name);
        SpecialRegion island = new SpecialRegion(35, 0, "무인도", 21);
        map.mapGroundName(island.getX(), island.getY(), island.name);

        //for(int i=0;i<20;i++){
        // map.changeGroundStatePoint(ground[i].groundStatePointX,ground[i].groundStatePointY,"X");
        //}

        //여기까지 특수 기능이 있는 지역까지, 플레이어 시작세팅+ 맵에 고정되는 것들 전부 맵에 표시하고  맵한번 찍어보는 코드

        Dice dice = new Dice();

        Function function = new Function();
        int a = 0, diceNum = 0;

        System.out.println("시연용 입니까? \t\t(1.예 2.아니오)");
        choice=scan.nextInt();
        if(choice==1){
            isDemo=true;
        }
        else{
            isDemo=false;
        }


        cnt = 0;
        map.playerInfoInMap(player);
        map.mapImage();
        //musicThread.interrupt();

        Thread BlackHoleThread = new Thread(new BlackHoleThread(map, ground, playerNum, player,isDemo,monitorobject));
        BlackHoleThread.setDaemon(true);
        //---------------------------------------------------------------------------- 게임시작

        //BlackHoleThread.start();
        //BlackHoleThread.join();

        int checkSound;
        boolean checkboolean=false;
        //게임 주사위 굴리면서 게임 시작하는 부분
        while (true) {


                if (checkboolean == false) {
                    System.out.println("노래 ( 1. on/2. off) \t\t 3.아예 꺼버리기 ");
                    while (true) {
                        try {
                            checkSound = scan.nextInt();
                            if (checkSound >= 1 && checkSound <= 3) {
                                break;
                            } else {
                                System.out.println("번호를 다시 입력하세요");
                            }
                        } catch (InputMismatchException ime) {
                            scan = new Scanner(System.in);
                            System.out.println("숫자를 다시 입력해주세요");
                        }
                    }
                    if (checkSound == 1) {
                        synchronized (sharedObject) {
                            sharedObject.turnOn = false;
                            sharedObject.notifyAll();

                        }
                    } else if (checkSound == 2) {
                        sharedObject.turnOn = true;
                    } else {
                        sharedObject.TURNOFF = true;
                        checkboolean = true;
                        musicThread.interrupt();
                    }
                }
           /*
            else{
                while(true) {
                    if(checkboolean==true){break;}
                    if (checkboolean == false) {
                        System.out.println("노래 ( 1. on/2. off) \t\t 3.아예 꺼버리기 ");
                        while (true) {
                            try {
                                checkSound = scan.nextInt();
                                if (checkSound >= 1 && checkSound <= 3) {
                                    break;
                                } else {
                                    System.out.println("번호를 다시 입력하세요");
                                }
                            } catch (InputMismatchException ime) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");
                            }
                        }
                        if (checkSound == 1) {
                            synchronized (sharedObject) {
                                sharedObject.turnOn = false;
                                sharedObject.notifyAll();

                            }
                        } else if (checkSound == 2) {
                            sharedObject.turnOn = true;
                        } else {
                            sharedObject.TURNOFF = true;
                            checkboolean = true;
                            musicThread.interrupt();
                            break;
                        }
                    }
                }

            }

            */
            gameEnd1 = function.isPlayerLabCntEnd(player, ground, BlackHoleThread);
            gameEnd2 = function.isGameTheEnd(player, playerNum, BlackHoleThread);
            gameEnd3 = function.isPlayerLineMonoPoly(player);
            if (gameEnd1 == true || gameEnd2 == true || gameEnd3 == true) {
                System.out.println("게임을 종료합니다.");
                break;
            }
            //주사위를 던지기 시작 아랫부분

            while (true) {
                if (player[cnt].isPlayerPlaying == false && player[cnt].playerNumber < playerNum) {
                    cnt++;

                } else if (player[cnt].isPlayerPlaying == false && player[cnt].playerNumber == playerNum) {
                    cnt = 0;
                } else {
                    break;
                }
            }


            //여기서 캐릭터 고유스킬 사용사용하는곳.
            System.out.println("\n====================================================================");
            System.out.println(player[cnt].playerNumber + " 번째 플레이어의 순서입니다.\t\t 현재 바퀴수 : " + player[cnt].labCnt + "바퀴 \t\t 현재 돈 : " + player[cnt].money + "만원");
            System.out.println("아무 키를 눌러서 플레이어 턴을 확인 하세요 (5초동안 아무 입력 없을시 자동으로 행동합니다.) \t\t");
            function.InputThread_cancelThread();
            choice=2;

            if(isDemo==true&&function.automatic!=1) {
                while (true) {
                    try {
                        System.out.println("턴을 넘기시겠습니까? (1.예 2. 아니오)");
                        choice = scan.nextInt();
                        if (choice >= 1 && choice <= 2) {
                            break;
                        } else {
                            System.out.println("숫자를 다시 입력해주세요");
                        }
                    } catch (InputMismatchException ime) {
                        scan = new Scanner(System.in);
                        System.out.println("숫자를 다시 입력해주세요");
                    }
                }
            }

            if(function.automatic==1){
                System.out.println("\n====================================================================");
                System.out.println("자동 이동 시작.");
            }

            if(choice==2) {
                if(player[cnt].playerCharacterNumber==3) {
                    player[cnt].traveler_character.passiveFunction(player[cnt]);
                }
                if (function.automatic == 0) {
                    if (player[cnt].playerCharacterNumber == 1) {
                        player[cnt].architech_character.specialFunction(player[cnt], ground, map);
                    } else if (player[cnt].playerCharacterNumber == 2) {
                        player[cnt].itemlover_character.specialFunction(player[cnt]);
                    } else if (player[cnt].playerCharacterNumber == 3) {

                        player[cnt].traveler_character.specialFunction(map, ground, player[cnt]);

                    }
                }

                diceNum = function.playerDiceControl(player[cnt],isDemo);
                a = player[cnt].getIndex() + diceNum;
                //a 는 이제 맵에서의 위치 인덱스 이다
                if (a >= 28) {
                    checkLabCnt = true;
                    a = a - 28;
                }

                map.changePlayerPosition(player[cnt], a, function, player);
                if (checkLabCnt == true) {
                    function.playerLabPlusProcess(player[cnt], BlackHoleThread);
                }


                //주사위 던지고 나서 도착한 땅에 대한 정보를 알려준다. 아랫부분
                //주사위 던지고 어느 땅인지 확인하는 기능 함수도 만들면 괜찮겠다
                if (a == 3 || a == 10 || a == 17 || a == 24) {
                    goldKey[0].randomGoldenKey(player[cnt], player, ground, map,isDemo);


                } else if (a == 0) {
                    start.startFunction(player[cnt]);

                } else if (a == 7) {
                    //비행여행 칸을 밟았을 때 나타나는 행동
                    System.out.println("\n" + player[cnt].playerNumber + "번 플레이어는  \"비행여행\" 칸으로 이동하였습니다.");
                    System.out.println("비행여행 칸을 밟았을 때, 플레이어가 원하는 장소로 이동이 가능합니다!\t\t 이동하시겠습니까?\t\t (1.예 2.아니오) ");
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
                        airplane.airplaneFunction(map, player[cnt], ground, goldKey, start, charity, island, dice, player, BlackHoleThread, function,isDemo);
                    } else {
                        System.out.println("턴을 종료합니다.");
                    }

                } else if (a == 14) {
                    //기부재단 칸으로 이동했을 시 간단하지 돈 뻇고 모아놧다가 다른
                    charity.charityFunction(player[cnt], map, ground);

                } else if (a == 21) {
                    island.islandFunction(player[cnt]);
                } else {
                    for (int i = 0; i < 20; i++) {
                        //특정  땅 지역에 왔을 때 진행되는 process.
                        if (a == ground[i].getIndex()) {
                            System.out.println(" ");
                            System.out.println((cnt + 1) + "번째 플레이어는 현재  \"" + ground[i].name + "\" 땅 으로 이동하셨습니다. \n");
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
                                    player[cnt].playerBuyInfo();
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
                                    isBuyGround = function.buyGroundProcess(player[cnt], ground[i], i);
                                    if (isBuyGround == true) {

                                        map.changeGroundHostPoint(ground[i], player[cnt].playerNumber);
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
                                            //ground[i].structure1.structureInfo();
                                            //ground[i].structure1.landMarkInfo();

                                            while (true) {
                                                try {
                                                    choice = scan.nextInt();
                                                    break;
                                                } catch (InputMismatchException ime) {
                                                    scan = new Scanner(System.in);
                                                    System.out.println("숫자를 다시 입력해주세요");
                                                }
                                            }

                                            buildSuccess = function.buildStructureProcess(player[cnt], ground[i], choice);
                                            //  어떤 건물을 지을 것인지, 돈은 충분한지, 충분하다면 차감하고 건물짓기..
                                            //ground[i].passPayInfo();
                                            if (buildSuccess == true) {

                                                if (player[cnt].playerCharacterNumber == 1 && ground[i].structure.equals("호텔")) {
                                                    player[cnt].architech_character.passiveFunction(map, ground[i]);
                                                } else {
                                                    map.changeGroundStructure(ground[i], choice);
                                                }

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
                                //주인이 있는 땅을 밟았기 때문에, 돈을 지불해야하는 process가 필요


                                if (ground[i].getHostNumber() != player[cnt].playerNumber) {
                                    ground[i].groundInfo();
                                    function.passPayProcess(player[ground[i].getHostNumber() - 1], player[cnt], ground[i], ground, map,monitorobject);
                                    if(player[cnt].isPlayerPlaying==true) {
                                        function.TakeOverGround(player[ground[i].getHostNumber() - 1], player[cnt], ground[i], map, i);
                                    }
                                } else {
                                    function.upgradePlayerStructure(player[cnt], ground[i], map);

                                }


                            }

                        }
                    }
                }
            }

            if (player[cnt].special_dice.isDoubleNumber == false) {
                cnt++;
            } else {
                player[cnt].special_dice.isDoubleNumber = false;
            }
            checkLabCnt = false;
            if (cnt == playerNum) {
                cnt = 0;
            }
            function.groundStateCheck(ground, map);
            System.out.println("변경된 맵 정보를 얻으시려면 아무키를 눌러주세요\n");
            check = scan.next();
            map.playerInfoInMap(player);
            map.mapImage();


        }
    }
}
