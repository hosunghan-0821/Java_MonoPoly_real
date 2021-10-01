public class IslandEscape extends Items{
    public IslandEscape() {
        super.name="무인도 탈출권";
    }
    public static void function(Player player){
        System.out.println("무인도를 탈출합니다.");
        player.stayIslandCnt=0;
        player.isPlayerInIsland=false;
        player.islandEscape--;
    }
}
