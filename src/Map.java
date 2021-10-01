import java.util.InputMismatchException;
import java.util.Scanner;

public class Map {
    public static final String RESET = "\u001B[0m";
    public static final String FONT_PURPLE = "\u001B[35m";
    public static final String FONT_BLACK = "\u001B[30m";
    public static final String FONT_RED = "\u001B[31m";
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_YELLOW = "\u001B[33m";
    public static final String FONT_BLUE = "\u001B[34m";

    public static final String FONT_CYAN = "\u001B[36m";
    public static final String FONT_WHITE = "\u001B[37m";
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_PURPLE = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";

    public String[][] playMap = new String[39][40];
    public int[] playerX = new int[28];
    public int[] playerY = new int[28];

    public Map() {
    }


    //player 1,2,3,4 들의 index에 따른 좌표값들을 갖도록 미리 값들을 저장하는 소스


    public void mapGroundName(int x, int y, String name) {
        //땅의 xy좌표를 받고 map에 xy 좌표에 맞게끔 이름을 넣는것.
        playMap[x][y] = name;

    }


    public void mapSetting() {

        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 40; j++) {
                playMap[i][j] = "    ";
            }
        }

        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 39; j++) {

                if ((j - 4) % 5 == 0 && j > 0 && (i <= 4 || i >= 34)) {
                    playMap[i][j] = "|";
                } else if ((j - 4) % 5 == 0 && j > 0 && i >= 4) {
                    playMap[i][j] = " ";
                }
                if (j == 4 || j == 34) {
                    playMap[i][j] = "|";
                }

            }
        }

        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 39; j++) {

                if ((i - 4) % 5 == 0 && i > 0 && (j <= 4 || j >= 34)) {
                    playMap[i][j] = "ㅡ  ";
                }

                if (i == 4 || i == 34) {
                    playMap[i][j] = "ㅡ  ";
                }
            }
        }


        playMap[3][8] += " ";
        //playMap[3][18] += " ";
        playMap[3][23] += " ";
        playMap[3][32] += " ";
        playMap[35][2] = "   ";
        playMap[35][8] += " ";
        //playMap[35][18] += " ";
        playMap[35][23] += " ";
        playMap[35][32] += " ";
        //playMap[3][28]="   ";
        playMap[1][2] = "   ";
        playMap[5][2] += " ";
        playMap[10][2] += " ";
        playMap[15][2] += " ";
        //playMap[20][2] += " ";
        playMap[25][2] += " ";
        playMap[30][2] += " ";
        playMap[2][0] = "----";
        playMap[2][1] = "----";
        playMap[2][2] = "--->";
        playMap[36][37] = "기부금: ";
        playMap[36][38] = "0만원";

    }

    void mapImage() {
        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(playMap[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("1번 플레이어의 땅 표시 :" + FONT_RED + "1P" + RESET + " \t\t2번 플레이어의 땅 표시 :" + FONT_BLUE + "2P" + RESET + "\t\t3번 플레이어의 땅 표시 :" + FONT_YELLOW + "3P" + RESET + " \t\t4번 플레이어의 땅 표시 :" + FONT_GREEN + "4P" + RESET);
    }

    void mapPlayerSetting(int x, int y, String playerNum) {

        playMap[x][y] = "  " + playerNum + " ";
    }

    void changePlayerPosition(Player player, int index, Function function,Player[] allPlayer) {
        int check;
        Scanner scan = new Scanner(System.in);
        this.playerX = player.getPlayerX();
        this.playerY = player.getPlayerY();

        if (player.playerNumber == 1) {
            if (player.isPlayerInIsland == false) {
                System.out.println("\n====================================================================");
                System.out.println(" \"1번\" 플레이어의 위치가 변화합니다.\n");
            }
            playMap[player.getX()][player.getY()] = "    ";
            playMap[this.playerX[index]][this.playerY[index]] = FONT_RED + "  1 " + RESET;

            player.setX(this.playerX[index]);
            player.setY(this.playerY[index]);
            player.setIndex(index);
        }
        if (player.playerNumber == 2) {
            if (player.isPlayerInIsland == false) {
                System.out.println("\n====================================================================");
                System.out.println(" \"2번\" 플레이어의 위치가 변화합니다.\n");
            }
            playMap[player.getX()][player.getY()] = "    ";
            playMap[this.playerX[index]][this.playerY[index]] = FONT_BLUE + "  2 " + RESET;

            player.setX(this.playerX[index]);
            player.setY(this.playerY[index]);
            player.setIndex(index);
        }
        if (player.playerNumber == 3) {
            if (player.isPlayerInIsland == false) {
                System.out.println("\n====================================================================");
                System.out.println(" \"3번\" 플레이어의 위치가 변화합니다.\n");
            }
            playMap[player.getX()][player.getY()] = "    ";
            playMap[this.playerX[index]][this.playerY[index]] = FONT_YELLOW + "  3 " + RESET;

            player.setX(this.playerX[index]);
            player.setY(this.playerY[index]);
            player.setIndex(index);
        }
        if (player.playerNumber == 4) {
            if (player.isPlayerInIsland == false) {
                System.out.println("\n====================================================================");
                System.out.println(" \"4번\" 플레이어의 위치가 변화합니다.\n");
            }
            playMap[player.getX()][player.getY()] = "    ";
            playMap[this.playerX[index]][this.playerY[index]] = FONT_GREEN + "  4 " + RESET;

            player.setX(this.playerX[index]);
            player.setY(this.playerY[index]);
            player.setIndex(index);
        }
        if (function.automatic != 1) {
            System.out.println("플레이어의 위치를 맵을 통해 확인 하시겠습니까? (1.예 2. 아니오)");
            System.out.println("\n====================================================================");
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
                playerInfoInMap(allPlayer);
                mapImage();
            } else {
                return;
            }
        } else {
            try {
                Thread.sleep(1500);
                playerInfoInMap(allPlayer);
                mapImage();
            } catch (Exception e) {

            }

        }

    }

    void changeGroundHostPoint(Ground ground, int playerNumber) {
        if (playerNumber == 1) {
            playMap[ground.hostPointX][ground.hostPointY] = FONT_RED + " 1P " + RESET;
        } else if (playerNumber == 2) {
            playMap[ground.hostPointX][ground.hostPointY] = FONT_BLUE + " 2P " + RESET;
        } else if (playerNumber == 3) {
            playMap[ground.hostPointX][ground.hostPointY] = FONT_YELLOW + " 3P " + RESET;
        } else if (playerNumber == 4) {
            playMap[ground.hostPointX][ground.hostPointY] = FONT_GREEN + " 4P " + RESET;
        }
    }

    void changeGroundStructure(Ground ground, int choice) {
        if (choice == 1) {
            playMap[ground.structurePointX][ground.structurePointY] = "Villa";
            playMap[ground.structurePointX][ground.structurePointY + 1] = "   ";
        }
        if (choice == 2) {
            playMap[ground.structurePointX][ground.structurePointY] = "Building";
            playMap[ground.structurePointX][ground.structurePointY + 1] = "";

        }
        if (choice == 3) {
            playMap[ground.structurePointX][ground.structurePointY] = "Hotel";
            playMap[ground.structurePointX][ground.structurePointY + 1] = "   ";
        }
    }

    void changeCharityMoney(int money, SpecialRegion charity) {
        playMap[charity.getX() + 1][charity.getY()] = Integer.toString(money) + "만원";
    }

    void changeGroundStatePoint(int groundStatePointX, int groundStatePointY, String state) {
        int length = state.length();
        if (length == 1) {
            playMap[groundStatePointX][groundStatePointY] = " " + FONT_PURPLE + state + RESET + "  ";
        } else if (length == 2) {
            playMap[groundStatePointX][groundStatePointY] = " " + FONT_PURPLE + state + RESET + " ";
        }
    }

    void whenSellProcessChangeMapInfo(Ground ground) {
        playMap[ground.hostPointX][ground.hostPointY] = "    ";
        //주인 표시 없애고
        if (ground.structure != null) {
            if (ground.structure.equals("별장")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            } else if (ground.structure.equals("빌딩")) {

                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            } else if (ground.structure.equals("호텔")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            } else if (ground.structure.equals("랜드마크")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            }
        }
        //건물표시 없애고
    }

    void BankRunPlayerChangePosition(Player player) {
        playMap[player.getX()][player.getY()] = "    ";
        playMap[7][25] = "ObserveP";
        playMap[7][26] = "";
        if (player.playerNumber == 1) {
            playMap[8][25] = Integer.toString(player.playerNumber) + "   ";
        } else if (player.playerNumber == 2) {
            playMap[8][26] = Integer.toString(player.playerNumber) + "   ";
        } else if (player.playerNumber == 3) {
            playMap[8][27] = Integer.toString(player.playerNumber) + "   ";
        } else if (player.playerNumber == 4) {
            playMap[8][28] = Integer.toString(player.playerNumber) + "   ";
        }
    }

    void upgradePlayerStructure(String beforeStructure, Ground ground) {
        if (beforeStructure != null) {
            if (beforeStructure.equals("별장")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            } else if (beforeStructure.equals("빌딩")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            } else if (beforeStructure.equals("호텔")) {
                playMap[ground.structurePointX][ground.structurePointY] = "    ";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "    ";
            }
        }

        if (ground.structure != null) {
            if (ground.structure.equals("별장")) {
                playMap[ground.structurePointX][ground.structurePointY] = "Villa";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "   ";
            } else if (ground.structure.equals("빌딩")) {

                playMap[ground.structurePointX][ground.structurePointY] = "Building";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "";
            } else if (ground.structure.equals("호텔")) {
                playMap[ground.structurePointX][ground.structurePointY] = "Hotel";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "   ";
            } else if (ground.structure.equals("랜드마크")) {
                playMap[ground.structurePointX][ground.structurePointY] = "LandMark";
                playMap[ground.structurePointX][ground.structurePointY + 1] = "";
            }
        }
    }

    void playerInfoInMap(Player[] allPlayer) {

        String information;
        String groundInformation;
        String itemInformation;
        boolean[] itemCheck=new boolean[4];


        for (int i = 0; i < allPlayer.length; i++) {

            itemInformation = "보유_ITEM : ";

            if(allPlayer[i].golden_dice>0){
               itemInformation+=" gold_dice " + allPlayer[i].golden_dice;
            }
            if(allPlayer[i].dia_dice>0){
                itemInformation+=" dia_dice " + allPlayer[i].dia_dice;
            }
            if(allPlayer[i].freePassPay>0){
                itemInformation+=" 통행료 면제 " + allPlayer[i].freePassPay;
            }
            if(allPlayer[i].islandEscape>0){
               itemInformation+=" 무인도 탈출 " + allPlayer[i].islandEscape;
            }

            information = "LAB : " + allPlayer[i].labCnt + "/7 \t보유 돈 :" + allPlayer[i].money + " 만원";
            groundInformation = "보유 땅 : ";

            for (int k = 0; k < 20; k++) {
                if (allPlayer[i].Ground[k] == null) {

                } else {
                    groundInformation += allPlayer[i].Ground[k];
                    groundInformation += "/";
                }
            }
            if (i + 1 == 1) {
                playMap[5][39] = "\t\t\t" + FONT_RED + "1P" + RESET + "  (" + allPlayer[i].characterName + ")";
                playMap[6][39] = "\t\t\t" + information;
                playMap[7][39] = "\t\t\t" + groundInformation;
                playMap[8][39] = "\t\t\t" + itemInformation;

                if (allPlayer[0].isPlayerPlaying == false) {
                    playMap[5][39] = "\t\t\t" + "*********파산한 플레이어입니다(1P).*********";
                    playMap[6][39] = "\t\t\t";
                    playMap[7][39] = "\t\t\t";
                    playMap[8][39] = "\t\t\t";
                }
            } else if (i + 1 == 2) {
                playMap[10][39] = "\t\t\t" + FONT_BLUE + "2P" + RESET + "  (" + allPlayer[i].characterName + ")";
                playMap[11][39] = "\t\t\t" + information;
                playMap[12][39] = "\t\t\t" + groundInformation;
                playMap[13][39] = "\t\t\t" + itemInformation;
                if (allPlayer[1].isPlayerPlaying == false) {
                    playMap[10][39] = "\t\t\t" + "*********파산한 플레이어입니다(2P)*********";
                    playMap[11][39] = "\t\t\t";
                    playMap[12][39] = "\t\t\t";
                    playMap[13][39] = "\t\t\t";
                }

            } else if (i + 1 == 3) {
                playMap[20][39] = "\t\t\t" + FONT_YELLOW + "3P" + RESET + "  (" + allPlayer[i].characterName + ")";
                playMap[21][39] = "\t\t\t" + information;
                playMap[22][39] = "\t\t\t" + groundInformation;
                playMap[23][39] = "\t\t\t" + itemInformation;
                if (allPlayer[2].isPlayerPlaying == false) {
                    playMap[20][39] = "\t\t\t" + "*********파산한 플레이어입니다(3P)*********";
                    playMap[21][39] = "\t\t\t";
                    playMap[22][39] = "\t\t\t";
                    playMap[23][39] = "\t\t\t";
                }

            } else if (i + 1 == 4) {

                playMap[25][39] = "\t\t\t" + FONT_GREEN + "4P" + RESET + "  (" + allPlayer[i].characterName + ")";
                playMap[26][39] = "\t\t\t" + information;
                playMap[27][39] = "\t\t\t" + groundInformation;
                playMap[28][39] = "\t\t\t" + itemInformation;

                if (allPlayer[3].isPlayerPlaying == false) {
                    playMap[25][39] = "\t\t\t" + "*********파산한 플레이어입니다(4P)*********";
                    playMap[26][39] = "\t\t\t";
                    playMap[27][39] = "\t\t\t";
                    playMap[28][39] = "\t\t\t";
                }

            }


        }
    }
}
