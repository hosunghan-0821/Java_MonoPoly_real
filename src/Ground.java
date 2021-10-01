import java.util.Scanner;
public class  Ground extends XY {
    private boolean host;
    private int hostNumber;

    public int hostPointX;
    public int hostPointY;
    public int structurePointX;
    public int structurePointY;
    public int groundStatePointX;
    public int groundStatePointY;

    public int villaPrice;
    public int BuildingPrice;
    public int hotelPrice;
    public int landMarkPrice;

    public int passPay;
    public int tempPassPay;
    public int freePassPayCnt=0;

    public String name;
    public int price;
    public int groundNumber;
    public String structure;
    //Structure structure1=new Structure();

    public boolean isEvnet2X=false;
    public boolean isBlackHole=false;

    public Ground(){}
    public  Ground(String name, int groundX, int groundY, int price, int groundNumber, boolean host,int index,int hostPointX,int hostPointY,int structurePointX, int structurePointY,int villaPrice,int groundStatePointX,int groundStatePointY) {
        super(groundX,groundY,index);
        this.name = name;
        this.price = price;
        this.groundNumber = groundNumber;
        this.host = host;
        this.hostPointX=hostPointX;
        this.hostPointY=hostPointY;
        this.structurePointX=structurePointX;
        this.structurePointY=structurePointY;

        this.villaPrice=villaPrice;
        this.BuildingPrice=(int)(villaPrice*1.5);
        this.hotelPrice=villaPrice*2;
        this.landMarkPrice=villaPrice*4;

        this.groundStatePointX=groundStatePointX;
        this.groundStatePointY=groundStatePointY;

        //this.structure1.villaPrice=villaPrice;
        //this.structure1.BuildingPrice=(int)(villaPrice*1.5);
        //this.structure1.hotelPrice=villaPrice*2;
        //this.structure1.landMarkPrice=villaPrice*4;
    }
    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getHostNumber() {
        return hostNumber;
    }

    public void setHostNumber(int hostNumber) {
        this.hostNumber = hostNumber;
    }

    public void groundInfo(){
        if(host==true){
            String check;
            Scanner scan=new Scanner(System.in);
            System.out.println("땅에 대한 정보를 보기 위해서 아무키나 눌러주세요 ");
            check=scan.next();
            if(structure==null){
                System.out.println("\n====================================================================");
                System.out.println("땅이름 : \t\t\t\t\t"+this.name);
                System.out.println("땅 소유자 플레이어 번호 \t\t"+this.hostNumber+"번 플레이어");
                System.out.println("통행료 : \t\t\t\t\t"+passPay+"만원\n");
            }
            else {
                System.out.println("\n====================================================================");
                System.out.println("땅이름 : \t\t\t\t\t"+this.name);
                System.out.println("땅 소유자 플레이어 번호: \t\t"+this.hostNumber+"번 플레이어");
                System.out.println("땅에 건설된 건물 종류 : \t\t"+structure);
                System.out.println("통행료 : \t\t\t\t\t"+passPay+"만원\n");
            }
        }
        else{
            String check;
            Scanner scan=new Scanner(System.in);
            System.out.println("땅에 대한 정보를 보기 위해서 아무키나 눌러주세요 ");
            check=scan.next();
            System.out.println("\n====================================================================");
            System.out.println(name+"땅은 소유자가 없습니다.\n\n땅의 이름 : "+name + "\n땅의 가격 : "+price+ "만원 \n");
            System.out.println("\n====================================================================\n");
            //System.out.print("땅의 ");super.xyInfo();
            //System.out.println("땅의 X,Y 좌표"+super.getX()+" , "+super.getY());
        }
    }
    public void StructureInfo(){
        System.out.println("1.별장 가격 : "+villaPrice+"\t\t2.빌딩 가격 : "+BuildingPrice+"\t\t3.호텔 가격 :"+hotelPrice+"\n");
    }
    public void LandMarkInfo(){
        System.out.println("랜드마크 가격 : "+landMarkPrice+"만원\n");
    }
    public void passPayInfo(){
        System.out.println("다른 플레이어들이 땅 밟을시 통행료 : "+passPay+"만원 \n");
    }

}
