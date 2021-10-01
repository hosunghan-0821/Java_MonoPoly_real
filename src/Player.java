public class Player extends XY {
    private int [] playerX= new int [28];
    private int [] playerY= new int [28];

    public int[] getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int[] playerX) {
        this.playerX = playerX;
    }

    public int[] getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int[] playerY) {
        this.playerY = playerY;
    }
    //public final Player a =null;
    String characterName;

    public Special_Dice special_dice;
    public int playerCharacterNumber;
    public int labCnt; // 몇바퀴 돌았는지 저장하는 변수
    public int PlayerAllCapital;
    public int stayIslandCnt; //무인도 횟수제는 변수
    public boolean isPlayerPlaying;
    public String name;
    public int playerNumber;
    public int money;
    public int groundNum=0;
    public boolean isPlayerInIsland;
    public String[] Ground =new String[20];

    public int golden_dice;
    public int dia_dice;
    public int freePassPay;
    public int islandEscape;

    public Architech_Character architech_character;
    public ItemLover_Character itemlover_character;
    public Traveler_Character traveler_character;

    public Player(){}
    public Player(int playerNumber,String name,int money,int positionX,int positionY,int index,boolean isPlayerPlaying,Special_Dice special_Dice,Architech_Character architech_character){
        super(positionX,positionY,index);
        this.playerNumber=playerNumber;
        this.name=name;
        this.money=money;
        this.stayIslandCnt=0;
        this.isPlayerInIsland=false;
        this.labCnt=0;
        this.isPlayerPlaying=isPlayerPlaying;
        this.special_dice= special_Dice;
        this.architech_character=architech_character;
        this.playerCharacterNumber= architech_character.characterNumber;
        this.characterName= architech_character.name;
    }
    public Player(int playerNumber,String name,int money,int positionX,int positionY,int index,boolean isPlayerPlaying,Special_Dice special_Dice,ItemLover_Character itemlover_character){
        super(positionX,positionY,index);
        this.playerNumber=playerNumber;
        this.name=name;
        this.money=money;
        this.stayIslandCnt=0;
        this.isPlayerInIsland=false;
        this.labCnt=0;
        this.isPlayerPlaying=isPlayerPlaying;
        this.special_dice= special_Dice;
        this.itemlover_character=itemlover_character;
        this.playerCharacterNumber= itemlover_character.characterNumber;
        this.characterName= itemlover_character.name;
        this.golden_dice=1;
        this.dia_dice=1;
        this.freePassPay=1;
        this.islandEscape=1;
    }
    public Player(int playerNumber,String name,int money,int positionX,int positionY,int index,boolean isPlayerPlaying,Special_Dice special_Dice,Traveler_Character traveler_character){
        super(positionX,positionY,index);
        this.playerNumber=playerNumber;
        this.name=name;
        this.money=money;
        this.stayIslandCnt=0;
        this.isPlayerInIsland=false;
        this.labCnt=0;
        this.isPlayerPlaying=isPlayerPlaying;
        this.special_dice= special_Dice;
        this.traveler_character=traveler_character;
        this.playerCharacterNumber= traveler_character.characterNumber;
        this.characterName= traveler_character.name;

    }
    public Player(int playerNumber,String name,int money,int positionX,int positionY,int index,boolean isPlayerPlaying,Special_Dice special_Dice){
        super(positionX,positionY,index);
        this.playerNumber=playerNumber;
        this.name=name;
        this.money=money;
        this.stayIslandCnt=0;
        this.isPlayerInIsland=false;
        this.labCnt=0;
        this.isPlayerPlaying=isPlayerPlaying;
        this.special_dice= special_Dice;
    }

    public void playerCharacterInfo(){
       if(this.playerCharacterNumber==1) {
           System.out.println("선택한 캐릭터 : " + this.architech_character.name);
       }
       else if(this.playerCharacterNumber==2){
           System.out.println("선택한 캐릭터 : " + this.itemlover_character.name);
       }
       else if (this.playerCharacterNumber==3){
           System.out.println("선택한 캐릭터 : " + this.traveler_character.name);
       }
       else{
           System.out.println("아직 캐릭터 선택 안했다.");
       }

    }

    public void PlayerInfo(){
        System.out.println("---------------------------------\n");
        System.out.println((playerNumber)+". "+name+" 플레이어님의 정보를 나타냅니다.");
        //super.xyInfo();
        System.out.println("돈 : "+money+" 만원"+"\n아이템 정보 _황금주사위 : "+golden_dice+" 다이아주사위 : "+dia_dice);
        System.out.print("현재 땅 보유 상황 : ");
        for(int i=0;i<20;i++){
            if(Ground[i]==null){
                break;
            }
            System.out.print(" "+Ground[i]);
        }
        System.out.println("보유 땅 갯수"+groundNum);
        System.out.println(" ");
    }
    public void playerBuyInfo(){
        System.out.println("\n====================================================================");
        System.out.println((playerNumber)+". "+name+" 플레이어님의 현재 땅,돈 보유상황이 나타납니다..");
        System.out.println("돈 : \t\t\t"+money+" 만원");
        System.out.print("현재 땅 보유 상황 : ");

        for(int i=0;i<20;i++){
            if(Ground[i]==null){

            }
            else {
                System.out.print(Ground[i]);
                System.out.print(" ");
            }
        }
        System.out.println("\n보유 땅 갯수 \t\t"+groundNum);
        System.out.println(" ");
        System.out.println("\n====================================================================");
    }
    public void playerSellGroundInfo(Ground[] allGround){

        System.out.println("\n====================================================================");
        System.out.print("현재 땅 보유 상황 : \n");

        for(int i=0;i<20;i++){
            if(Ground[i]==null){

            }
            else {
                System.out.println("땅 이름 : "+Ground[i]+"\t\t 땅 매각시 가격 : "+((int)(allGround[i].passPay*0.5))+"만원");
            }
        }
        System.out.println("\n====================================================================");

    }
    public void playerFinalInfo(Ground[] allGround){
        PlayerAllCapital+=money;
        System.out.println("\n====================================================================");
        System.out.println(playerNumber+"번 플레이어의 돈, 땅, 최종자산에 대하여 나타냅니다.\n");
        System.out.println("보유 돈 : \t\t\t\t"+money+"만원");
        System.out.print("보유 땅 : \t\t\t\t");
        for(int i=0;i<20;i++){
            if(Ground[i]==null){

            }
            else {
                System.out.print(Ground[i]);
                PlayerAllCapital+=allGround[i].passPay;
                System.out.print(" ");
            }
        }
        System.out.println("\n최종자산 : \t\t\t\t"+PlayerAllCapital+"만원");
        System.out.println("\n====================================================================");
    }
}
