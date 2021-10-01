import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BlackHoleThread implements Runnable {
    public static final String RESET = "\u001B[0m";
    public static final String FONT_PURPLE = "\u001B[35m";

    private Map map;
    private Ground[] allGround = new Ground[20];
    private int playerNum;
    private Player[] allPlayer;
    public boolean isDemo;
    public monitorObject monitorobject;

    public BlackHoleThread(Map map, Ground[] allGround, int playerNum, Player[] allPlayer, boolean isDemo,monitorObject monitorobject) {
        this.map = map;
        this.isDemo = isDemo;
        this.allGround = allGround;
        this.playerNum = playerNum;
        this.allPlayer = new Player[playerNum];
        this.allPlayer = allPlayer;
        this.monitorobject=monitorobject;
    }

    @Override
    public void run() {

        while (true) {

            Ground[] hostGround;
            int randomNum, randomNumChoice;
            double random, randomChoice;
            random = Math.random();
            randomNum = (int) (random * 20);
            randomChoice = Math.random();
            randomNumChoice = (int) (random * 2 + 1);
            //randomNumChoice=2;
            //randomNumChoice=2;
            //randomNum=1;
            if (isDemo == true) {
                randomNum = 0;
            }

            if (randomNumChoice == 1) {

                Thread attackSound = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\034_천둥소리.wav");
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


                output(randomNum, allGround, 1);
                if (allGround[randomNum].isHost() == true && allGround[randomNum].freePassPayCnt == 0) {

                    allGround[randomNum].isBlackHole = true;
                    map.changeGroundStatePoint(allGround[randomNum].groundStatePointX, allGround[randomNum].groundStatePointY, "X");
                    attackSound.start();
                    try {
                        Thread.sleep(2500);
                        attackSound.interrupt();
                    } catch (Exception e) {

                    }


                }

                try {
                        Thread.sleep(40000);

                    //Thread.sleep(5000);
                } catch (Exception e) {
                    //System.out.println("이벤트 스레드 Interrupt : " + e);
                    break;
                }

                if (allGround[randomNum].isHost() == true) {

                    //allGround[randomNum].passPay = tempPassPay;
                    output2(randomNum, allGround, 1);

                    allGround[randomNum].isBlackHole = false;

                    map.changeGroundStatePoint(allGround[randomNum].groundStatePointX, allGround[randomNum].groundStatePointY, " ");


                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        break;

                    }
                }

            }
            //블랙홀 발생했을 시 위의 코드

            //아래코드는 통행료 2배 이벤트
            else if (randomNumChoice == 2) {


                Thread eventSound = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File a = new File("C:\\자바4_5주차\\src\\MusicFiles\\012_군중환호.wav");
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


                output(randomNum, allGround, 2);
                if (allGround[randomNum].isHost() == true && allGround[randomNum].freePassPayCnt == 0) {
                    allGround[randomNum].isEvnet2X = true;
                    map.changeGroundStatePoint(allGround[randomNum].groundStatePointX, allGround[randomNum].groundStatePointY, "X2");
                    eventSound.start();
                    try {
                        Thread.sleep(2500);
                        eventSound.interrupt();
                    } catch (Exception e) {

                    }

                }

                try {
                        Thread.sleep(40000);

                    //Thread.sleep(5000);
                } catch (Exception e) {
                    //System.out.println("이벤트 스레드 Interrupt : " + e);
                    break;
                }

                output2(randomNum, allGround, 2);
                if (allGround[randomNum].isHost() == true) {
                    allGround[randomNum].isEvnet2X = false;
                    map.changeGroundStatePoint(allGround[randomNum].groundStatePointX, allGround[randomNum].groundStatePointY, "  ");
                }

                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    break;

                }

            }


        }
    }

    public void output(int randomNum, Ground[] allGround, int eventType) {
        if (eventType == 1) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (allGround[randomNum].isHost() == true && allGround[randomNum].freePassPayCnt == 0) {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n블랙홀 발생하였습니다. 40초간 플레이어 보유한 땅의 통행료를 0원으로 만듭니다.\n\n");
                System.out.println("공격 대상 지역 : " + allGround[randomNum].name + "\t\t 공격 대상 플레이어 " + allGround[randomNum].getHostNumber() + "P");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            } else {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n블랙홀 발생했지만, 주인이 없는 땅이여서 아무일도 일어나지 않습니다. 혹은 외부에 다른 공격이 가해지고 있습니다.\n\n");
                System.out.println("공격 대상 지역 : " + allGround[randomNum].name);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            }
        } else if (eventType == 2) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (allGround[randomNum].isHost() == true && allGround[randomNum].freePassPayCnt == 0) {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n통행료 2배 이벤트가 발생하였습니다. 40초간 플레이어 보유한 땅의 통행료 2배로 만듭니다.\n\n");
                System.out.println("이벤트 대상 지역 : " + allGround[randomNum].name + "\t\t 이벤트 대상 플레이어 " + allGround[randomNum].getHostNumber() + "P");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            } else {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n이벤트가 발생했지만, 주인이 없는 땅이여서 아무일도 일어나지 않습니다. 혹은 외부에 다른 공격이 가해지고 있습니다.\n\n");
                System.out.println("이벤트 대상 지역 : " + allGround[randomNum].name);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            }


        }
    }

    public void output2(int randomNum, Ground[] allGround, int eventType) {
        if (eventType == 1) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (allGround[randomNum].isHost() == true && allGround[randomNum].isBlackHole == true) {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n 블랙홀이 사라졌습니다. 통행료를 정상으로 만듭니다.\n\n");
                System.out.println("복구 대상 지역 : " + allGround[randomNum].name);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            }
        } else if (eventType == 2) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            if (allGround[randomNum].isHost() == true && allGround[randomNum].isEvnet2X == true) {
                System.out.println(FONT_PURPLE + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\n\n 이벤트가 끝났습니다. 통행료를 원래대로 만듭니다.\n\n");
                System.out.println("이벤트 끝난 지역 : " + allGround[randomNum].name + "\t\t 이벤트 끝난 대상 플레이어 " + allGround[randomNum].getHostNumber() + "P");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + RESET);
            }

        }
    }
}