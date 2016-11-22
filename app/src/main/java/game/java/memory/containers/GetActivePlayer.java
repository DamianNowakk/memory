package game.java.memory.containers;

public class GetActivePlayer {
    public static String PLAYERID = "PlayerId";

    public int playerID;

    public GetActivePlayer(int whoseTour){
        this.playerID = whoseTour;
    }


    @Override
    public String toString(){
        return  String.valueOf(playerID);
    }
}
