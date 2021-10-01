public class FreePassPay_Item extends Items{

    public FreePassPay_Item() {
        super.name="통행료 면제권";
    }


    public void function(Player player) {
        System.out.println("통행료가 면제되었습니다.");
        player.freePassPay--;
    }
}
