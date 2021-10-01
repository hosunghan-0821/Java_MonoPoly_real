public class XY {
    private int x;
    private int y;
    private int index;
    public XY(){}
    public XY(int x, int y,int index) {
        this.x = x;
        this.y = y;
        this.index=index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void xyInfo(){
        System.out.println("x 좌표 : "+x+" y 좌표 : "+y+" Index : "+index);
    }
}
