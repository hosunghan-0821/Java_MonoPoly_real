
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Function {
    public int automatic = 0;
    String input;
    boolean isBlackHoleThread = false;
    public static final String FONT_RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String FONT_PURPLE = "\u001B[35m";

    public boolean buyGroundProcess(Player player, Ground ground, int index) {
        // 이 메소드는 플레이어가 땅을 살 때, 돈이 제대로 있는지 확인하고, 플레이어 정보 소유 땅 정보 변경, 땅 객체의 소유자 번호 지정 등 기능 수행
        boolean isBuyGround = false;

        if (player.money >= ground.price) {
            System.out.println("\n====================================================================");
            System.out.println(ground.name + "땅을 구매하셨습니다. \n\n- " + ground.price + "만원이 차감됩니다. \t\t" + player.playerNumber + "번 플레이어의 남은 돈 :" + (player.money - ground.price) + "만원\n");
            System.out.println("\n====================================================================");
            player.money = player.money - ground.price;
            player.groundNum++;
            player.Ground[index] = ground.name;
            ground.setHost(true);
            ground.setHostNumber(player.playerNumber);
            ground.passPay = ground.price;
            isBuyGround = true;

        } else {
            System.out.println(player.playerNumber + " " + player.name + "플레이어 님의 돈이 부족하여 땅을 살 수 없습니다." + "\t\t현재 보유 돈 : " + player.money + "만원\n\n");
            isBuyGround = false;
        }
        return isBuyGround;
    }

    public boolean buildStructureProcess(Player player, Ground ground, int choice) {

        Thread buildSound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\031_슈퍼마리오.WAV");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(c.getMicrosecondLength() / 1000);
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                } catch (Exception e) {

                }

            }
        });


        boolean buildSuccess = false;
        //땅을 세우고 건물 올릴 때 작업하는 과정 충분한 돈이 있는지 확인하고  안되면 컽내는 기능


        if (choice == 1) {
            if (player.money >= ground.villaPrice) {
                buildSound.start();

                player.money = player.money - ground.villaPrice;
                System.out.println("\n====================================================================");
                System.out.println("별장을 건설합니다. \t -" + ground.villaPrice + "만원 차감됩니다.\t\t " + player.playerNumber + "번 플레이어의 남은 돈 : " + player.money + "만원");
                System.out.println("\n====================================================================");
                ground.structure = "별장";
                ground.passPay = (int) ((ground.price + ground.villaPrice) * 1.2);
                buildSuccess = true;
            } else {
                System.out.println("\n돈이 부족합니다. 현재 돈 : " + player.money + "만원\n");
                buildSuccess = false;
            }
        }
        if (choice == 2) {
            if (player.money >= ground.BuildingPrice) {
                buildSound.start();
                player.money = player.money - ground.BuildingPrice;
                System.out.println("\n====================================================================");
                System.out.println("빌딩을 건설합니다. \t -" + ground.BuildingPrice + "만원 차감됩니다.\t\t " + player.playerNumber + "번 플레이어의 남은 돈 : " + player.money + "만원");
                System.out.println("\n====================================================================");
                ground.structure = "빌딩";
                ground.passPay = (int) ((ground.price + ground.BuildingPrice) * 1.2);
                buildSuccess = true;
            } else {
                System.out.println("\n돈이 부족합니다. 현재 돈 : " + player.money + "만원\n");
                buildSuccess = false;
            }
        }
        if (choice == 3) {
            if (player.money >= ground.hotelPrice) {
                buildSound.start();
                player.money = player.money - ground.hotelPrice;
                System.out.println("\n====================================================================");
                System.out.println("호텔을 건설합니다. \t -" + ground.hotelPrice + "만원 차감됩니다.\t\t " + player.playerNumber + "번 플레이어의 남은 돈 : " + player.money + "만원");
                System.out.println("\n====================================================================");
                ground.structure = "호텔";
                ground.passPay = (int) ((ground.price + ground.hotelPrice) * 1.2);
                buildSuccess = true;
            } else {
                System.out.println("\n돈이 부족합니다. 현재 돈 : " + player.money + "만원\n");
                buildSuccess = false;
            }
        }
        return buildSuccess;
    }

    public void passPayProcess(Player playerHost, Player playerGuest, Ground ground, Ground[] allGround, Map map, monitorObject monitorobject) {
        int tempGuestMoney = 0;
        String check;
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        boolean passPay2XEvent = false;

        Thread sadSound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\068_슬픈음악배경.wav");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(3000);
                        c.stop();
                        c.close();
                    } catch (Exception e) {
                        c.stop();
                        c.close();

                    }

                } catch (Exception e) {

                }

            }
        });
        Thread happySound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\119_축복-배경음.wav");
                try {
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

                } catch (Exception e) {

                }

            }
        });


        if (ground.freePassPayCnt > 0 || ground.isBlackHole == true) {
            System.out.println("\n====================================================================");
            System.out.println("외부의 공격을 받아 잠시동안 통행료가 0원입니다.");
            System.out.println("\n====================================================================");
            return;
        }

        if (playerGuest.freePassPay > 0) {
            System.out.println(playerGuest.playerNumber + "번 플레이어는 통행료 면제 아이템이 " + playerGuest.freePassPay + "개 존재 합니다. 사용하시겠습니까? \t\t (1.예 2.아니오)");
            while (true) {
                try {
                    choice = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력하세요");
                }
            }
            if (choice == 1) {
                System.out.println("\n====================================================================");
                System.out.println("통행료가 면제되었습니다.");
                playerGuest.freePassPay--;
                happySound.start();

                return;
            } else {
                System.out.println("다른 플레이어 소유 땅을 밟았습니다. 통행료를 정산하려면 아무키를 눌러주세요 ");
                sadSound.start();


            }

        } else {

            System.out.println("다른 플레이어 소유 땅을 밟았습니다. 통행료를 정산하려면 아무키를 눌러주세요 ");
            sadSound.start();

        }

        //플레이어간의 통행료를 지불하는 class

        check = scan.next();
        if (ground.isEvnet2X == true) {
            ground.passPay = ground.passPay * 2;
            System.out.println(FONT_PURPLE + "\n====================================================================");
            System.out.println("****통행료 2배 이벤트가 진행중입니다.**** 인상된 통행료 : " + ground.passPay + "만원" + RESET);
            passPay2XEvent = true;
        }
        if (playerGuest.money < ground.passPay && playerGuest.groundNum > 0) {
            System.out.println("\n====================================================================");
            System.out.println(playerGuest.playerNumber + "번 플레이어의 돈이 부족하여 통행료를 지불할 수 없습니다. \n\n보유한 땅을 매각하셔야 합니다.\t\t 플레이어 보유 돈 : " + playerGuest.money + "만원\n\n 부족한 통행료 :" + (ground.passPay - playerGuest.money) + "만원\n");
            sellGroundProcess(playerGuest, allGround, map, ground.passPay - playerGuest.money);
        }

        System.out.println("\n====================================================================");
        System.out.println("통행료 지불 전 \n");
        System.out.println(playerGuest.playerNumber + "번 플레이어의  돈 : " + playerGuest.money + "만원\t\t" + playerHost.playerNumber + "번 플레이어의  돈 : " + playerHost.money + "만원\n");


        tempGuestMoney = playerGuest.money;
        playerGuest.money -= ground.passPay;

        //여기서 파산절차 진행
        if (playerGuest.money < 0 && playerGuest.groundNum == 0) {
            System.out.println("(**다른 플레이어 파산시킨 플레이어는 파산 당한 플레이어의 남은 돈만 가져갈 수 있습니다.)\n");
            playerHost.money += tempGuestMoney;
            System.out.println("파산 후 통행료 정리 \n");
            System.out.println(playerGuest.playerNumber + "번 플레이어의  돈 : " + playerGuest.money + "만원\t\t" + playerHost.playerNumber + "번 플레이어의  돈 : " + playerHost.money + "만원\n\n");
            BankRunProcess(playerGuest, allGround, map);
            if (passPay2XEvent == true) {
                ground.passPay = ground.passPay / 2;
            }
            return;
        }
        playerHost.money += ground.passPay;

        System.out.println("통행료 지불 후 \n");
        System.out.println(playerGuest.playerNumber + "번 플레이어의  돈 : " + playerGuest.money + "만원\t\t" + playerHost.playerNumber + "번 플레이어의  돈 : " + playerHost.money + "만원\n\n");
        System.out.println(playerGuest.playerNumber + "번 플레이어는 " + ground.name + " 땅 통행료 " + ground.passPay + "만원을 " + playerHost.playerNumber + "번 플레이어에게 지불합니다.");
        System.out.println("====================================================================");

        sadSound.interrupt();

        if (passPay2XEvent == true) {
            ground.passPay = ground.passPay / 2;
        }


    }

    public void playerLabPlusProcess(Player player, Thread blackHoleThread) {
        String check;
        Scanner scan = new Scanner(System.in);
        player.labCnt++;
        System.out.println("====================================================================");
        System.out.println("플레이어가 출발지점을 넘어갔습니다. 월급을 받으려면 아무키나 눌러주세요");
        check = scan.next();
        System.out.println("\n====================================================================");
        System.out.println(player.playerNumber + "번째 플레이어가 맵을 \"" + player.labCnt + "\" 바퀴 돌았습니다!\t\t\n(게임 종료 조건: 플레이어들 중 누군가 맵을 \"7\"바퀴 돌면 게임을 종료합니다.)\n");
        System.out.println("플레이어들은 한바퀴 돌 때마다 월급을 받습니다. \t\t+100만원\n\n");
        player.money += 100;
        System.out.println(player.playerNumber + "번 플레이어의 현재 돈 : " + player.money + "만원\n\n");
        System.out.println("\n====================================================================");
        if (isBlackHoleThread == false) {
            blackHoleThread.start();
            isBlackHoleThread = true;
        }
    }

    public int playerDiceControl(Player player, boolean isDemo) throws InterruptedException, Exception {
        String check;
        int choice = 0;
        Scanner scan = new Scanner(System.in);

        Thread otherEffectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\주사위-굴리는소리.wav");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    c.open(b);
                    c.start();
                } catch (Exception e) {

                }

            }
        });
        Thread happySound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\119_축복-배경음.wav");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(c.getMicrosecondLength() / 1000);
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                } catch (Exception e) {

                }

            }
        });

        if (player.stayIslandCnt == 0 && player.isPlayerInIsland == false) {
            int diceNumControl = 0;

            System.out.println("\n====================================================================");
            if ((player.dia_dice != 0 || player.golden_dice != 0) && player.special_dice.in_Succession == false && automatic != 1) {
                System.out.print(player.playerNumber + "번 플레이어는 주사위 아이템을 보유하고 있습니다 \t\t");
                System.out.println("주사위 아이템을 사용하시겠습니까? \t\t(1.예 ,2.아니오 ) ");
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
                    player.special_dice.in_Succession = true;
                    while (true) {
                        System.out.println("====================================================================");
                        System.out.println("아이템을 번호를 선택하세요. \n\n아이템 보유상황 : \t1.황금주사위 : " + player.golden_dice + "개 \t2.다이아 주사위 : " + player.dia_dice + "개\n");
                        //System.out.println("어떤 아이템을 사용하시겠습니까? \t\t(1.황금주사위\t\t 2.다이아 주사위\n )");
                        System.out.println("***황금 주사위는 플레이어가 움직이고 싶은 칸 수를 33% 확률로 이동 가능하게 해주는 아이템입니다.\n");
                        System.out.println("***다이아 주사위는 플레이어가 움직이고 싶은 칸 수를 100% 확률로 이동 가능하게 해주는 아이템입니다.\n");
                        System.out.println("====================================================================");
                        while (true) {
                            try {
                                choice = scan.nextInt();
                                break;
                            } catch (InputMismatchException ime) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");
                            }
                        }
                        if (choice == 1 && player.golden_dice > 0) {
                            player.golden_dice--;
                            return player.special_dice.goldenRandomNumber();
                        } else if (choice == 2 && player.dia_dice > 0) {
                            player.dia_dice--;
                            return player.special_dice.diaRandomNumber();
                        } else {
                            System.out.println("사용할 아이템의 갯수를 확인하고 다시 입력하세요\n");
                        }
                    }

                } else {
                    if (isDemo == false) {
                        System.out.println("주사위를 굴리려면 아무키를 눌러주세요 \t(**5초동안 입력없으면 자동으로 진행됩니다.)");
                        InputThread_cancelThread();
                        otherEffectThread.start();
                        player.special_dice.function();
                        System.out.println("첫번째 주사위 결과  : " + player.special_dice.randomNum1 + "\n두번째 주사위 결과  : " + player.special_dice.randomNum2 + "\n\n앞으로 " + (player.special_dice.randomNum1 + player.special_dice.randomNum2) + "칸 전진합니다.\n");
                        if (player.special_dice.isDoubleNumber == true) {
                            System.out.println("\n====================================================================");
                            System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                        }
                    } else {
                        System.out.println("시연용 주사위던지기 시작합니다.\n");

                        System.out.println("첫번째 주사위를 입력해주세요 (0~14)");

                        while (true) {
                            try {
                                player.special_dice.randomNum1 = scan.nextInt();
                                if (player.special_dice.randomNum1 >= 0 && player.special_dice.randomNum1 <= 14) {
                                    break;
                                } else {
                                    System.out.println("0 부터 14 사이의 숫자만 입력해주세요");
                                }
                            } catch (Exception e) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");

                            }
                        }

                        System.out.println("두번째 주사위를 입력해주세요 (0~14)");

                        while (true) {
                            try {
                                player.special_dice.randomNum2 = scan.nextInt();
                                if (player.special_dice.randomNum2 >= 0 && player.special_dice.randomNum2 <= 14) {
                                    break;
                                } else {
                                    System.out.println("0 부터 14 사이의 숫자만 입력해주세요");
                                }
                            } catch (Exception e) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");

                            }
                        }
                        if (player.special_dice.randomNum1 == player.special_dice.randomNum2) {
                            player.special_dice.isDoubleNumber = true;
                            System.out.println("\n====================================================================");
                            System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                        }
                    }
                }

            }// 주사위 아이템이 있을 떄 없을 때  나누는 곳
            else {
                if (player.special_dice.in_Succession) {
                    player.special_dice.in_Succession = false;
                }
                if (isDemo == false) {
                    if (automatic != 1) {
                        System.out.println("주사위를 굴리려면 아무키를 눌러주세요 \t(**5초동안 입력없으면 자동으로 진행됩니다.)");
                        InputThread_cancelThread();
                    }
                    otherEffectThread.start();
                    player.special_dice.function();
                    System.out.println("첫번째 주사위 결과  : " + player.special_dice.randomNum1 + "\n두번째 주사위 결과  : " + player.special_dice.randomNum2 + "\n\n앞으로 " + (player.special_dice.randomNum1 + player.special_dice.randomNum2) + "칸 전진합니다.\n");
                    if (player.special_dice.isDoubleNumber == true) {
                        System.out.println("\n====================================================================");
                        System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                        System.out.println("\n====================================================================");
                    }
                } else {
                    if (automatic != 1) {

                        System.out.println("시연용 주사위던지기 시작합니다.\n");

                        System.out.println("첫번째 주사위를 입력해주세요 (0~14)");

                        while (true) {
                            try {
                                player.special_dice.randomNum1 = scan.nextInt();
                                if (player.special_dice.randomNum1 >= 0 && player.special_dice.randomNum1 <= 14) {
                                    break;
                                } else {
                                    System.out.println("0 부터 14 사이의 숫자만 입력해주세요");
                                }
                            } catch (Exception e) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");

                            }
                        }

                        System.out.println("두번째 주사위를 입력해주세요 (0~14)");

                        while (true) {
                            try {
                                player.special_dice.randomNum2 = scan.nextInt();
                                if (player.special_dice.randomNum2 >= 0 && player.special_dice.randomNum2 <= 14) {
                                    break;
                                } else {
                                    System.out.println("0 부터 14 사이의 숫자만 입력해주세요");
                                }
                            } catch (Exception e) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력해주세요");

                            }
                        }
                        if (player.special_dice.randomNum1 == player.special_dice.randomNum2) {
                            player.special_dice.isDoubleNumber = true;
                            System.out.println("\n====================================================================");
                            System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                        }


                    } else {
                        otherEffectThread.start();
                        player.special_dice.function();
                        System.out.println("첫번째 주사위 결과  : " + player.special_dice.randomNum1 + "\n두번째 주사위 결과  : " + player.special_dice.randomNum2 + "\n\n앞으로 " + (player.special_dice.randomNum1 + player.special_dice.randomNum2) + "칸 전진합니다.\n");
                        if (player.special_dice.isDoubleNumber == true) {
                            System.out.println("\n====================================================================");
                            System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                            System.out.println("\n====================================================================");
                        }

                    }


                }


            }


            //테스트를 위해서 주사위 내가 쓰는 숫자 입력하게끔 가능. ********************* 이 부분이 내가 주사위 컨트롤하게 만든 부분
            /*

            if(automatic!=1) {
                System.out.println("현재 테스트를 위해 원하는 주사위 숫자 컨트롤 가능하다. 원하는 숫자 입력해서 디버깅하시오");

                while (true) {
                    try {

                        diceNumControl = scan.nextInt();
                        break;
                    } catch (InputMismatchException ime) {
                        scan = new Scanner(System.in);
                        System.out.println("숫자를 다시 입력해주세요");
                    }
                }
                return diceNumControl;
            }


             */

            return player.special_dice.randomNum1 + player.special_dice.randomNum2;

        } else {
            if (player.special_dice.in_Succession) {
                player.special_dice.in_Succession = false;
            }
            //무인도에 갖혔을 때,
            System.out.println("\n====================================================================\n");
            System.out.println(player.playerNumber + "번째 플레이어의 순서입니다.\t\t" + player.playerNumber + "번째 플레이어는 현재 무인도에서 갇혔습니다.\n");
            if (player.islandEscape > 0) {
                int choiceIslandEscape;
                System.out.println("무인도 탈출권 아이템을 보유하고 있습니다. 사용하시겠습니까? \t (1.예 2.아니오)");
                while (true) {
                    try {

                        choiceIslandEscape = scan.nextInt();
                        break;
                    } catch (InputMismatchException ime) {
                        scan = new Scanner(System.in);
                        System.out.println("숫자를 다시 입력해주세요");
                    }
                }
                if (choiceIslandEscape == 1) {
                    IslandEscape.function(player);
                    happySound.start();
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                    happySound.interrupt();
                    player.special_dice.function();
                    otherEffectThread.start();
                    System.out.println("첫번째 주사위 결과  : " + player.special_dice.randomNum1 + "\n두번째 주사위 결과  : " + player.special_dice.randomNum2 + "\n\n앞으로 " + (player.special_dice.randomNum1 + player.special_dice.randomNum2) + "칸 전진합니다.\n");
                    if (player.special_dice.isDoubleNumber == true) {
                        System.out.println("\n====================================================================");
                        System.out.println(FONT_RED + "2개의 주사위에서 같은 숫자의 주사위가 나타났습니다. 주사위를 한번 더 굴릴 수 있습니다.\n" + RESET);
                        System.out.println("\n====================================================================");
                    }
                    return player.special_dice.randomNum1 + player.special_dice.randomNum2;
                } else {

                }
            }
            System.out.println("무인도에 갖혔을 때는 주사위 아이템을 사용할 수 없습니다.\n");
            System.out.println("두개의 주사위에서 같은 숫자가 나오면 탈출이 가능합니다. (*무인도 탈출시 나오는 더블 주사위로 주사위를 한번 더 굴리는 효과는 없습니다.) \n\n");
            if (automatic != 1) {
                System.out.println("주사위를 굴리려면 아무키를 눌러주세요 (5초후 자동으로 이동합니다)");
                InputThread_cancelThread();
            }
            otherEffectThread.start();
            player.special_dice.function();
            System.out.println("첫번째 주사위 결과  : " + player.special_dice.randomNum1 + "\n두번째 주사위 결과  : " + player.special_dice.randomNum2 + "\n\n앞으로 " + (player.special_dice.randomNum1 + player.special_dice.randomNum2) + "칸 전진합니다.\n");
            if (player.special_dice.randomNum1 == player.special_dice.randomNum2) {
                player.special_dice.isDoubleNumber = false;
                System.out.println("두개의 주사위에서 같은 숫자가 나왔습니다. 무인도를 탈출합니다!!\n");
                player.stayIslandCnt = 0;
                player.isPlayerInIsland = false;
                return player.special_dice.randomNum1 + player.special_dice.randomNum2;
            } else {
                player.stayIslandCnt--;
                System.out.println(FONT_RED + "무인도 탈출에 실패하였습니다.\t\t무인도 탈출까지 남은 턴 횟수 :" + player.stayIslandCnt + "\n\n" + RESET);

                return 0;
            }
        }

    }

    public void InputThread_cancelThread() throws InterruptedException, Exception, IOException {
        automatic = 0;
        input = null;
        Thread inputThread = new Thread() {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            @Override
            public void run() {
                try {

                    while (!bufferedReader.ready()) {
                        sleep(200);
                    }
                    String inputString = bufferedReader.readLine();
                    input = inputString;
                } catch (Exception e) {
                    automatic = 1;
                    //System.out.println("시간초과 하였습니다.");
                }
            }
        };

        Thread cancelThread = new Thread() {

            Thread clockSound = new Thread(new Runnable() {
                @Override
                public void run() {
                    File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\077_째깍째깍-_시계_.wav");
                    try {
                        AudioInputStream b = AudioSystem.getAudioInputStream(a);
                        Clip c = AudioSystem.getClip();
                        try {
                            c.open(b);
                            c.start();
                            Thread.sleep(c.getMicrosecondLength() / 1000);
                        } catch (Exception e) {
                            c.stop();
                            c.close();
                        }

                    } catch (Exception e) {

                    }

                }
            });

            @Override
            public void run() {
                clockSound.setDaemon(true);
                clockSound.start();
                try {
                    sleep(5000);
                    clockSound.interrupt();
                    inputThread.interrupt();
                } catch (Exception e) {
                    clockSound.interrupt();
                    //System.out.println("취소 스레드 중단 " + e);

                }
            }
        };

        inputThread.start();
        cancelThread.start();
        inputThread.join();
        cancelThread.interrupt();


    }

    public void groundStateCheck(Ground[] ground, Map map) {
        for (int i = 0; i < 20; i++) {
            if (ground[i].freePassPayCnt > 0) {
                ground[i].freePassPayCnt--;
                if (ground[i].freePassPayCnt == 0) {
                    map.changeGroundStatePoint(ground[i].groundStatePointX, ground[i].groundStatePointY, " ");
                }
            }

        }
    }

    public void sellGroundProcess(Player player, Ground[] allGround, Map map, int needMoney) {

        boolean isGroundHave = false;
        String check;
        Scanner scan = new Scanner(System.in);
        int groundIndex = 0, sellGroundPay = 0;
        player.playerSellGroundInfo(allGround);
        while (true) {
            while (true) {
                System.out.println("매각할 땅의 이름을 입력해주세요 \n");
                check = scan.next();
                for (int i = 0; i < 20; i++) {
                    if (check.equals(player.Ground[i])) {
                        isGroundHave = true;
                        groundIndex = i;
                        break;
                    }
                }
                if (isGroundHave == true) {
                    isGroundHave = false;
                    break;
                } else {
                    System.out.println("입력오류 다시 입력하세요");
                }
            }
            //여기까지 매각할 땅 선정
            //이제부터 매각하고 플레이어에게 돈 정산, 지도에서 그 땅 정보 원상태로 바꾸기.
            sellGroundPay = (int) (allGround[groundIndex].passPay * 0.5);
            System.out.println(check + " 땅을 매각하셨습니다. +" + sellGroundPay + "만원 \n");

            if (allGround[groundIndex].isBlackHole == true) {

                System.out.println(FONT_PURPLE + "땅을 매각하면서 존재하던, 블랙홀도 사라졌습니다." + RESET);
                allGround[groundIndex].isBlackHole = false;
                map.changeGroundStatePoint(allGround[groundIndex].groundStatePointX, allGround[groundIndex].groundStatePointY, " ");

            } else if (allGround[groundIndex].isEvnet2X == true) {

                System.out.println(FONT_PURPLE + "땅을 매각하면서 존재하던, 2배 이벤트도 사라졌습니다." + RESET);
                allGround[groundIndex].isEvnet2X = false;
                map.changeGroundStatePoint(allGround[groundIndex].groundStatePointX, allGround[groundIndex].groundStatePointY, "  ");

            }

            player.money += sellGroundPay;
            player.Ground[groundIndex] = null;
            player.groundNum--;
            //플레이어 정보 수정
            map.whenSellProcessChangeMapInfo(allGround[groundIndex]);

            //map 정보 수정.
            allGround[groundIndex].freePassPayCnt = 0;
            allGround[groundIndex].passPay = 0;
            allGround[groundIndex].setHostNumber(0);
            allGround[groundIndex].setHost(false);
            allGround[groundIndex].structure = null;
            //땅 정보 수정
            needMoney -= sellGroundPay;
            if (needMoney < 0) {
                System.out.println("\n====================================================================\n");
                System.out.println("매각을 종료합니다. \t\t" + player.playerNumber + "번 플레이어의 보유 돈 : " + player.money + "만원\n\n");
                System.out.println("\n====================================================================\n");
                break;
            } else if (needMoney > 0 && player.groundNum > 0) {
                System.out.println("매각이 더 필요합니다. ");
            } else {
                System.out.println("\n====================================================================\n");
                System.out.println("갚아야 할 돈이 더 필요합니다. 하지만, 매각할 땅이 더 이상 존재하지 않습니다. 다음 절차로 진행하려면 아무키나 눌러주세요");
                System.out.println("\n====================================================================\n");
                check = scan.next();
                break;
            }
        }
    }

    public void BankRunProcess(Player player, Ground[] allGround, Map map) {

        Thread bankRunSound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\074_어이없는-웃음소리.wav");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(3000);
                        c.stop();
                        c.close();
                    } catch (Exception e) {

                    }

                } catch (Exception e) {

                }

            }
        });
        bankRunSound.start();
        System.out.println("\n====================================================================\n");
        System.out.println(player.playerNumber + "번 플레이어는 돈과 땅이 부족하여 더 이상 게임을 진행 할 수 없습니다.\n");
        System.out.println(player.playerNumber + "번 플레이어의 돈 : " + player.money + "만원\t\t 보유 땅 개수 : " + player.groundNum + "개\n\n");
        System.out.println("파산 당하셨습니다.\n");
        System.out.println(player.playerNumber + "번 플레이어는 게임에서 패배하였습니다. 다른 플레이어를 관전하세요\n");
        System.out.println("\n====================================================================");
        player.money = 0;
        map.BankRunPlayerChangePosition(player);

        player.isPlayerPlaying = false;

    }

    public boolean isGameTheEnd(Player[] allPlayer, int playerNum, Thread blackHoleThread) {
        Scanner scan = new Scanner(System.in);
        String check;
        int cnt = 0;
        for (int i = 0; i < allPlayer.length; i++) {
            if (allPlayer[i].isPlayerPlaying == true) {
                cnt++;
            }
        }
        if (cnt == 1) {
            for (int i = 0; i < allPlayer.length; i++) {
                if (allPlayer[i].isPlayerPlaying == true) {
                    blackHoleThread.interrupt();
                    System.out.println("\n====================================================================");
                    System.out.println("게임이 끝났습니다. 결과를 보시려면 아무키나 눌러주세요");
                    System.out.println("\n====================================================================");
                    check = scan.next();
                    System.out.println("\n====================================================================\n\n\n\n\n\n\n\n");
                    System.out.println(allPlayer[i].playerNumber + "번 플레이어\t" + allPlayer[i].name + "님이 승리하였습니다. \t축하합니다");
                    System.out.println("\n\n\n\n\n\n\n\n====================================================================");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPlayerLabCntEnd(Player[] allPlayer, Ground[] allGround, Thread blackHoleThread) {
        int winnerIndex;
        boolean isGameEnd = false;
        int[] finalWinner = new int[4];
        Scanner scan = new Scanner(System.in);
        String check;
        //System.out.println("player labcnt end 작동중\n");
        for (int i = 0; i < allPlayer.length; i++) {
            if (allPlayer[i].labCnt >= 7) {
                blackHoleThread.interrupt();
                System.out.println("\n====================================================================");
                System.out.println(allPlayer[i].playerNumber + "번 플레이어가 \"" + allPlayer[i].labCnt + "\"바퀴를 달성하였습니다. 정산을 진행하고 게임의 승자를 정합니다. 아무키를 눌러서 정산을 진행해주세요");
                System.out.println("\n====================================================================");
                check = scan.next();
                for (int j = 0; j < allPlayer.length; j++) {
                    if (allPlayer[j].isPlayerPlaying == true) {
                        allPlayer[j].playerFinalInfo(allGround);
                        finalWinner[j] = allPlayer[j].PlayerAllCapital;
                    }
                }
                Arrays.sort(finalWinner);

                System.out.println("최종 승자를 알기 위해서 아무키를 눌러주세요");
                check = scan.next();
                for (int j = 0; j < allPlayer.length; j++) {
                    if (finalWinner[finalWinner.length - 1] == allPlayer[j].PlayerAllCapital) {
                        System.out.println("\n====================================================================");
                        System.out.println("승자는 " + allPlayer[j].playerNumber + "번 플레이어 \t" + allPlayer[j].name + "님입니다! \n 축하합니다.");
                        System.out.println("\n====================================================================");
                    }
                }
                return true;
            } else {

            }
        }
        return false;
    }

    public void upgradePlayerStructure(Player player, Ground ground, Map map) {
        //땅 건물이 호텔인지 아닌지..
        Thread buildSound = new Thread(new Runnable() {
            @Override
            public void run() {
                File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\031_슈퍼마리오.WAV");
                try {
                    AudioInputStream b = AudioSystem.getAudioInputStream(a);
                    Clip c = AudioSystem.getClip();
                    try {
                        c.open(b);
                        c.start();
                        Thread.sleep(c.getMicrosecondLength() / 1000);
                    } catch (Exception e) {
                        c.stop();
                        c.close();
                    }

                } catch (Exception e) {

                }

            }
        });
        String check;
        String beforeStructure;
        int upgrade;
        Scanner scan = new Scanner(System.in);
        if (ground.isBlackHole == true) {
            System.out.println("====================================================================\n");
            System.out.println("자신의 땅에 돌아왔습니다. 하지만 , 블랙홀이 존재합니다. 건물 업그레이드 불가능합니다. ");
            System.out.println("====================================================================\n");
            return;
        }
        System.out.println("====================================================================\n");
        System.out.println("자신의 땅에 돌아왔습니다. 건물 업그레이드 여부를 확인하려면 아무키를 눌러주세요 ");
        System.out.println("====================================================================\n");
        check = scan.next();

        if (ground.structure == null) {
            System.out.println("====================================================================\n");
            System.out.println("당신의 건물은 업그레이드 가능합니다. \t업그레이드 하시겠습니까? \t(1.예 2.아니오)");
            System.out.println("====================================================================\n");
            while (true) {
                try {
                    upgrade = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력하세요");
                }
            }
            if (upgrade == 1) {
                System.out.println("====================================================================\n");
                System.out.println("당신은 땅만 소유하고 있습니다. 업그레이드 비용을 확인 하고 번호를 선택하여 주세요 \t\t 플레이어의 돈 : " + player.money + "만원\n");
                System.out.println("1.호텔 : " + ground.hotelPrice + "만원\t\t 2.빌딩 : " + ground.BuildingPrice + "만원\t\t 3.별장 : " + ground.villaPrice + "만원\n");
                System.out.println("====================================================================\n");
            } else {
                System.out.println("턴을 맞칩니다.");
                return;
            }
            while (true) {
                while (true) {
                    try {
                        upgrade = scan.nextInt();
                        break;
                    } catch (InputMismatchException ime) {
                        scan = new Scanner(System.in);
                        System.out.println("숫자를 다시 입력하세요");
                    }
                }
                if (upgrade == 1) {
                    if (player.money < ground.hotelPrice) {

                        System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        return;
                    } else {
                        buildSound.start();
                        player.money -= ground.hotelPrice;
                        System.out.println("====================================================================\n");
                        System.out.println("건물을 호텔로 업그레이드를 진행합니다. \t\t -" + ground.hotelPrice + "만원 차감합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        System.out.println("====================================================================\n");
                        ground.structure = "호텔";
                        ground.passPay = (int) ((ground.price + ground.hotelPrice) * 1.2);
                        map.upgradePlayerStructure(null, ground);
                        break;
                    }
                } else if (upgrade == 2) {
                    if (player.money < ground.BuildingPrice) {
                        System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        return;
                    } else {
                        buildSound.start();
                        player.money -= (ground.BuildingPrice);
                        System.out.println("====================================================================\n");
                        System.out.println("건물을 빌딩으로 업그레이드를 진행합니다. \t\t -" + (ground.BuildingPrice) + "만원 차감합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        System.out.println("====================================================================\n");
                        ground.structure = "빌딩";
                        ground.passPay = (int) ((ground.price + ground.BuildingPrice) * 1.2);
                        map.upgradePlayerStructure(null, ground);
                        break;
                    }
                } else if (upgrade == 3) {
                    if (player.money < ground.villaPrice) {
                        System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        return;
                    } else {
                        buildSound.start();
                        player.money -= ground.villaPrice;
                        System.out.println("====================================================================\n");
                        System.out.println("건물을 빌라로 업그레이드를 진행합니다. \t\t -" + ground.villaPrice + "만원 차감합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                        System.out.println("====================================================================\n");
                        ground.structure = "별장";
                        ground.passPay = (int) ((ground.price + ground.villaPrice) * 1.2);
                        map.upgradePlayerStructure(null, ground);
                        break;
                    }
                } else {
                    System.out.println("입력오류 다시 입력하세요");
                }
            }
        } else if (ground.structure.compareTo("랜드마크") == 0) {
            System.out.println("====================================================================\n");
            System.out.println("당신의 건물은 건물 최종단계인 랜드마크입니다. 업그레이드 프로세스를 마칩니다.\n");
            System.out.println("====================================================================\n");
            return;
        } else if (ground.structure.compareTo("호텔") == 0) {
            //여기서 랜드마크 업그레이드

            System.out.println("====================================================================\n");
            System.out.println("당신의 건물은 업그레이드 가능합니다. \t업그레이드 하시겠습니까? \t(1.예 2.아니오)");
            System.out.println("====================================================================\n");
            while (true) {
                try {
                    upgrade = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력하세요");
                }
            }

            if (upgrade == 1) {
                System.out.println("====================================================================\n");
                System.out.println("당신의 건물은 호텔입니다. 업그레이드 비용을 확인 하고 번호를 선택하여 주세요 \t\t 플레이어의 돈 : " + player.money + "만원\n");
                System.out.println("1.랜드마크 : " + (ground.landMarkPrice - ground.hotelPrice) + "만원\n");
                System.out.println("====================================================================\n");
                while (true) {
                    while (true) {
                        try {
                            upgrade = scan.nextInt();
                            break;
                        } catch (InputMismatchException ime) {
                            scan = new Scanner(System.in);
                            System.out.println("숫자를 다시 입력하세요");
                        }
                    }
                    if (upgrade == 1) {
                        if (player.money < (ground.landMarkPrice - ground.hotelPrice)) {
                            System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                            return;
                        } else {
                            buildSound.start();
                            player.money -= (ground.landMarkPrice - ground.hotelPrice);
                            System.out.println("====================================================================\n");
                            System.out.println("건물을 랜드마크로 업그레이드를 진행합니다. \t\t -" + (ground.landMarkPrice - ground.hotelPrice) + "만원 차감합니다.\t\t player의 돈 : " + player.money + "만원\n");
                            System.out.println("====================================================================\n");
                            ground.structure = "랜드마크";
                            ground.passPay = (int) ((ground.price + ground.landMarkPrice) * 1.2);
                            map.upgradePlayerStructure("호텔", ground);
                            break;
                        }
                    } else {
                        System.out.println("입력오류 입니다.다시 입력하세요");
                    }
                }
            } else {
                return;
            }
        } else {
            System.out.println("====================================================================\n");
            System.out.println("당신의 건물은 업그레이드 가능합니다. \t업그레이드 하시겠습니까? \t(1.예 2.아니오)");
            System.out.println("====================================================================\n");
            while (true) {
                try {
                    upgrade = scan.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    scan = new Scanner(System.in);
                    System.out.println("숫자를 다시 입력하세요");
                }
            }
            if (upgrade == 1) {
                if (ground.structure.compareTo("빌딩") == 0) {
                    System.out.println("====================================================================\n");
                    System.out.println("당신의 건물은 빌딩입니다. 업그레이드 비용을 확인 하고 번호를 선택하여 주세요 \t\t 플레이어의 돈 : " + player.money + "만원\n");
                    System.out.println("1.호텔 : " + (ground.hotelPrice - ground.BuildingPrice) + "만원\n");
                    System.out.println("====================================================================\n");
                    while (true) {
                        while (true) {
                            try {
                                upgrade = scan.nextInt();
                                break;
                            } catch (InputMismatchException ime) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력하세요");
                            }
                        }
                        if (upgrade == 1) {
                            if (player.money < (ground.hotelPrice - ground.BuildingPrice)) {
                                System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                                return;
                            } else {
                                buildSound.start();
                                player.money -= (ground.hotelPrice - ground.BuildingPrice);
                                System.out.println("====================================================================\n");
                                System.out.println("건물을 호텔로 업그레이드를 진행합니다. \t\t -" + (ground.hotelPrice - ground.BuildingPrice) + "만원 차감합니다.\t\t player의 돈 : " + player.money + "만원\n");
                                System.out.println("====================================================================\n");
                                ground.structure = "호텔";
                                ground.passPay = (int) ((ground.price + ground.hotelPrice) * 1.2);
                                map.upgradePlayerStructure("빌딩", ground);
                                break;
                            }
                        } else {
                            System.out.println("입력오류 입니다.다시 입력하세요");
                        }
                    }
                } else if (ground.structure.compareTo("별장") == 0) {

                    System.out.println("====================================================================\n");
                    System.out.println("당신의 건물은 별장입니다. 업그레이드 비용을 확인 하고 번호를 선택하여 주세요 \t\t 플레이어의 돈 : " + player.money + "만원\n");
                    System.out.println("1.호텔 : " + (ground.hotelPrice - ground.villaPrice) + "만원\t\t 2.빌딩 : " + (ground.BuildingPrice - ground.villaPrice) + "만원\n");
                    System.out.println("====================================================================\n");
                    while (true) {
                        while (true) {
                            try {
                                upgrade = scan.nextInt();
                                break;
                            } catch (InputMismatchException ime) {
                                scan = new Scanner(System.in);
                                System.out.println("숫자를 다시 입력하세요");
                            }
                        }
                        if (upgrade == 1) {

                            if (player.money < ground.hotelPrice - ground.villaPrice) {
                                System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                                return;
                            } else {
                                buildSound.start();
                                player.money -= (ground.hotelPrice - ground.villaPrice);
                                System.out.println("====================================================================\n");
                                System.out.println("건물을 호텔로 업그레이드를 진행합니다. \t\t -" + (ground.hotelPrice - ground.villaPrice) + "만원 차감합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                                System.out.println("====================================================================\n");
                                ground.structure = "호텔";
                                ground.passPay = (int) ((ground.price + ground.hotelPrice) * 1.2);
                                map.upgradePlayerStructure("별장", ground);
                                break;
                            }
                        } else if (upgrade == 2) {
                            if (player.money < ground.BuildingPrice - ground.villaPrice) {
                                System.out.println("돈이 부족하여 업그레이드 진행을 하지 못합니다.\t\t플레이어의 돈 : " + player.money + "만원\n");
                                return;
                            } else {
                                buildSound.start();
                                player.money -= (ground.BuildingPrice - ground.villaPrice);
                                System.out.println("====================================================================\n");
                                System.out.println("건물을 빌딩으로 업그레이드를 진행합니다. \t\t -" + (ground.BuildingPrice - ground.villaPrice) + "만원 차감합니다.\t\t 플레이어의 돈 : " + player.money + "만원\n");
                                System.out.println("====================================================================\n");
                                ground.structure = "빌딩";
                                ground.passPay = (int) ((ground.price + ground.BuildingPrice) * 1.2);
                                map.upgradePlayerStructure("별장", ground);
                                break;
                            }
                        } else {
                            System.out.println("입력오류 입니다.다시 입력하세요");
                        }
                    }

                }


            } else {
                //맨처음에 업그레이드 안한다고 한 부분.
                System.out.println("턴을맞칩니다.\n");
                return;
            }

        }


    }

    public boolean isPlayerLineMonoPoly(Player[] allPlayer) {
        boolean isLineMonopoly = false;
        int cnt = 0;

        for (int i = 0; i < allPlayer.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (allPlayer[i].Ground[j] != null) {
                    cnt++;
                }
            }

            if (cnt == 5) {
                System.out.println("\n====================================================================");
                System.out.println("플레이어가 라인 독점을 하셨습니다. 게임의 승자가 정해졌습니다. \n");
                System.out.println("승자는 " + allPlayer[i].playerNumber + "번 플레이어 \t" + allPlayer[i].name + "님입니다! \n축하합니다.");
                System.out.println("\n====================================================================");
                return true;
            } else {
                cnt = 0;
            }

            for (int j = 5; j < 10; j++) {
                if (allPlayer[i].Ground[j] != null) {
                    cnt++;
                }
            }
            if (cnt == 5) {
                System.out.println("\n====================================================================");
                System.out.println("플레이어가 라인 독점을 하셨습니다. 게임의 승자가 정해졌습니다. \n");
                System.out.println("승자는 " + allPlayer[i].playerNumber + "번 플레이어 \t" + allPlayer[i].name + "님입니다! \n축하합니다.");
                System.out.println("\n====================================================================");
                return true;
            } else {
                cnt = 0;
            }

            for (int j = 10; j < 15; j++) {
                if (allPlayer[i].Ground[j] != null) {
                    cnt++;
                }
            }
            if (cnt == 5) {
                System.out.println("\n====================================================================");
                System.out.println("플레이어가 라인 독점을 하셨습니다. 게임의 승자가 정해졌습니다. \n");
                System.out.println("승자는 " + allPlayer[i].playerNumber + "번 플레이어 \t" + allPlayer[i].name + "님입니다! \n축하합니다.");
                System.out.println("\n====================================================================");
                return true;
            } else {
                cnt = 0;
            }

            for (int j = 15; j < 20; j++) {
                if (allPlayer[i].Ground[j] != null) {
                    cnt++;
                }
            }
            if (cnt == 5) {
                System.out.println("\n====================================================================");
                System.out.println("플레이어가 라인 독점을 하셨습니다. 게임의 승자가 정해졌습니다. \n");
                System.out.println("승자는 " + allPlayer[i].playerNumber + "번 플레이어 \t" + allPlayer[i].name + "님입니다! \n축하합니다.");
                System.out.println("\n====================================================================");
                return true;
            } else {
                cnt = 0;
            }

        }
        return false;
    }

    public void TakeOverGround(Player playerHost, Player playerGuest, Ground ground, Map map, int groundIndex) {
        int check;
        Scanner scan = new Scanner(System.in);

        int takeOverPay = (int) (ground.passPay * 1.2);

        System.out.println("\n====================================================================\n\n");
        System.out.println(ground.name + "땅을 인수 하시겠습니까? \t( 1.예  2.아니오 )\t\t 인수비용 : " + takeOverPay + "만원");
        //System.out.println("\n\n====================================================================");
        while (true) {
            try {
                check = scan.nextInt();
                break;
            } catch (InputMismatchException ime) {
                scan = new Scanner(System.in);
                System.out.println("숫자를 다시 입력해주세요");
            }
        }

        if (check == 1) {
            if (playerGuest.money > takeOverPay) {

                System.out.println("\n====================================================================");
                System.out.println(ground.name + "땅을 인수합니다.\t\t인수한 플레이어 : " + playerGuest.playerNumber + "P\n");
                System.out.println("인수비용 지불 전 \n");
                System.out.println(playerGuest.playerNumber + "번 플레이어의  돈 : " + playerGuest.money + "만원\t\t" + playerHost.playerNumber + "번 플레이어의  돈 : " + playerHost.money + "만원\n");

                //돈 정보 바꾸고
                playerGuest.money -= takeOverPay;
                playerHost.money += takeOverPay;
                //땅 정보 바꾸고
                ground.setHostNumber(playerGuest.playerNumber);
                //맵 정보 바꾸고
                map.changeGroundHostPoint(ground, playerGuest.playerNumber);
                //player 정보 바꾸고
                playerGuest.Ground[groundIndex] = ground.name;
                playerHost.Ground[groundIndex] = null;
                playerGuest.groundNum++;
                playerHost.groundNum--;

                System.out.println("인수비용 지불 후 \n");
                System.out.println(playerGuest.playerNumber + "번 플레이어의  돈 : " + playerGuest.money + "만원\t\t" + playerHost.playerNumber + "번 플레이어의  돈 : " + playerHost.money + "만원\n\n");
                //System.out.println(playerGuest.playerNumber + "번 플레이어는 " + ground.name + " 땅 통행료 " + ground.passPay + "만원을 " + playerHost.playerNumber + "번 플레이어에게 지불합니다.");
                System.out.println("\n====================================================================");


            } else {
                System.out.println("\n====================================================================");
                System.out.println("\n돈이 부족합니다. 현재 돈 : " + playerGuest.money + "만원\n");
                System.out.println("\n====================================================================");
                return;
            }
        } else {
            System.out.println("\n====================================================================");
            System.out.println("인수하지 않고 턴을 마칩니다.");
            System.out.println("\n====================================================================");
            return;
        }
    }

}
